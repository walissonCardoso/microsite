package br.com.micropensamento.microsite.controller.form;

import java.time.LocalDate;

import br.com.micropensamento.microsite.model.Autor;
import br.com.micropensamento.microsite.model.enums.SexoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorForm {

    private String nome;
    private String pseudonimo;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String senha;
    
    public Autor converterParaAutor() {
    
        Autor autor = new Autor();
        
        autor.setNome(this.nome);
        autor.setPseudonimo(this.pseudonimo);
        autor.setDataNascimento(this.dataNascimento);
        autor.getSexo();
        autor.setSexo(SexoEnum.valueOf(this.sexo));
        autor.setEmail(this.email);
        autor.setHashSenha(this.senha);
        
        return autor;
        
    }
}
