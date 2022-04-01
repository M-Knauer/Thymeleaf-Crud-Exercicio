package com.exemplo.main.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo nome não pode ser vazio")
	@Column(nullable = false)
	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtNascimento;
	
	@Column(nullable = false)
	@NotBlank(message = "Campo cpf não pode ser vazio")
	private String cpf;
	
	@NotBlank(message = "Campo email não pode ser vazio")
	@Email(message = "Email precisa ter formato padrão")
	@Column(nullable = false)
	private String email;
	
	private String tel;
	
	@ManyToOne
	private Departamento departamento;
	
	@ManyToOne
	private Cidade cidade;
	
	public Pessoa() {
		
	}

	public Pessoa(String nome, LocalDate dtNascimento, String cpf, String email, String tel, Departamento departamento, Cidade cidade) {
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.email = email;
		this.tel = tel;
		this.departamento = departamento;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	public String getNomeDepartamento() {
		if (departamento != null) {
			return departamento.getNome();
		}
		return "";
	}
	
	
	
}
