package com.eddyemb.EventoManager.controller;

import com.eddyemb.EventoManager.dto.request.TicketRequest;
import com.eddyemb.EventoManager.dto.request.response.TicketResponse;
import com.eddyemb.EventoManager.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/{id}")
    public ResponseEntity<String> create(@Valid @RequestBody TicketRequest request, @PathVariable("id") Long eventId){
        return new ResponseEntity<>(ticketService.createTicket(request,eventId),CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findAllTickets(){
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TicketResponse>> findAllTicketsByEventId(@PathVariable("id") Long eventId){
        return ResponseEntity.ok(ticketService.findAllTicketsByEventId(eventId));
    }

}
