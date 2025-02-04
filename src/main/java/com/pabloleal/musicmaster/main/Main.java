package com.pabloleal.musicmaster.main;

import com.pabloleal.musicmaster.models.Artista;
import com.pabloleal.musicmaster.models.Musica;
import com.pabloleal.musicmaster.repository.ArtistaRepository;
import com.pabloleal.musicmaster.repository.MusicaRepository;

import java.util.List;
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

    public void cadastarArtista(String nomeArtista){
        Artista artista = new Artista();
        artista.setNome(nomeArtista);
        artistaRepository.save(artista);
    }
    private void cadastrarArtista() {

        System.out.print("Digite o nome do Artista: ");
        String nomeDigitado = scan.nextLine();

        artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

        if (artistaBuscado.isPresent()){
            System.out.println("\nArtista já cadastrado no banco de dados!\n");
        } else {
            cadastarArtista(nomeDigitado);
            System.out.println("\nArtista salvo com Sucesso!\n");
        }

    }

    private void cadastrarMusica(){

        Musica musica = new Musica();

        System.out.print("\nDigite o nome da Música: ");
        String nomeMusica = scan.nextLine();

        System.out.print("Digite o nome do Artista: ");
        String nomeDigitado = scan.nextLine();

        artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

        if (!artistaBuscado.isPresent()){
            cadastarArtista(nomeDigitado);
        }

        artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

        musica.setTitulo(nomeMusica);
        musica.setArtista(artistaBuscado.get());
        musicaRepository.save(musica);

        System.out.println("\nMúsica salva com sucesso!");

    }

    private void listarArtistas(){

        List<Artista> artistaList = artistaRepository.findAll();

        artistaList.stream()
                .forEach(System.out::println);

    }

    private void listarMusicas(){

        List<Musica> musicaList = musicaRepository.findAll();

        musicaList.stream()
                .forEach(System.out::println);
    }

    private void burcarMusicaPorArtista(){

        listarArtistas();

        System.out.print("\nDigite o nome do Artista: ");
        String nomeDigitado = scan.nextLine();

        artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

        if (!artistaBuscado.isPresent()){
            System.out.println("\nArtista não encontrado!");
        } else {
            List<Musica> musicaList = artistaBuscado.get().getMusicas();

            musicaList.stream()
                    .forEach(System.out::println);
        }

    }

    private void pesquisarDadosArtista(){

    }


}
