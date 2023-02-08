package br.com.micropensamento.microsite.dto;

import java.time.LocalDateTime;

import br.com.micropensamento.microsite.model.Texto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoResumidoDto {

    private Long id;
    private String titulo;
    private LocalDateTime dataCriacao;
    private Long autor_id;
    
    public TextoResumidoDto(Texto texto) {
    
        this.id = texto.getId();
        this.titulo = texto.getTitulo();
        this.dataCriacao = texto.getDataCriacao();
        this.autor_id = texto.getAutor().getId();
        
    }

}
