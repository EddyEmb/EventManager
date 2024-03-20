package com.eddyemb.EventoManager.dto.request;

import com.eddyemb.EventoManager.model.Community;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EventRequest {
    @NotBlank
    @Size(min = 5,max = 50)
    private String title;
    @NotBlank
    @Size(min = 50, max = 255)
    private String description;
    private LocalDate date;
    @NotBlank
    private String status;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @NotNull
    private int eventlimit;

}
