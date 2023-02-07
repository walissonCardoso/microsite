package br.com.micropensamento.microsite.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.micropensamento.microsite.dto.TextoDto;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.repository.TextoRepository;

@RestController
public class TextoController {
    
    @Autowired
    private TextoRepository textoRepository;
    
    @RequestMapping("/textos")
    public List<TextoDto> lista() {
        
        List<Texto> textos = textoRepository.findAll();
        List<TextoDto> textosDto = textos.stream().map(TextoDto::new).collect(Collectors.toList());
        
        return textosDto;
    }
    
}
