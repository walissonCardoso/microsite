package br.com.micropensamento.microsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micropensamento.microsite.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    
}
