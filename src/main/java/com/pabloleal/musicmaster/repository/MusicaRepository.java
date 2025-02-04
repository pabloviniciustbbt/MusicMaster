package com.pabloleal.musicmaster.repository;

import com.pabloleal.musicmaster.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
