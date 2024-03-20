package com.eddyemb.EventoManager.controller;

import com.eddyemb.EventoManager.dto.request.CommunityRequest;
import com.eddyemb.EventoManager.dto.request.response.CommunityResponse;
import com.eddyemb.EventoManager.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor //Injecção de dependencias
@RequestMapping("/api/v1/communities")
public class CommunityController {
    private final CommunityService communityService;
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CommunityRequest request){
                return ResponseEntity.ok(communityService.createCommunity(request));
    }
    //@Valid for input field, before "@RequestBody"
    @GetMapping
    public ResponseEntity <List<CommunityResponse>> findAllCommunities(){
        return ResponseEntity.ok(communityService.findAllCommunities());
    }
    @GetMapping("/{id}")
    public ResponseEntity <CommunityResponse> findById(@PathVariable("id")Long id){
        return ResponseEntity.ok(communityService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCommunity(@Valid @RequestBody CommunityRequest request,@PathVariable("id") Long id){
        return ResponseEntity.ok(communityService.update(request, id));
    }
}
