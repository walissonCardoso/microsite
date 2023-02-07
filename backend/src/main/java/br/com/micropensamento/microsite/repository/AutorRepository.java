package br.com.micropensamento.microsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micropensamento.microsite.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
}
