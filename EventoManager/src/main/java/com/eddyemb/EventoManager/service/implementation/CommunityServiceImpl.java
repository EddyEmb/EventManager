package com.eddyemb.EventoManager.service.implementation;

import com.eddyemb.EventoManager.dto.request.CommunityRequest;
import com.eddyemb.EventoManager.dto.request.response.CommunityResponse;
import com.eddyemb.EventoManager.exception.BadRequestException;
import com.eddyemb.EventoManager.mapper.Mapper;
import com.eddyemb.EventoManager.model.Community;
import com.eddyemb.EventoManager.repository.CommunityRepository;
import com.eddyemb.EventoManager.service.CommunityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final Mapper mapper;
    @Override
    public String createCommunity(CommunityRequest request) {
        if (communityRepository.existsByNameOrEmail(request.getName(),request.getEmail())) {
            throw new BadRequestException("Registration Error, Name or Mail already exist");
        }
            Community communityToBeSaved = Community.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .description(request.getDescription())
                    .website(request.getWebsite())
                    .password(request.getPassword())
                    .build();
            communityRepository.save(communityToBeSaved);
            return "Community created successfully";
    }

    public List<CommunityResponse> findAllCommunities(){
        var communities =communityRepository.findAll();
        return mapper.mapToCommunityResponseList(communities);
    }

    @Override
    public CommunityResponse findById(Long id) {
        var community =communityRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Community not Found!"));//Exception in case no match
        return mapper.mapToCommunityResponse(community);
    }

    @Override
    public Community getCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Community not Found!"));
    }

    @Override
    public String update(CommunityRequest communityRequest, Long id) {
        Community community = getCommunityById(id);
        community.setName(communityRequest.getName());
        community.setDescription(communityRequest.getDescription());
        community.setEmail(communityRequest.getEmail());
        community.setWebsite(communityRequest.getWebsite());
        communityRepository.save(community);

        return "Community updated Successfully";
    }


}
