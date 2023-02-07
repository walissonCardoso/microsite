package br.com.micropensamento.microsite.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.micropensamento.microsite.dto.AutorDto;
import br.com.micropensamento.microsite.model.Autor;
import br.com.micropensamento.microsite.repository.AutorRepository;

@RestController
public class AutorController {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @RequestMapping("/autores")
    public List<AutorDto> lista() {
        
        List<Autor> autores = autorRepository.findAll();
        List<AutorDto> autoresDto = autores.stream().map(AutorDto::new).collect(Collectors.toList());
        
        return autoresDto;
    }
}
