package br.com.micropensamento.microsite.dto;

import br.com.micropensamento.microsite.model.Genero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneroDto {
    
    private Long id;
    private String nome;
    
    public GeneroDto(Genero genero) {
        
        this.id = genero.getId();
        this.nome = genero.getNome();
        
    }
    
}
