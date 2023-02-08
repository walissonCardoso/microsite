package br.com.micropensamento.microsite.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.micropensamento.microsite.controller.form.AutorForm;
import br.com.micropensamento.microsite.dto.AutorDto;
import br.com.micropensamento.microsite.model.Autor;
import br.com.micropensamento.microsite.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping
    public AutorDto recuperarAutores(String id) {
        
        Long idNumber = 1L;
        
        if(id == null || id == "")
            return null;
        else
            idNumber = Long.parseLong(id);
        
        Autor autor = autorRepository.findById(idNumber).get();
        AutorDto autorDto = new AutorDto(autor);
        
        return autorDto;
    }
    
    @PostMapping
    public ResponseEntity<AutorDto> submeterTexto(@RequestBody AutorForm autorForm, UriComponentsBuilder uriBuilder) {
    
        Autor autor = autorForm.converterParaAutor();
        autorRepository.save(autor);
                
        URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(autor));
        
    }
}
