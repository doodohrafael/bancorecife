package com.rafael.bancorecife.exception;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PessoaNaoEncontradaException(Long pessoaId) {
		super(String.format("Não existe cadastro de pessoa com o código %d", pessoaId));
	}

}
