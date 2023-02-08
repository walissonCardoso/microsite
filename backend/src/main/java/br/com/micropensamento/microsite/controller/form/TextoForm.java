package br.com.micropensamento.microsite.controller.form;

import java.util.List;

import br.com.micropensamento.microsite.model.Texto;
import br.com.micropensamento.microsite.repository.AutorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoForm {

    private String titulo;
    private String corpo;
    private Long autor_id;
    private List<Long> generos;
    
    public Texto converterParaTexto(AutorRepository autorRepository) {
    
        Texto texto = new Texto();
        
        texto.setTitulo(this.titulo);
        texto.setCorpo(this.corpo);
        texto.setAutor(
            autorRepository.findById(this.autor_id).get()
        );
        
        return texto;
    }
}
