package com.eddyemb.EventoManager.dto.request.response;

import com.eddyemb.EventoManager.model.Community;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String status;
    private String startTime;
    private String endTime;
    private int eventlimit;
    private CommunityResponse communityResponse;

}
