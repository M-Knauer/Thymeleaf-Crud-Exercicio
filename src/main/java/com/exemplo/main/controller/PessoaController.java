package com.exemplo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String salvar(@ModelAttribute("pessoa") Pessoa pessoa) {
		ps.save(pessoa);
		return "redirect:/rh/pessoas";
	}
}
