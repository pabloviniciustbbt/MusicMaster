package com.pabloleal.musicmaster.repository;

import com.pabloleal.musicmaster.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeDigitado);

    @Query("SELECT a.nome FROM Artista a ORDER BY a.nome ASC")
    List<String> listarArtistasOrdemAlfabetica();

    List<Artista> findAllByOrderByNomeAsc();
}
