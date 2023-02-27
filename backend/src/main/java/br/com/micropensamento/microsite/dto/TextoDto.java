package br.com.micropensamento.microsite.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.micropensamento.microsite.model.Genero;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.TextoGenero;
import br.com.micropensamento.microsite.repository.AutorRepository;
import br.com.micropensamento.microsite.repository.TextoGeneroRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoDto {
    
    private Long id;
    private String titulo;
    private String corpo;
    private String dataCriacao;
    private Long autorId;
    private String pseudonimoAutor;
    private List<String> generos;
    
    public TextoDto(Texto texto) {
    
        this.id = texto.getId();
        this.titulo = texto.getTitulo();
        this.corpo = texto.getCorpo();
        this.autorId = texto.getAutor().getId();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataCriacao = texto.getDataCriacao().format(formatter);
        
    }
    
    public TextoDto(Texto texto,
                    AutorRepository autorRepository,
                    TextoGeneroRepository textoGeneroRepository) {
        this(texto);
        
        this.pseudonimoAutor = autorRepository.getReferenceById(this.autorId).getPseudonimo();
        
        List<TextoGenero> generosTexto = textoGeneroRepository.findAllByTexto(texto);
        List<String> generosString = new ArrayList<String>();
        
        for(TextoGenero textoGenero : generosTexto){
            Genero genero = textoGenero.getGenero();
            String nome = genero.getNome();
        
            generosString.add(nome);
        }
        
        this.setGeneros(generosString);
    }
}
