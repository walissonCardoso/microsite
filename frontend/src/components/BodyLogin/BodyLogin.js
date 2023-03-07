import { useState } from "react";
import { useNavigate } from "react-router-dom";
import http from "../../http";
import Button from "../Button";
import "./BodyLogin.css"

const BodyLogin = (props) => {

    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')
    const [whongPassword, setWrongPassword] = useState(false)
    
    const navigate = useNavigate();
    
    const aoSubmeter = (event) => {
        event.preventDefault()
        
        const credentials = {
            "email": email,
            "senha": senha
        }
        
        http.post("/login", credentials)
            .then(response => {
                setWrongPassword(false)
                sessionStorage.setItem('token', response.data.token)
                props.setLoggedUser(true)
                
                navigate('/')
            })
            .catch(() => {
                setWrongPassword(true)
                alert('E-mail ou senha incorretos')
            })
    }

    return (
        <section className="body_login">
            <form onSubmit={aoSubmeter}>
                <h2>Entre com seu e-mail e senha</h2>
                
                <div className="center_item">
                    <label htmlFor="login">
                        login
                    </label>
                    <input
                        id="login"
                        type="text"
                        placeholder="exemplo@mail.com"
                        minLength={3}
                        required={true}
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                    />
                </div>
                
                <div className="center_item">
                    <label htmlFor="senha">
                        senha
                    </label>
                    <input
                        id="senha"
                        type="password"
                        placeholder="********"
                        minLength={8}
                        required={true}
                        value={senha}
                        onChange={(event) => setSenha(event.target.value)}
                    />
                </div>
                
                <div className="center_item">
                    <Button label="Entrar"/>
                </div>
                
                <div className="center_item">
                    {
                        whongPassword &&
                        <label className="create_account">
                            Esqueceu sua senha?<br></br>
                            Recupere-a <a href="/create_account">clicando aqui</a>
                        </label>
                    } 
                    <label className="create_account">
                        Não possui uma conta?<br></br>
                        Crie uma <a href="/create_account">clicando aqui</a>
                    </label> 
                </div>
            </form>
        </section>
    )
}

export default BodyLogin;