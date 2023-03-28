package br.com.microssite.Autor;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.microssite.Texto.Texto;
import br.com.microssite.Texto.TextoRepository;
import br.com.microssite.TextoGenero.TextoGeneroRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private TextoRepository textoRepository;
    
    @Autowired
    private TextoGeneroRepository textoGeneroRepository;
    
    @GetMapping("/info")
    public ResponseEntity<AutorDto> recuperarAutores(@AuthenticationPrincipal Autor autor) {
        
        Autor loggedAutor = autorRepository.getReferenceById(autor.getId());
        AutorDto autorDto = new AutorDto(loggedAutor);
        
        return ResponseEntity.ok(autorDto);
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> criarAutor(@RequestBody AutorForm autorForm, UriComponentsBuilder uriBuilder) {
    
        Autor autor = autorForm.converterParaAutor();
        autorRepository.save(autor);
                
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(autor));
        
    }
    
    @PatchMapping
    @Transactional
    public ResponseEntity<AutorDto> update(@RequestBody AutorUpdateInfo autorInfo, @AuthenticationPrincipal Autor loggeAutor) {
        var autor = autorRepository.getReferenceById(loggeAutor.getId());
        autor.updateData(autorInfo);
        
        return ResponseEntity.ok(new AutorDto(autor));
    }
    
    @DeleteMapping
    @Transactional
    @CacheEvict(value = "listaDeTextos", allEntries = true)
    public ResponseEntity<Object> delete(@RequestParam Long id, @AuthenticationPrincipal Autor loggeAutor) {
        Autor autor = autorRepository.getReferenceById(id);
        if(autor.getId() != loggeAutor.getId())
            return ResponseEntity.badRequest().build();
        
        List<Texto> textos = textoRepository.findAllByAutor(autor);
        for(Texto texto : textos){
            textoGeneroRepository.deleteAllByTexto(texto);
            textoRepository.deleteById(texto.getId());
        }
        
        autorRepository.deleteById(autor.getId());
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/verifyemail/{email}")
    public String emailExists(@PathVariable String email) {
        Autor autor = autorRepository.findByEmail(email);
        
        if(autor != null)
            return "existe";
        else
            return "livre";
    }
    
    @GetMapping("/verifypseudonimo/{pseudonimo}")
    public String pseudonimoExists(@PathVariable String pseudonimo) {
        Autor autor = autorRepository.findByPseudonimo(pseudonimo);
        
        if(autor != null)
            return "existe";
        else
            return "livre";
    }
}
