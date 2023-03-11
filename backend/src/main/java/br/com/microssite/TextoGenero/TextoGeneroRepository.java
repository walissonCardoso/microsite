package br.com.microssite.TextoGenero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.microssite.Texto.Texto;

public interface TextoGeneroRepository extends JpaRepository<TextoGenero, Long>{

    List<TextoGenero> findAllByTexto(Texto texto);

    void deleteAllByTexto(Texto texto);
    
}