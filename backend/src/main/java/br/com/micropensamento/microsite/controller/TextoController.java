package br.com.micropensamento.microsite.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.micropensamento.microsite.controller.form.TextoForm;
import br.com.micropensamento.microsite.dto.TextoDto;
import br.com.micropensamento.microsite.model.Genero;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.TextoGenero;
import br.com.micropensamento.microsite.model.enums.StatusTextoEnum;
import br.com.micropensamento.microsite.repository.AutorRepository;
import br.com.micropensamento.microsite.repository.GeneroRepository;
import br.com.micropensamento.microsite.repository.TextoGeneroRepository;
import br.com.micropensamento.microsite.repository.TextoRepository;

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
    public List<TextoDto> listarTextos() {
        
        List<Texto> textos = textoRepository.findAllByStatusTexto(StatusTextoEnum.APROVADO);
        
        List<TextoDto> textosDto = textos.stream()
                                         .map(texto -> new TextoDto(texto, autorRepository, textoGeneroRepository))
                                         .collect(Collectors.toList());
        
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
