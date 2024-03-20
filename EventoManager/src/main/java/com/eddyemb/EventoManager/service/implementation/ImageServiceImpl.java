package com.eddyemb.EventoManager.service.implementation;

import com.eddyemb.EventoManager.exception.BadRequestException;
import com.eddyemb.EventoManager.exception.EntityNotFoundException;
import com.eddyemb.EventoManager.model.Event;
import com.eddyemb.EventoManager.model.Image;
import com.eddyemb.EventoManager.repository.ImageRepository;
import com.eddyemb.EventoManager.service.EventService;
import com.eddyemb.EventoManager.service.ImageService;
import com.eddyemb.EventoManager.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final EventService eventService;
    private final ImageRepository imageRepository;
    @Override
    @Transactional
    public String upload(MultipartFile file, Long eventId) {
        Event event = eventService.getEventById(eventId);
        String contentType = file.getContentType();
        boolean isAnImageFile = ImageUtils.isValidImageFile(contentType);
        if (isAnImageFile){
            try {
                Image image = Image.builder()
                        .originalFileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .image(compressBytes(file.getBytes()))
                        .event(event)
                        .build();
                imageRepository.save(image);
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new BadRequestException("Failed to save image, make sure its a supported image.");
            }
        }else {
            throw new BadRequestException("Invalid Type!");
        }
        return "Saved Image!";
    }
    @Override
    @Transactional
    public Image download(Long eventId) {
        var img = imageRepository.findByEventId(eventId).orElseThrow(() ->
                new EntityNotFoundException("The image could not be saved!"));
        return Image.builder()
                .id(img.getId())
                .originalFileName(img.getOriginalFileName())
                .fileType(img.getFileType())
                .image(decompressBytes(img.getImage()))
                .build();
    }

    @Override
    @Transactional
    public void delete(Long eventId) {
        var img = imageRepository.findByEventId(eventId).orElseThrow(() ->
                new EntityNotFoundException("Could not Eliminate image!"));
                imageRepository.delete(img);
    }

    public static byte[] compressBytes(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer,0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e){
            throw new BadRequestException("Failed to Save Image");
        }
        return outputStream.toByteArray();
    }
    public static byte[] decompressBytes(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try{
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);
                outputStream.write(buffer,0,count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
            System.out.println(ioe.getMessage());
            throw new BadRequestException("Internal error occurred while load image");
        }
        return outputStream.toByteArray();
    }
}
