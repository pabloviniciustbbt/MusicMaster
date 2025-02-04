package com.pabloleal.musicmaster.Main;

import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

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
