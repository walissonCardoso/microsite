import axios from "axios"
import { Fragment, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import Button from "../Button"
import ListaPaginas from "../ListaPaginas"
import Texto from "../Texto"
import "./MyAccount.css"

const MyAccount = (props) => {
    
    const navigate = useNavigate()
    const [userInfo, setUserInfo] = useState(null)
    const [textos, setTextos] = useState(null)
    const [pageNumber, setPageNumber] = useState(0)
    const [totalPages, setTotalPages] = useState(null)
    const [refleshText, setRefleshText] = useState(false)
    
    const logout = () => {
        props.setLoggedUser(false)
        sessionStorage.removeItem('token')
        navigate('/')
    }
    
    const deleteText = (id) => {
        const config = {
            headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` }
        };
        
        if (window.confirm("Esta ação não pode ser revertida. Tem certeza que deseja excluir o texto?") === true) {
            axios.delete("/api/textos/" + id, config)
            .then(response => {
                if(response.status === 200){
                    setRefleshText(true)
                    alert("Texto excluído com sucesso")
                }
            })
        }
    }
    
    useEffect(() => {
        if(!props.loggedUser) {
            alert("Você precisa estar logado para acessar esta página")
            navigate("/login")
        }
    }, [props.loggedUser, navigate]);

    
    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` }
        };
        
        axios.get("/api/autor/info", config)
        .then(response => setUserInfo(response.data))
        .catch(response => console.log(response.config))
    }, []);

    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` }
        };
        
        axios.get("/api/textos/list/by_autor?page=" + pageNumber, config)
        .then(response => {
            setTotalPages(response.data.totalPages);
            setPageNumber(response.data.number);
            setTextos(response.data.content);
            setRefleshText(false);
        })
    }, [pageNumber, refleshText])
    
    return (
        <Fragment>{
            userInfo && textos &&
            <div className="my_account">
                <section className="user_info">
                    <div>
                        <h2> Autor logado:</h2>
                        <Button label={"logout"} onClick={logout}/>
                    </div>
                    <p><strong>Pseudonimo</strong> <br></br> {userInfo.pseudonimo}</p>
                    <p><strong>E-mail</strong> <br></br> {userInfo.email}</p>
                    <p><strong>Nome</strong> <br></br> {userInfo.nome}</p>
                </section>
                <section className="lista_textos">
                    <h2> Textos:</h2>
                    {
                        textos.map(texto => 
                            <div className="item_texto" key={"div_" + texto.id}>
                                <Texto
                                    key={texto.id}
                                    titulo={texto.titulo + "  (" + texto.statusTexto + ")"}
                                    corpo={texto.corpo}
                                    autor={texto.pseudonimoAutor}
                                    data={texto.dataCriacao}
                                    generos={texto.generos}
                                />
                                <Button label={"excluir"} onClick={() => deleteText(texto.id)}/>
                            </div>
                        )
                    }
                </section>
                <ListaPaginas
                    pageNumber={pageNumber}
                    totalPages={totalPages}
                    setPageNumber={setPageNumber}
                />
            </div>
        }</Fragment>
    )
}

export default MyAccount;