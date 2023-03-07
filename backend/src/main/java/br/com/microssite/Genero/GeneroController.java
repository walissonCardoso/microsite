package br.com.microssite.Genero;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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