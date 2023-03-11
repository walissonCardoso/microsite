package br.com.microssite.Autor;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorForm {
    
    private String nome;
    private String pseudonimo;
    private String email;
    private String senha;
    
    public Autor converterParaAutor() {
    
        Autor autor = new Autor();
        
        autor.setNome(this.nome);
        autor.setPseudonimo(this.pseudonimo);
        autor.setEmail(this.email);
        autor.setHashSenha(hashPassword(this.senha));
        
        return autor;
        
    }
    
    private String hashPassword(String password) {
    
        int strength = 10;
        
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String hash = bCryptPasswordEncoder.encode(password);
        
        return hash;
    }
}
