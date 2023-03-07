package br.com.microssite.Autor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByEmail(String email);

    Autor findByPseudonimo(String pseudonimo);
    
}

