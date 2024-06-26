package com.eddyemb.EventoManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    private String description;
    private LocalDate date;
    private String status;
    private String startTime;
    private String endTime;
    private int eventlimit;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
    
    @OneToMany(mappedBy = "event")
    private List<Image> images;

}
