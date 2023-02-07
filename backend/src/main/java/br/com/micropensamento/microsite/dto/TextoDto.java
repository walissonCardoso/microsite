package br.com.micropensamento.microsite.dto;

import java.time.LocalDate;

import br.com.micropensamento.microsite.model.Autor;
import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.model.enums.StatusTextoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoDto {
    
    private Long id;
    private String titulo;
    private String corpo;
    private LocalDate dataCriacao;
    private Long autor_id;
    private StatusTextoEnum statusTexto;
    
    public TextoDto(Texto texto) {
    
        this.id = texto.getId();
        this.titulo = texto.getTitulo();
        this.corpo = texto.getCorpo();
        this.dataCriacao = texto.getDataCriacao();
        this.autor_id = texto.getAutor().getId();
        this.statusTexto = texto.getStatusTexto();
    
    }
}
