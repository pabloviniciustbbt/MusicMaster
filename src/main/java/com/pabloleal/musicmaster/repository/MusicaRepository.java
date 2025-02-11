package com.pabloleal.musicmaster.repository;

import com.pabloleal.musicmaster.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    @Query("SELECT m FROM Musica m JOIN m.artista a WHERE a.nome ILIKE %:nomeDigitado%")
    List<Musica> buscaMusicasPorArtista(String nomeDigitado);

}
