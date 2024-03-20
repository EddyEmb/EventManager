package com.eddyemb.EventoManager.service;

import com.eddyemb.EventoManager.dto.request.EventRequest;
import com.eddyemb.EventoManager.dto.request.response.EventResponse;
import com.eddyemb.EventoManager.model.Event;

import java.util.List;

public interface EventService {
    String createEvent(EventRequest request, Long id);
    List<EventResponse> findAllEvents();
    EventResponse findAllEvent(Long id);
    EventResponse findById(Long id);
    Event getEventById(Long id);
    String update(EventRequest eventRequest, Long id);
    String delete(Long id);

}
