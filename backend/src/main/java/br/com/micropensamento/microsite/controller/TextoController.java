package br.com.micropensamento.microsite.controller;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<TextoDto> lista() {
        
        List<Texto> textos = textoRepository.findAll();
        List<TextoDto> textosDto = new ArrayList<TextoDto>();
        
        for(Texto texto : textos){
            TextoDto textoDto = new TextoDto(texto);
            
            List<TextoGenero> generoList = textoGeneroRepository.findAllByTexto(texto);
            List<Genero> generos = new ArrayList<Genero>();
            
            for(TextoGenero genero : generoList){
                generos.add(genero.getGenero());
            }
            
            textoDto.setGeneros(generos);
            textosDto.add(textoDto);
        }
        
        return textosDto;
    }
    
    @PostMapping
    public ResponseEntity<TextoDto> submeter(@RequestBody TextoForm textoForm, UriComponentsBuilder uriBuilder) {
    
        Texto texto = textoForm.converterParaTexto(autorRepository);
        textoRepository.save(texto);
        
        textoForm.getGeneros().forEach((generoId) -> {
            saveTextoGenero(generoId, texto);
        });
                
        URI uri = uriBuilder.path("/textos/{id}").buildAndExpand(texto.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new TextoDto(texto));
        
    }
    
    private void saveTextoGenero(Long generoId, Texto texto){
    
        Genero genero = generoRepository.findById(generoId).get();
        TextoGenero textoGenero = new TextoGenero();
        
        textoGenero.setTexto(texto);
        textoGenero.setGenero(genero);
        textoGeneroRepository.save(textoGenero);
    
    }
}
