package br.com.microssite.Texto;

import java.util.List;

import br.com.microssite.Autor.Autor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextoForm {

    private String titulo;
    private String corpo;
    private List<Long> generos;
    
    public Texto converterParaTexto(Autor autor) {
    
        Texto texto = new Texto();
        
        texto.setTitulo(this.titulo);
        texto.setCorpo(this.corpo);
        texto.setAutor(autor);
        
        return texto;
    }
}
