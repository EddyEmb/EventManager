package com.eddyemb.EventoManager.repository;

import com.eddyemb.EventoManager.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByEventId(Long id);
}
