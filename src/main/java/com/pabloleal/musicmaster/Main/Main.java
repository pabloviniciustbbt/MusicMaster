package com.pabloleal.musicmaster.main;

import com.pabloleal.musicmaster.models.Artista;
import com.pabloleal.musicmaster.repository.ArtistaRepository;
import com.pabloleal.musicmaster.repository.MusicaRepository;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner scan = new Scanner(System.in);
    private ArtistaRepository artistaRepository;
    private MusicaRepository musicaRepository;
    private Optional<Artista> artistaBuscado;

    public Main(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibeMenu() {

        int opcao = -1;

        while (opcao != 0) {

            String menu = """
                    =================================
                              Music Master
                    =================================
                                        
                    1 - Cadastrar Artistas
                    2 - Cadastrar Músicas
                    3 - Listar Artistas
                    4 - Listar Músicas
                    5 - Buscar Músicas por Artistas
                    6 - Pesquisar Dados Sobre um Artista
                    0 - Sair
                                        
                    Digite Aqui: """;

            System.out.print(menu);
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarArtistas();
                    break;
                case 4:
                    listarMusicas();
                    break;
                case 5:
                    burcarMusicaPorArtista();
                    break;
                case 6:
                    pesquisarDadosArtista();
                    break;
            }
        }
    }

    private void cadastrarArtista() {

        Artista artista = new Artista();

        System.out.print("Digite o nome do Artista: ");
        String nomeDigitado = scan.nextLine();

        artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

        if (artistaBuscado.isPresent()){
            System.out.println("\nArtista já cadastrado no banco de dados!");
        } else {
            artista.setNome(nomeDigitado);
            artistaRepository.save(artista);
            System.out.println("Artista salvo com Sucesso!");
        }

    }

    private void cadastrarMusica(){

    }

    private void listarArtistas(){

    }

    private void listarMusicas(){

    }

    private void burcarMusicaPorArtista(){

    }

    private void pesquisarDadosArtista(){

    }


}
