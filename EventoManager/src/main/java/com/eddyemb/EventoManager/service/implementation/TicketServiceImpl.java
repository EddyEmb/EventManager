package com.eddyemb.EventoManager.service.implementation;

import com.eddyemb.EventoManager.dto.request.TicketRequest;
import com.eddyemb.EventoManager.dto.request.response.TicketResponse;
import com.eddyemb.EventoManager.exception.BadRequestException;
import com.eddyemb.EventoManager.mapper.Mapper;
import com.eddyemb.EventoManager.model.Event;
import com.eddyemb.EventoManager.model.Ticket;
import com.eddyemb.EventoManager.repository.TicketRepository;
import com.eddyemb.EventoManager.service.EventService;
import com.eddyemb.EventoManager.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final Mapper mapper;
    private final EventService eventService;
    private final TicketRepository ticketRepository;

    @Override
    public String createTicket(TicketRequest request, Long eventId) {
        Event event = eventService.getEventById(eventId);
        //Verify is The ticket already exists
        if (ticketRepository.existsByAttendeeEmailAndEvent(request.getAttendeeEmail(),event)){
            throw new BadRequestException("You're already attending this event! ENJOY!");
        }
        Ticket ticketToBeSaved = Ticket.builder()
                .attendeeName(request.getAttendeeName())
                .attendeeEmail(request.getAttendeeEmail())
                .isChecked(false)
                .event(event)
                .build();
        ticketRepository.save(ticketToBeSaved);
        return "Event Saved Successfully!";
    }

    @Override
    public List<TicketResponse> findAllTickets() {
        return mapper.mapToTicketResponseList(ticketRepository.findAll());
    }

    @Override
    public List<TicketResponse> findAllTicketsByEventId(Long eventId) {
        return mapper.mapToTicketResponseList(ticketRepository.findAllByEventId(eventId));
    }
}
