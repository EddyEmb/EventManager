package com.eddyemb.EventoManager.service;

import com.eddyemb.EventoManager.dto.request.TicketRequest;
import com.eddyemb.EventoManager.dto.request.response.TicketResponse;

import java.util.List;

public interface TicketService {
    String createTicket(TicketRequest request, Long eventId);
    List<TicketResponse> findAllTickets();
    List<TicketResponse> findAllTicketsByEventId(Long eventId);
}
