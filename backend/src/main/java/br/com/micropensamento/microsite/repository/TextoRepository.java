package br.com.micropensamento.microsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.enums.StatusTextoEnum;

public interface TextoRepository extends JpaRepository<Texto, Long> {
    
    List<Texto> findAllByStatusTexto(StatusTextoEnum statusTextoEnum);
    
}
