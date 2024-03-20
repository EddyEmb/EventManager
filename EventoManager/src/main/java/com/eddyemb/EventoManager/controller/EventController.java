package com.eddyemb.EventoManager.controller;

import com.eddyemb.EventoManager.dto.request.EventRequest;
import com.eddyemb.EventoManager.dto.request.response.EventResponse;
import com.eddyemb.EventoManager.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @PostMapping("/{id}")
    public ResponseEntity<String> createEvent(@Valid @RequestBody EventRequest request, @PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.createEvent(request, id), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> findAllEvents(){
      return ResponseEntity.ok(eventService.findAllEvents());
    }
    @GetMapping("/event/{id}")
    public ResponseEntity<EventResponse> findAllEvent(@PathVariable("id") Long id){
       return ResponseEntity.ok(eventService.findAllEvent(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@Valid @RequestBody EventRequest request, @PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.update(request,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.delete(id));
    }
}
