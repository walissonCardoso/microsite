package br.com.micropensamento.microsite.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.micropensamento.microsite.dto.TextoDto;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.repository.TextoGeneroRepository;
import br.com.micropensamento.microsite.repository.TextoRepository;

@RestController
@RequestMapping("/listaTextos")
public class ListarTextosController {
    
    @Autowired
    private TextoRepository textoRepository;
    
    @Autowired
    private TextoGeneroRepository textoGeneroRepository;
    
    private int textosPorPagina = 2;
    
    @GetMapping
    public List<TextoDto> recuperarTextos(String page) {
        
        List<Texto> textos = this.getTextos(page);
        
        List<TextoDto> textosDto = new ArrayList<TextoDto>();
        for(Texto texto : textos){
            TextoDto textoDto = new TextoDto(texto, textoGeneroRepository);
            textosDto.add(textoDto);
        }
        
        return textosDto;
    }
    
    
    private List<Texto> getTextos(String page) {
    
        int pageNumber = 0;
        
        if(page != null && page != "")
            pageNumber = Integer.parseInt(page);
        
        List<Texto> textos = textoRepository.findAll();
        Collections.reverse(textos);
        
        int start = pageNumber * this.textosPorPagina;
        int end = (pageNumber+1) * this.textosPorPagina;
        
        if(start > textos.size())
            textos = new ArrayList<>();
        else
            textos = textos.subList(start, end > textos.size() ? textos.size() : end);
        
        return textos;
    
    }
    
}
