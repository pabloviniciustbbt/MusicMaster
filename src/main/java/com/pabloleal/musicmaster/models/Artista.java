package com.pabloleal.musicmaster.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String nome;

    @OneToMany (mappedBy = "artista",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private List<Musica> musicas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "\n" + nome +
                "\nMúsicas = " + musicas;
    }
}
