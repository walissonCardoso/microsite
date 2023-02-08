package br.com.micropensamento.microsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.TextoGenero;

public interface TextoGeneroRepository extends JpaRepository<TextoGenero, Long>{

    List<TextoGenero> findAllByTexto(Texto texto);
    
}
