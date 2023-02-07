package br.com.micropensamento.microsite.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.com.micropensamento.microsite.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_AUTOR")
public class Autor {
    
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
    
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "CONTA_VALIDADA")
    private Boolean contaValidada;
    
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "AUTOR_BLOQUEADO")
    private Boolean autorBloqueado;
    
}
