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
            <h2>Bem-vindo à bordo, {props.nome}!</h2>
            <img src="./images/like.png" alt="Resultado positivo"/>
            <p>
                Sua conta foi criada com sucesso!<br></br>Uma vez que este projeto foi criado apenas como ferramenta de aprendizado, sua conta foi automaticamente ativada. É possível que futuramente isto seja revisto e todas as contas serão excluídas sem aviso prévio.
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