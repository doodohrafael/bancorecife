package com.rafael.bancorecife.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.bancorecife.dao.PessoaDao;
import com.rafael.bancorecife.model.Pessoa;
import com.rafael.bancorecife.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaDao pessoaDao;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> pessoas() {
		return pessoaDao.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return pessoaService.salvar(pessoa);
	}

	@GetMapping("/{pessoaId}")
	public Pessoa pessoa(@PathVariable Long pessoaId) {
		return pessoaService.buscarOuFalhar(pessoaId);
	}
	
	@PutMapping("/{pessoaId}")
	public Pessoa atualizar(@PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {
		Pessoa cozinhaAtual = pessoaService.buscarOuFalhar(pessoaId);
		BeanUtils.copyProperties(pessoa, cozinhaAtual, "id");
		return pessoaService.salvar(cozinhaAtual);
	}
	
	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long pessoaId) {
		pessoaService.excluir(pessoaId);
	}

}
