package br.com.microssite.Texto;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.microssite.Autor.AutorRepository;
import br.com.microssite.Enum.StatusTextoEnum;
import br.com.microssite.Genero.Genero;
import br.com.microssite.Genero.GeneroRepository;
import br.com.microssite.TextoGenero.TextoGenero;
import br.com.microssite.TextoGenero.TextoGeneroRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/textos")
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
    public Page<TextoDto> listarTextos(@PageableDefault(sort="dataCriacao", direction=Direction.DESC, page=0, size=10) Pageable paging) {
        
        Page<Texto> textos = textoRepository.findAllByStatusTexto(StatusTextoEnum.APROVADO, paging);
                                     
        Page<TextoDto> textosDto = textos.map(texto -> new TextoDto(texto, autorRepository, textoGeneroRepository));
        
        return textosDto;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TextoDto> recuperarTexto(@PathVariable String id) {

        Long idNumber = 1L;
        
        if(id == null || id == "")
            return null;
        else
            idNumber = Long.parseLong(id);
        
        Optional<Texto> texto = textoRepository.findById(idNumber);
        if (texto.isPresent())
            return ResponseEntity.ok(new TextoDto(texto.get(),
                                                  autorRepository,
                                                  textoGeneroRepository
                                                 )
                                    );
        
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTextos", allEntries = true)
    public ResponseEntity<TextoDto> submeterTexto(@RequestBody TextoForm textoForm, UriComponentsBuilder uriBuilder) {
    
        Texto texto = textoForm.converterParaTexto(autorRepository);
        textoRepository.save(texto);
        
        textoForm.getGeneros().forEach((generoId) -> {
            saveTextoGenero(generoId, texto);
        });
                
        URI uri = uriBuilder.path("/textos/{id}").buildAndExpand(texto.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new TextoDto(texto));
        
    }
    
    private void saveTextoGenero(Long generoId, Texto texto){
    
        Genero genero = generoRepository.getReferenceById(generoId);
        TextoGenero textoGenero = new TextoGenero();
        
        textoGenero.setTexto(texto);
        textoGenero.setGenero(genero);
        textoGeneroRepository.save(textoGenero);
    
    }
}