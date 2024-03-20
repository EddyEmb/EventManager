package com.eddyemb.EventoManager.repository;

import com.eddyemb.EventoManager.model.Event;
import com.eddyemb.EventoManager.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByEventId(Long eventId);
    boolean existsByAttendeeEmailAndEvent(String email, Event event);
}
