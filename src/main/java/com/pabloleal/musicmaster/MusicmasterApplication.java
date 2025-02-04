package com.pabloleal.musicmaster;

import com.pabloleal.musicmaster.main.Main;
import com.pabloleal.musicmaster.repository.ArtistaRepository;
import com.pabloleal.musicmaster.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicmasterApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MusicmasterApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Main main = new Main(artistaRepository, musicaRepository);
		main.exibeMenu();

	}
}
