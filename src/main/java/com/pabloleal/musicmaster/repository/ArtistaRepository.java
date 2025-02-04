package com.pabloleal.musicmaster.repository;

import com.pabloleal.musicmaster.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
