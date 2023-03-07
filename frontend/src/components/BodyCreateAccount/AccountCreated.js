import { useNavigate } from "react-router-dom";
import Button from "../Button";
import "./AccountCreated.css"

const AccountCreated = (props) =>{
    
    const navigate = useNavigate();
    
    const go_home = (event) => {
        event.preventDefault()
        navigate('/')
    }
    
    return (
        <section className="section">
            <h2>Bem-vindo à bordo {props.nome}!</h2>
            <img src="./images/like.png"/>
            <p>
                Sua conta foi criada com sucesso. Você precisa apenas validá-la em até 24 horas utilizando o link que foi enviado para o e-mail cadastrado.
            </p>
            <form onSubmit={go_home}>
                <div className="home_button">
                    <Button label="Página inicial"/>
                </div>
            </form>
        </section>
    )
}

export default AccountCreated;