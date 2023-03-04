package com.rafael.bancorecife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.bancorecife.model.Pessoa;

public interface PessoaDao extends JpaRepository<Pessoa, Long> {

}
