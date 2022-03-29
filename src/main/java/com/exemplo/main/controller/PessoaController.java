package com.exemplo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
