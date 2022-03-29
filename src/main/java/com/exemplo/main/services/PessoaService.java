package com.exemplo.main.services;

import java.util.List;

import com.exemplo.main.models.Pessoa;
import com.exemplo.main.repositories.PessoaRepository;

public class PessoaService {

	private PessoaRepository pr;
	
	public List<Pessoa> findAll() {
		List<Pessoa> pessoas = pr.findAll();
		return pessoas;
	}
	
	public Pessoa findById(Long id) {
		return pr.findById(id).get();
	}
	
	public Pessoa insert(Pessoa pessoa) {
		return pr.save(pessoa);
	}
	
	public Pessoa update (Long id, Pessoa pessoaMod) {
		Pessoa pessoaRaiz = pr.findById(id).get();
		pessoaRaiz.setEmail(pessoaMod.getEmail());
		pessoaRaiz.setTel(pessoaMod.getTel());
		return pessoaRaiz;
	}
	
	public void delete(Long id) {
		pr.deleteById(id);
	}
	
}
