package br.com.micropensamento.microsite.dto;

import java.time.LocalDate;

import br.com.micropensamento.microsite.model.Autor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorDto {
    
    private Long id;
    private String nome;
    private String pseudonimo;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private Boolean contaValidada;
    private Boolean autorBloqueado;
    
    public AutorDto(Autor autor){
        
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.pseudonimo = autor.getPseudonimo();
        this.dataNascimento = autor.getDataNascimento();
        this.sexo = autor.getSexo().toString();
        this.email = autor.getEmail();
        this.contaValidada = autor.getContaValidada();
        this.autorBloqueado = autor.getAutorBloqueado();
    
    }
}
