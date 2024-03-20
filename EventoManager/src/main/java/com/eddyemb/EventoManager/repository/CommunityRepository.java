package com.eddyemb.EventoManager.repository;

import com.eddyemb.EventoManager.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {

    boolean existsByNameOrEmail(String name, String email);
}
