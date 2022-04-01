package com.exemplo.main.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exemplo.main.models.AutoComplete;
import com.exemplo.main.models.Departamento;
import com.exemplo.main.models.Pessoa;
import com.exemplo.main.repositories.CidadeRepository;
import com.exemplo.main.repositories.DepartamentoRepository;
import com.exemplo.main.repositories.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository ps;
	
	@Autowired
	private CidadeRepository cr;
	
	
	@Autowired
	private DepartamentoRepository dr;
	
	private List<Departamento> dpSugeridos = new ArrayList<>();
	
	@GetMapping("rh/pessoas")
	public String listAll(Model model) {
		model.addAttribute("listaPessoas", ps.findAll());
		return"rh/pessoas/index";
	}
	
	@GetMapping("rh/pessoas/nova")
	public String novaPessoa(Model model) {
		model.addAttribute("pessoa", new Pessoa());
		model.addAttribute("cidades", cr.findAll());
		return "rh/pessoas/form";
	}
	
	@PostMapping("rh/pessoas/salvar")
	public String salvar(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("cidades", cr.findAll());
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
		model.addAttribute("cidades", cr.findAll());
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
	
	@RequestMapping("/rh/pessoas/departamentosNomeAutoComplete")
	@ResponseBody
	public List<AutoComplete> departamentosNomeAutoComplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
		List<AutoComplete> sugestoes = new ArrayList<>();
		try {
			if (term.length() == 3) {
				dpSugeridos = dr.serchByName(term);
			}
			for (Departamento dp : dpSugeridos) {
				if (dp.getNome().toLowerCase().contains(term.toLowerCase())) {
					AutoComplete ac = new AutoComplete(dp.getNome(), Long.toString(dp.getId()));
					sugestoes.add(ac);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sugestoes;
	}
}
