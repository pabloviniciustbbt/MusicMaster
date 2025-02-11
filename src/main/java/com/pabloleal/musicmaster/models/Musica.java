package com.pabloleal.musicmaster.models;

import jakarta.persistence.*;

@Entity
@Table (name = "musicas")
public class Musica {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @Enumerated(EnumType.STRING)
    private GeneroMusical generoMusical;

    @ManyToOne
    private Artista artista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "\nNome da Música = " + titulo +
                "\nArtista = " + artista.getNome() +
                "\nGenero Musical = " + generoMusical + "\n";
    }
}
