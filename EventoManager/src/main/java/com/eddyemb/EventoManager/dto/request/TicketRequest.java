package com.eddyemb.EventoManager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String attendeeName;
    @NotBlank
    @Email
    private String attendeeEmail;
    private boolean isChecked;
}
