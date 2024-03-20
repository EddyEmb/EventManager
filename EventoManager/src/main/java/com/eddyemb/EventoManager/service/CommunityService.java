package com.eddyemb.EventoManager.service;

import com.eddyemb.EventoManager.dto.request.CommunityRequest;
import com.eddyemb.EventoManager.dto.request.response.CommunityResponse;
import com.eddyemb.EventoManager.model.Community;

import java.util.List;

public interface CommunityService {
    String createCommunity(CommunityRequest request);
    List<CommunityResponse> findAllCommunities();
    CommunityResponse findById(Long id);
    Community getCommunityById(Long id);
    String update(CommunityRequest communityRequest, Long id);

}
