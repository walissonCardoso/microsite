package br.com.micropensamento.microsite.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.micropensamento.microsite.model.enums.StatusTextoEnum;
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
    
    @Column(name = "TITULO")
    private String titulo;
    
    @Column(name = "CORPO")
    private String corpo;
    
    @Column(name = "DT_CRIACAO")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "AUTOR_ID", nullable = false)
    private Autor autor;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusTextoEnum statusTexto = StatusTextoEnum.AGUARDANDO_APROVACAO;
}
