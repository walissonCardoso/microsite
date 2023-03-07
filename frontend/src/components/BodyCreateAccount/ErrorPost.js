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
            <h2>Ops, algo deu errado</h2>
            <img src="./images/thumb-down.png"/>
            <p>
                Parece que nosso servidor não conseguiu processar a sua requisição. Como este pode ser apenas um erro temporário, por favor tente novamente daqui a alguns minutos.
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