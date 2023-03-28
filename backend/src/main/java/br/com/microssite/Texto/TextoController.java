package br.com.microssite.Texto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.microssite.Autor.Autor;
import br.com.microssite.Autor.AutorRepository;
import br.com.microssite.Enum.StatusTextoEnum;
import br.com.microssite.Genero.Genero;
import br.com.microssite.Genero.GeneroRepository;
import br.com.microssite.TextoGenero.TextoGenero;
import br.com.microssite.TextoGenero.TextoGeneroRepository;
import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/textos")
public class TextoController {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private TextoRepository textoRepository;
    
    @Autowired
    private GeneroRepository generoRepository; 
    
    @Autowired
    private TextoGeneroRepository textoGeneroRepository;
    
    @GetMapping
    @Cacheable(value = "listaDeTextos")
    public ResponseEntity<Page<TextoDto>> listarTextos(@PageableDefault(sort="dataCriacao", direction=Direction.DESC, page=0, size=10) Pageable paging) {
        
        Page<Texto> textos = textoRepository.findAllByStatusTexto(StatusTextoEnum.APROVADO, paging);
                                     
        Page<TextoDto> textosDto = textos.map(texto -> new TextoDto(texto, autorRepository, textoGeneroRepository));
        
        return ResponseEntity.ok(textosDto);
    }
    
    @GetMapping("/list/by_autor")
    public ResponseEntity<Page<TextoDto>> listarTextosDeAutor(@AuthenticationPrincipal Autor autor, @PageableDefault(sort="dataCriacao", direction=Direction.DESC, page=0, size=10) Pageable paging) {
        
        Page<Texto> textos = textoRepository.findAllByAutor(autor, paging);
                                     
        Page<TextoDto> textosDto = textos.map(texto -> new TextoDto(texto, autorRepository, textoGeneroRepository));
        
        return ResponseEntity.ok(textosDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TextoDto> recuperarTexto(@PathVariable Long id) {
        
        Texto texto = textoRepository.getReferenceById(id);
        
        if(texto != null)
            return ResponseEntity.ok(new TextoDto(texto, autorRepository, textoGeneroRepository));
        else
            return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTextos", allEntries = true)
    public ResponseEntity<TextoDto> submeterTexto(@RequestBody TextoForm textoForm, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Autor autor) {
    
        Texto texto = textoForm.converterParaTexto(autor);
        textoRepository.save(texto);
        
        System.out.println(autor);
        
        textoForm.getGeneros().forEach((generoId) -> {
            saveTextoGenero(generoId, texto);
        });
                
        URI uri = uriBuilder.path("/textos/{id}").buildAndExpand(texto.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new TextoDto(texto, autorRepository, textoGeneroRepository));        
    }
    
    @DeleteMapping
    @Transactional
    @CacheEvict(value = "listaDeTextos", allEntries = true)
    public void delete(@RequestParam Long id, @AuthenticationPrincipal Autor loggeAutor) {
        Texto texto = textoRepository.getReferenceById(id);
        if(texto.getAutor().getId() != loggeAutor.getId())
            return;
        
        textoGeneroRepository.deleteAllByTexto(texto);
        textoRepository.deleteById(texto.getId());
    }
    
    private void saveTextoGenero(Long generoId, Texto texto){
    
        Genero genero = generoRepository.getReferenceById(generoId);
        TextoGenero textoGenero = new TextoGenero();
        
        textoGenero.setTexto(texto);
        textoGenero.setGenero(genero);
        textoGeneroRepository.save(textoGenero);
    
    }
}