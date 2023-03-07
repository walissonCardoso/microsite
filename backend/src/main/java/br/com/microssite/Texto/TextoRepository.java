package br.com.microssite.Texto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.microssite.Enum.StatusTextoEnum;

public interface TextoRepository extends JpaRepository<Texto, Long> {
    
    Page<Texto> findAllByStatusTexto(StatusTextoEnum statusTextoEnum, Pageable paging);
    
}
