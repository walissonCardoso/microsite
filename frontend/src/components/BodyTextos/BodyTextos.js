import Texto from "../Texto"
import ListaPaginas from "../ListaPaginas"
import "./BodyTextos.css"
import { useEffect, useState } from "react"

const BodyTextos = () => {

    const [textos, setTextos] = useState(null)
    
    useEffect(() => {
        fetch("/textos")
        .then(response => response.json())
        .then(response => setTextos(response))
    }, [])

    return (
        <section className="body_textos">
            {textos &&
                <div>{
                    textos.map(texto =>
                        <Texto
                            key={"texto.autor" + texto.titulo}
                            titulo={texto.titulo}
                            corpo={texto.corpo}
                            autor={texto.pseudonimoAutor}
                            data={texto.dataCriacao}
                            generos={texto.generos}
                        />)
                }</div>
            }
            <ListaPaginas/>
        </section>
    )
}

export default BodyTextos