package br.com.microssite.Texto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.microssite.Autor.Autor;
import br.com.microssite.Enum.StatusTextoEnum;

public interface TextoRepository extends JpaRepository<Texto, Long> {
    
    Page<Texto> findAllByStatusTexto(StatusTextoEnum statusTextoEnum, Pageable paging);
    
    Page<Texto> findAllByAutor(Autor autor, Pageable paging);

    List<Texto> findAllByAutor(Autor autor);    
}
