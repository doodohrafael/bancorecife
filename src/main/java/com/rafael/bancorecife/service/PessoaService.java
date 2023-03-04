package com.rafael.bancorecife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafael.bancorecife.dao.PessoaDao;
import com.rafael.bancorecife.exception.NegocioException;
import com.rafael.bancorecife.exception.PessoaNaoEncontradaException;
import com.rafael.bancorecife.model.Pessoa;
import com.rafael.bancorecife.service.notificacao.NotificadorSMS;

@Service
public class PessoaService {

	@Autowired
	private PessoaDao pessoaDao;
	
	@Autowired
	private NotificadorSMS notificadorSMS;

	public Pessoa salvar(Pessoa pessoa) {
		try {
			pessoaDao.save(pessoa);
			notificadorSMS.notificar();
		} catch (Exception e) {
			throw new NegocioException("Usuário não foi cadastrado.");
		}
		return null;
	}
	
	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaDao.findById(pessoaId)
				.orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}
	
	public void excluir(Long pessoaId) {
		try {
			pessoaDao.deleteById(pessoaId);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(pessoaId);
		}
	}
	
}
