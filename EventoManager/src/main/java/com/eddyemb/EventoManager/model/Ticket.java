package com.eddyemb.EventoManager.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(nullable = false)
    private String attendeeName;
    @Column(unique = true, nullable = false)
    private String attendeeEmail;
    private boolean isChecked;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

}
