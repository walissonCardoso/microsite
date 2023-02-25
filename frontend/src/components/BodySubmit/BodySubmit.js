import { useEffect, useState } from "react"
import Button from "../Button"
import GridGeneros from "./GridGeneros"

import "./BodySubmit.css"

const BodySubmit = () => {

    const [titulo, setTitulo] = useState('')
    const [corpo, setCorpo] = useState('')
    const [generos, setGeneros] = useState(null)
    
    useEffect(() => {
        fetch("/generos")
        .then(response => response.json())
        .then((usefulData) => setGeneros(usefulData))
    }, [])

    const aoSubmeter = (evento) => {
        evento.preventDefault()
        
        const texto = {
            "titulo": titulo,
            "corpo": corpo,
            "autor_id": -2,
            "generos": [-1,-2]
        }
        
        fetch("/textos", {
            method: 'POST',
            body: JSON.stringify(texto),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            }
        })
    }
    
    return (
        <section className="body_submit">
            <form onSubmit={aoSubmeter}>
                <h2>Envie o seu texto</h2>
                <div>
                    <label>
                        Titulo (máximo de 60 caracteres)
                    </label>
                    <textarea
                        rows="1"
                        placeholder="ex: Uma Noite Fria"
                        required={true}
                        value={titulo}
                        onChange={evento => setTitulo(evento.target.value)}
                    ></textarea>
                </div>
                <div>
                    <label>
                        Corpo (máximo de 600 caracteres)
                    </label>
                    <textarea
                        rows="5"
                        placeholder="ex: Era uma noite realmente muito fria quando dei por mim..."
                        required={true}
                        value={corpo}
                        onChange={evento => setCorpo(evento.target.value)}
                    ></textarea>
                </div>
                <div>
                    <label>Gêneros (selecione entre um e três gêneros)</label>
                    {generos && <GridGeneros generos={generos}/>}
                </div>
                <div className="center_item">
                    <Button label="Submeter"/>
                </div>
            </form>
        </section>
    )
}

export default BodySubmit