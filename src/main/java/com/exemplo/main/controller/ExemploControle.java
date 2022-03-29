package com.exemplo.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExemploControle {
	
	@RequestMapping("/dados")
	public String getDados(Model model) {
		model.addAttribute("msg", "Hello world");
		
		return "dados/index";
	}
}
