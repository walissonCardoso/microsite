import Texto from "../Texto"
import ListaPaginas from "../ListaPaginas"
import "./BodyTextos.css"
import { useEffect, useState } from "react"
import http from "../../http"

const BodyTextos = (props) => {

    const [textos, setTextos] = useState(null)
    const [pageNumber, setPageNumber] = useState(0)
    const [totalPages, setTotalPages] = useState(null)
    
    useEffect(() => {
        http.get("/textos?size=5&page=" + pageNumber)
        .then(response => {
            setTotalPages(response.data.totalPages);
            setPageNumber(response.data.number);
            setTextos(response.data.content);
        })
    }, [pageNumber, props.textCounter])

    return (
        <section className="body_textos">
            {textos &&
                <div>{
                    textos.map(texto =>
                        <Texto
                            key={texto.id}
                            titulo={texto.titulo}
                            corpo={texto.corpo}
                            autor={texto.pseudonimoAutor}
                            data={texto.dataCriacao}
                            generos={texto.generos}
                        />)
                }</div>
            }
            {totalPages &&
                <ListaPaginas
                    pageNumber={pageNumber}
                    totalPages={totalPages}
                    setPageNumber={setPageNumber}
                />
            }
        </section>
    )
}

export default BodyTextos