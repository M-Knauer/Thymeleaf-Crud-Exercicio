package com.exemplo.main.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exemplo.main.models.Cidade;
import com.exemplo.main.models.Departamento;
import com.exemplo.main.models.Pessoa;
import com.exemplo.main.repositories.CidadeRepository;
import com.exemplo.main.repositories.DepartamentoRepository;
import com.exemplo.main.repositories.PessoaRepository;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private PessoaRepository pr;
	
	@Autowired
	private CidadeRepository cr;
	
	@Autowired
	private DepartamentoRepository dr;
	
	@Override
	public void run(String... args) throws Exception {
		
		Cidade c1 = new Cidade("Rio de Janeiro", "RJ");
		Cidade c2 = new Cidade("São Paulo", "SP");
		Cidade c3 = new Cidade("Bahia", "BA");
		
		cr.saveAll(Arrays.asList(c1, c2, c3));
		cr.flush();
		
		Departamento dp1 = new Departamento("Recusos humanos", "RH");
		Departamento dp2 = new Departamento("Tecnologia da informação", "TI");
		Departamento dp3 = new Departamento("Logística", "LO");
		
		dr.saveAll(Arrays.asList(dp1, dp2, dp3));
		dr.flush();

		Pessoa p1 = new Pessoa("Marcelo", LocalDate.parse("1998-10-27"), "12345678911", "marcelo@gmail.com", "987756612", dp2, c3);
		Pessoa p2 = new Pessoa("Joao", LocalDate.parse("1999-11-29"), "12345678912", "joao@gmail.com", "987756613", dp1, c2);
		Pessoa p3 = new Pessoa("Luan", LocalDate.parse("1994-12-25"), "12345678913", "luan@gmail.com", "987756618", dp3, c1);
		
		pr.saveAll(Arrays.asList(p1,p2,p3));
	
	}

}
