package br.com.microssite.TextoGenero;

import br.com.microssite.Genero.Genero;
import br.com.microssite.Texto.Texto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
