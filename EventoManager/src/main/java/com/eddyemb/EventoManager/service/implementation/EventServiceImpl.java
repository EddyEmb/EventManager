package com.eddyemb.EventoManager.service.implementation;

import com.eddyemb.EventoManager.dto.request.EventRequest;
import com.eddyemb.EventoManager.dto.request.response.EventResponse;
import com.eddyemb.EventoManager.mapper.Mapper;
import com.eddyemb.EventoManager.model.Community;
import com.eddyemb.EventoManager.model.Event;
import com.eddyemb.EventoManager.repository.EventRepository;
import com.eddyemb.EventoManager.service.CommunityService;
import com.eddyemb.EventoManager.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final Mapper mapper;
    private final CommunityService communityService;
    @Override
    public String createEvent(EventRequest request, Long communityId) {
        Community community = communityService.getCommunityById(communityId);

        Event eventToBeSaved = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .date(LocalDate.now())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .eventlimit(request.getEventlimit())
                .community(community)

                .build();
        eventRepository.save(eventToBeSaved);
        return "Event created successfully";
    }

    @Override
    public List<EventResponse> findAllEvents() {

        var events = eventRepository.findAll();
        return mapper.mapToEventResponseList(events);
    }

    public EventResponse findAllEvent(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Event not Found!"));
        Community community = event.getCommunity();
        return mapper.mapToEventResponseDto(event,community);
    }

    @Override
    public EventResponse findById(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Event not Found!"));
        return mapper.mapToEventResponse(event);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Event not Found!"));
    }

    @Override
    public String update(EventRequest eventRequest, Long id) {
        Event event = getEventById(id);
        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setStatus(eventRequest.getStatus());
        event.setStartTime(eventRequest.getStartTime());
        event.setEndTime(eventRequest.getEndTime());
        event.setEventlimit(eventRequest.getEventlimit());
        eventRepository.save(event);
        return "Event updated Successfully";
    }

    @Override
    public String delete(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
        return "Event '"+event.getTitle() +"' deleted Successfully";
    }


}
