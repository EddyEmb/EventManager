package com.eddyemb.EventoManager.mapper;

import com.eddyemb.EventoManager.dto.request.response.CommunityResponse;
import com.eddyemb.EventoManager.dto.request.response.EventResponse;
import com.eddyemb.EventoManager.dto.request.response.TicketResponse;
import com.eddyemb.EventoManager.model.Community;
import com.eddyemb.EventoManager.model.Event;
import com.eddyemb.EventoManager.model.Ticket;
import com.eddyemb.EventoManager.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//Mandar os dados do client side para community response, sem usar setters em tudo
@Component
@RequiredArgsConstructor
public class Mapper {
    private final ModelMapper modelMapper;
    //Communities
    //Returns a community
    public CommunityResponse mapToCommunityResponse(Community community){
        return modelMapper.map(community, CommunityResponse.class);
    }
    //Returns a list of communities
    public List<CommunityResponse> mapToCommunityResponseList(List<Community> communities){
        return communities.stream().map(this::mapToCommunityResponse).collect(Collectors.toList());
    }
    //Events
    public EventResponse mapToEventResponse(Event event){

        return modelMapper.map(event,EventResponse.class);
    }

    public List<EventResponse> mapToEventResponseList(List<Event> events){
        return events.stream().map(this::mapToEventResponse).collect(Collectors.toList());
    }

//    public CommunityResponse mapToCommunityResponseDto(Community community) {
//        CommunityResponse communityResponse = new CommunityResponse();
//        communityResponse.setId(community.getId());
//        communityResponse.setName(community.getName());
//        communityResponse.setDescription(community.getDescription());
//        communityResponse.setEmail(community.getEmail());
//        communityResponse.setWebsite(community.getWebsite());
//        return communityResponse;
//    }

    public EventResponse mapToEventResponseDto(Event event, Community community) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setTitle(event.getTitle());
        eventResponse.setDate(event.getDate());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setStatus(event.getStatus());
        eventResponse.setStartTime(event.getStartTime());
        eventResponse.setEndTime(event.getEndTime());
        eventResponse.setEventlimit(event.getEventlimit());
        eventResponse.setCommunityResponse(mapToCommunityResponse(community));
        return eventResponse;
    }
    private TicketResponse mapToTicketResponse(Ticket ticket) {
        return modelMapper.map(ticket, TicketResponse.class);
    }
    public List<TicketResponse> mapToTicketResponseList(List<Ticket> tickets) {
        return tickets.stream().map(this::mapToTicketResponse).collect(Collectors.toList());
    }


}
