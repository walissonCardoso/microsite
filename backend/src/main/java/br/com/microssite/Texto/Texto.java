package br.com.microssite.Texto;

import java.time.LocalDateTime;

import br.com.microssite.Autor.Autor;
import br.com.microssite.Enum.StatusTextoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_TEXTO")
public class Texto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "TITULO", length = 60)
    private String titulo;
    
    @Column(name = "CORPO", length = 600)
    private String corpo;
    
    @Column(name = "DT_CRIACAO")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "AUTOR_ID", nullable = false)
    private Autor autor;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusTextoEnum statusTexto = StatusTextoEnum.APROVADO;
    
}