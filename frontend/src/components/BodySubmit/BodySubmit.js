import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import Button from "../Button"
import GridGeneros from "./GridGeneros"
import "./BodySubmit.css"
import axios from "axios"

const BodySubmit = (props) => {
    
    const [titulo, setTitulo] = useState('')
    const [corpo, setCorpo] = useState('')
    const [generos, setGeneros] = useState([])
    const [listaGeneros, setListaGeneros] = useState(null)
    
    const navigate = useNavigate();
    
    useEffect(() => {
        if(!props.loggedUser) {
            alert("Você precisa estar logado para enviar um texto")
            navigate("/login")
        }
    }, [props.loggedUser, navigate]);
    
    useEffect(() => {        
        axios.get("/api/generos")
        .then(response => setListaGeneros(response.data))
    }, [])

    const aoSubmeter = (evento) => {
        evento.preventDefault()
        
        if(props.loggedUser){
            const texto = {
                "titulo": titulo,
                "corpo": corpo,
                "generos": generos
            }
            
            const config = {
                headers: {
                    'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
                    'Content-Type': 'application/json'
                }
            };
            
            axios.post("/api/textos", texto, config)
            .then(() => {
                props.setTextCounter(props.textCounter + 1)
                alert("Texto enviado com sucesso! Obrigado pelo envio.")
            })
        }else{
            alert("Sua sessão expirou! Por favor, faça o login novamente.")
        }
        
        navigate('/')
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
                        maxLength={60}
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
                        maxLength={600}
                        onChange={evento => setCorpo(evento.target.value)}
                    ></textarea>
                </div>
                <div>
                    <label>Gêneros (selecione entre um e três gêneros)</label>
                    {
                        listaGeneros &&
                        <GridGeneros
                            listaGeneros={listaGeneros}
                            setGeneros={setGeneros}
                            generos={generos}
                        />
                    }
                </div>
                <div className="center_item">
                    <Button label="Submeter" disabled={generos.length < 1 || generos.length > 3}/>
                </div>
            </form>
        </section>
    )
}

export default BodySubmit