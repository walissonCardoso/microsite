package br.com.micropensamento.microsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micropensamento.microsite.model.Texto;

public interface TextoRepository extends JpaRepository<Texto, Long> {
    
}
