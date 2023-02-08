package br.com.micropensamento.microsite.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.micropensamento.microsite.model.Genero;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.TextoGenero;
import br.com.micropensamento.microsite.model.enums.StatusTextoEnum;
import br.com.micropensamento.microsite.repository.TextoGeneroRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoDto {
    
    private Long id;
    private String titulo;
    private String corpo;
    private LocalDateTime dataCriacao;
    private Long autor_id;
    private StatusTextoEnum statusTexto;
    private List<Genero> generos;
    
    public TextoDto(Texto texto) {
    
        this.id = texto.getId();
        this.titulo = texto.getTitulo();
        this.corpo = texto.getCorpo();
        this.dataCriacao = texto.getDataCriacao();
        this.autor_id = texto.getAutor().getId();
        this.statusTexto = texto.getStatusTexto();
        
    }
    
    public TextoDto(Texto texto, TextoGeneroRepository textoGeneroRepository) {
        
        this(texto);
            
        List<TextoGenero> generoList = textoGeneroRepository.findAllByTexto(texto);
        List<Genero> generos = new ArrayList<Genero>();
        
        for(TextoGenero genero : generoList){
            generos.add(genero.getGenero());
        }
        
        this.setGeneros(generos);
    }
    
}
