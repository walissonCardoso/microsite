package br.com.micropensamento.microsite.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.micropensamento.microsite.dto.GeneroDto;
import br.com.micropensamento.microsite.model.Genero;
import br.com.micropensamento.microsite.repository.GeneroRepository;


@RestController
public class GeneroController {
    
    @Autowired
    private GeneroRepository generoRepository;
    
    @RequestMapping("/generos")
    public List<GeneroDto> lista() {
        
        List<Genero> generos = generoRepository.findAll();
        List<GeneroDto> generosDto = generos.stream().map(GeneroDto::new).collect(Collectors.toList());
        
        return generosDto;
    }
}