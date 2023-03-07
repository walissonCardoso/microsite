package br.com.microssite.Autor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.microssite.Enum.SexoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_AUTOR")
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "PSEUDONIMO")
    private String pseudonimo;
    
    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;
    
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "HASH_SENHA")
    private String hashSenha;
    
    @Column(name = "CONTA_VALIDADA")
    private Boolean contaValidada = false;
    
    @Column(name = "AUTOR_BLOQUEADO")
    private Boolean autorBloqueado = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return hashSenha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
