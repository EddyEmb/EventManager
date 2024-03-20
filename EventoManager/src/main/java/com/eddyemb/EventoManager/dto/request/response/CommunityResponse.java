package com.eddyemb.EventoManager.dto.request.response;

import lombok.Data;

import java.util.List;

@Data
public class CommunityResponse {
    private Long id;
    private String name;
    private String description;
    private String email;
    private String website;
}
