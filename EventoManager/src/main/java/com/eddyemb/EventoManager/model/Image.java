package com.eddyemb.EventoManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tb_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private byte[] image;
    private String originalFileName;
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
