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
                            corpo="Aqui vai ser o corpo do texto"
                            autor="Autor do texto"
                            data={texto.dataCriacao}
                            generos={"Generos textuais"}
                        />)
                }</div>
            }
            <ListaPaginas/>
        </section>
    )
}

export default BodyTextos