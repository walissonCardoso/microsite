package br.com.micropensamento.microsite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_TEXTO_GENERO")
public class TextoGenero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name = "TEXTO_ID", nullable = false)
    private Texto texto;
    
    @ManyToOne
    @JoinColumn(name = "GENERO_ID", nullable = false)
    private Genero genero;
}
