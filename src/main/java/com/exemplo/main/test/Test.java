package com.exemplo.main.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exemplo.main.models.Pessoa;
import com.exemplo.main.repositories.PessoaRepository;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private PessoaRepository pr;
	
	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa("Marcelo", LocalDate.parse("1998-10-27"), "12345678911", "marcelo@gmail.com", "987756612");
		Pessoa p2 = new Pessoa("Joao", LocalDate.parse("1999-11-29"), "12345678912", "joao@gmail.com", "987756613");
		Pessoa p3 = new Pessoa("Luan", LocalDate.parse("1994-12-25"), "12345678913", "luan@gmail.com", "987756618");
		
		pr.saveAll(Arrays.asList(p1,p2,p3));
	
	}

}
