package br.com.microssite.Autor;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping("/info")
    public AutorDto recuperarAutores() {
        
        Autor autor = autorRepository.getReferenceById(-1L);
        AutorDto autorDto = new AutorDto(autor);
        
        return autorDto;
    }
    
    @GetMapping("/verifyemail/{email}")
    public boolean emailExists(@PathVariable String email) {
        Autor autor = autorRepository.findByEmail(email);
        var autorExists = autor != null;
        
        return autorExists;
    }
    
    @GetMapping("/verifypseudonimo/{pseudonimo}")
    public boolean pseudonimoExists(@PathVariable String pseudonimo) {
        Autor autor = autorRepository.findByPseudonimo(pseudonimo);
        var autorExists = autor != null;
        
        return autorExists;
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> criarAutor(@RequestBody AutorForm autorForm, UriComponentsBuilder uriBuilder) {
    
        Autor autor = autorForm.converterParaAutor();
        autorRepository.save(autor);
                
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(autor));
        
    }

}
