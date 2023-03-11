import "./BodyCreateAccount.css"

import { useState } from "react";
import Button from "../Button";
import AccountCreated from "./AccountCreated.js"
import ErrorPost from "./ErrorPost.js"
import axios from "axios";

const BodyCreateAccount = (props) => {

    const [state, setState] = useState('creating')
    
    const [pseudonimo, setPseudonimo] = useState('')
    const [nomeCompleto, setNomeCompleto] = useState('')
    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')
    const [senhaRepetida, setSenhaRepetida] = useState('')
    const [aceitaTermos, setAceitaTermos] = useState(false)
    
    const aoSubmeter = (event) => {
        event.preventDefault()
        
        const autor = {
            "nome": nomeCompleto,
            "pseudonimo": pseudonimo,
            "email": email,
            "senha": senha
        }
                
        axios.post('/api/autor', autor)
            .then(() => {
                setState("done")
                
                const credentials = {
                    "email": email,
                    "senha": senha
                }
                
                axios.post("/api/login", credentials)
                    .then(response => {
                        sessionStorage.setItem('token', response.data.token)
                        props.setLoggedUser(true)
                    })
            
            })
            .catch(() => setState("error"))
    }
    
    const validadePseudonimo = (event) => {
        axios.get("/api/autor/verifypseudonimo/" + event.target.value)
        .then(response => {
            if(response.data === "existe")
                event.target.setCustomValidity("Ops, este pseudônimo já está sendo usado")
            else
                event.target.setCustomValidity("")
        })
    }
    
    const validateEmail = (event) => {
        if(!event.target.value.match(/^[\w-.]+@([\w-]+.)+[\w-]{2,4}$/g)){
            event.target.setCustomValidity("O e-mail inserido não é válido")
            return
        }else{
            event.target.setCustomValidity("")
        }
        
        axios.get("/api/autor/verifyemail/" + event.target.value)
        .then(response => {
            if(response.data === "existe")
                event.target.setCustomValidity("Uma conta já está usando este endereço de e-mail")
            else
                event.target.setCustomValidity("")
        })
    }
    
    const validateRepeatedPassword = (event) => {
        if(senha !== event.target.value)
            event.target.setCustomValidity("As senhas não coincidem")
        else
            event.target.setCustomValidity("")    
    }
    
    return(
        <section className="body_create_account">
            {
                state === "creating" &&
                <form onSubmit={aoSubmeter}>
                    <h2>Crie sua conta abaixo</h2>
                    <div>
                        <label>
                            Pseudônimo (nome que aparecerá para outros usuários)
                        </label>
                        <input
                            type="text"
                            placeholder="ex: loudy cloud"
                            minLength={3}
                            maxLength={40}
                            required={true}
                            value={pseudonimo}
                            onChange={(event) => setPseudonimo(event.target.value)}
                            onBlur={(event) => validadePseudonimo(event)}
                        />
                    </div>
                    <div>
                        <label>
                            Nome completo
                        </label>
                        <input
                            placeholder="ex: Autor Inovador"
                            minLength={3}
                            maxLength={256}
                            required={true}
                            value={nomeCompleto}
                            onChange={(event) => setNomeCompleto(event.target.value)}
                        />
                    </div>
                    <div>
                        <label>
                            E-mail
                        </label>
                        <input
                            type="null"
                            placeholder="ex: autor.inovador@mail.com"
                            required={true}
                            value={email}
                            maxLength={256}
                            onChange={(event) => setEmail(event.target.value)}
                            onBlur={(event) => validateEmail(event)}
                        />
                    </div>
                    <div>
                        <label>
                            Senha
                        </label>
                        <input
                            type="password"
                            placeholder="********"
                            minLength={8}
                            maxLength={128}
                            required={true}
                            value={senha}
                            onChange={(event) => setSenha(event.target.value)}
                        />
                    </div>
                    <div>
                        <label>
                            Repita a senha
                        </label>
                        <input
                            type="password"
                            placeholder="********"
                            minLength={8}
                            maxLength={128}
                            required={true}
                            value={senhaRepetida}
                            onChange={(event) => setSenhaRepetida(event.target.value)}
                            onBlur={(event) => validateRepeatedPassword(event)}
                        />
                    </div>
                    <div>
                        <input
                            type="checkbox"
                            id="aceitaTermos"
                            className="aceita_termos"
                            onChange={() => setAceitaTermos(!aceitaTermos)}
                        />
                        <label htmlFor="aceitaTermos">
                            Concordo com os <a href="#">termos de uso e privacidade</a>
                        </label>
                    </div>
                    <div className="center_item">
                        <Button label="Submeter" disabled={!aceitaTermos}/>
                        
                    </div>
                </form>
            }
            {
                state === "done" &&
                <AccountCreated nome={pseudonimo}/>
            }
            {
                state === "error" &&
                <ErrorPost/>
            }
        </section>
    )
}

export default BodyCreateAccount;