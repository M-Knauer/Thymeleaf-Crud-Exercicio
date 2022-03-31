package com.exemplo.main.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exemplo.main.models.Pessoa;
import com.exemplo.main.repositories.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository ps;
	
	@GetMapping("rh/pessoas")
	public String listAll(Model model) {
		model.addAttribute("listaPessoas", ps.findAll());
		return"rh/pessoas/index";
	}
	
	@GetMapping("rh/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "rh/pessoas/form";
	}
	
	@PostMapping("rh/pessoas/salvar")
	public String salvar(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult br) {
		if (br.hasErrors()) {
			return "rh/pessoas/form";
		}
		ps.save(pessoa);
		return "redirect:/rh/pessoas";
	}
	
	@GetMapping("rh/pessoas/{id}")
	public String alterar(@PathVariable Long id, Model model) {
		Optional<Pessoa> pessoa = ps.findById(id);
		if (pessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		model.addAttribute("pessoa", pessoa.get());
		return "rh/pessoas/form";
	}
	
	@GetMapping("rh/pessoas/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		Optional<Pessoa> pessoa = ps.findById(id);
		if (pessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		ps.deleteById(id);
		return "redirect:/rh/pessoas";
	}
}
