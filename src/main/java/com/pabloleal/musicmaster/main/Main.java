package com.pabloleal.musicmaster.main;

import com.pabloleal.musicmaster.exceptions.MusicaException;
import com.pabloleal.musicmaster.models.Artista;
import com.pabloleal.musicmaster.models.GeneroMusical;
import com.pabloleal.musicmaster.models.Musica;
import com.pabloleal.musicmaster.repository.ArtistaRepository;
import com.pabloleal.musicmaster.repository.MusicaRepository;
import com.pabloleal.musicmaster.services.ConsultaGemini;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner scan = new Scanner(System.in);
    private ArtistaRepository artistaRepository;
    private MusicaRepository musicaRepository;
    private Optional<Artista> artistaBuscado;

    private String menuGenero = """
                        
            Escolha o gênero musical da música:
                            
            1 - Axe
            2 - Blues
            3 - Classica
            4 - Eletronica
            5 - Forro
            6 - Funk
            7 - Gospel
            8 - Hiphop
            9 - Indie
            10 - Mpb
            11 - Pagode
            12 - Pop
            13 - Punk
            14 - Reggae
            15 - Rock
            16 - Samba
            17 - Sertanejo
            18 - Soundtracks
            19 - Trap
                            
            Digite o número correspondente ao gênero: """;

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
                    6 - Listar Músicas por Gênero
                    7 - Pesquisar Dados Sobre um Artista
                    0 - Sair
                                        
                    Digite Aqui: """;

            try {
                System.out.print(menu);
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n=================================" +
                        "\n         Entrada Invalida" +
                        "\n=================================");
                scan.nextLine();
                continue;
            }

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
                    listarMusicasPorGenero();
                    break;
                case 7:
                    pesquisarDadosArtista();
                    break;
            }
        }
    }

    public void cadastrarArtista(String nomeArtista) {
        Artista artista = new Artista(nomeArtista);
        artistaRepository.save(artista);
    }

    public Artista cadastrarERetornarArtista(String nomeArtista) {
        Artista artista = new Artista(nomeArtista);
        artistaRepository.save(artista);
        return artista;
    }

    private void cadastrarArtista() {

        String opcao = "s";

        while (opcao.equalsIgnoreCase("s")) {

            try {
                System.out.print("Digite o nome do Artista: ");
                String nomeDigitado = scan.nextLine();

                if (nomeDigitado.isEmpty() || nomeDigitado.trim().isEmpty()) {
                    throw new IllegalArgumentException("\nO nome não pode estar em branco ou conter apenas espaços\n");
                }

                artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

                if (artistaBuscado.isPresent()) {
                    System.out.println("\nArtista já cadastrado no banco de dados!\n");
                } else {
                    cadastrarArtista(nomeDigitado);
                    System.out.println("\nArtista salvo com Sucesso!\n");
                }

                System.out.print("Gostaria de cadastrar outro artista (S/N)? ");
                opcao = scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cadastrarMusica() {

        String opcao = "s";

        while (opcao.equalsIgnoreCase("s")) {

            try {
                int generoDigitado;

                System.out.print("\nDigite o nome da Música: ");
                String musicaDigitada = scan.nextLine();

                System.out.print("Digite o nome do Artista: ");
                String nomeDigitado = scan.nextLine();

                if (musicaDigitada.isEmpty() || nomeDigitado.isEmpty() || musicaDigitada.trim().isEmpty() || nomeDigitado.trim().isEmpty()) {
                    throw new IllegalArgumentException("\nO nome da música ou do artista não pode estar em branco ou conter apenas espaços.\n");
                }

                artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(nomeDigitado);

                if (!artistaBuscado.isPresent()) {
                    artistaBuscado = Optional.of(cadastrarERetornarArtista(nomeDigitado));
                }

                try {
                    System.out.println(menuGenero);
                    generoDigitado = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\n=================================" +
                            "\n         Entrada Invalida" +
                            "\n=================================");
                    scan.nextLine();
                    continue;
                }

                GeneroMusical generoMusical = GeneroMusical.fromInt(generoDigitado);

                Musica musica = new Musica(musicaDigitada, artistaBuscado.get(), generoMusical);

                musicaRepository.save(musica);

                System.out.println("\nMúsica salva com sucesso!\n");

                System.out.print("Gostaria de cadastrar outra música (S/N)? ");
                opcao = scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void listarArtistas() {

        List<Artista> artistaList = artistaRepository.findAllByOrderByNomeAsc();
        artistaList.forEach(System.out::println);

    }

    private void listarMusicas() {

        List<Musica> musicaList = musicaRepository.findAllByOrderByTituloAsc();

        musicaList.forEach(System.out::println);
    }

    private void burcarMusicaPorArtista() {

        String opcao = "s";

        while (opcao.equalsIgnoreCase("s")) {

            try {
                List<String> artistaList = artistaRepository.listarArtistasOrdemAlfabetica();
                artistaList.forEach(System.out::println);

                System.out.print("\nDigite o nome do Artista: ");
                String nomeDigitado = scan.nextLine();

                if (nomeDigitado.isEmpty() || nomeDigitado.trim().isEmpty()) {
                    throw new IllegalArgumentException("\nO nome não pode estar em branco ou conter apenas espaços\n");
                }

                List<Musica> musicaList = musicaRepository.buscaMusicasPorArtista(nomeDigitado);

                if (!musicaList.isEmpty()) {
                    musicaList.stream()
                            .forEach(System.out::println);
                } else {
                    System.out.println("\nNenhuma música de " + nomeDigitado + " foi encontrada!\n");
                }

                System.out.print("Gostaria de realizar uma nova busca (S/N)? ");
                opcao = scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void listarMusicasPorGenero() {

        String opcao = "s";

        while (opcao.equalsIgnoreCase("s")) {

            try {
                System.out.println(menuGenero);
                int generoDigitado = scan.nextInt();
                scan.nextLine();

                if (generoDigitado < 1 || generoDigitado > 19) {
                    throw new MusicaException("\nGênero Musical não encontrado!\n");
                }

                exibirMusicasGeneroSelecionado(generoDigitado);

            } catch (MusicaException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("\n=================================" +
                        "\n         Entrada Invalida" +
                        "\n=================================");
                scan.nextLine();
                continue;
            }

            System.out.print("Gostaria de realizar uma nova consulta (S/N)? ");
            opcao = scan.nextLine();

        }
    }

    private void pesquisarDadosArtista() {

        String opcao = "s";

        while (opcao.equalsIgnoreCase("s")) {

            try {
                System.out.print("\nVocê quer pesquisar dados sobre qual Artista? ");
                String nomeArtista = scan.nextLine();

                if (nomeArtista.isEmpty() || nomeArtista.trim().isEmpty()) {
                    throw new IllegalArgumentException("\nO nome não pode estar em branco ou conter apenas espaços");
                }

                String resposta = ConsultaGemini.obterDadosArtista(nomeArtista);
                System.out.println(resposta.trim() + "\n");

                System.out.print("Gostaria de realizar uma nova pesquisa (S/N)? ");
                opcao = scan.nextLine();
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void exibirMusicasGeneroSelecionado(int generoDigitado) {
        List<Musica> musicaList = musicaRepository.findAll();

        List<Musica> musicasFiltradas = musicaList.stream()
                .filter(m -> m.getGeneroMusical().getNumeroGenero() == generoDigitado)
                .collect(Collectors.toList());

        if (!musicasFiltradas.isEmpty()) {
            musicasFiltradas.forEach(System.out::println);
        } else {
            System.out.println("\nNenhuma música encontrada para o gênero selecionado!\n");
        }
    }
}