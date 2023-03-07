import { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import Button from "../Button"
import "./MyAccount.css"

const MyAccount = (props) => {
    
    const navigate = useNavigate()
    
    const logout = () => {
        props.setLoggedUser(false)
        sessionStorage.removeItem('token')
        navigate('/')
    }
    
    useEffect(() => {
        if(!props.loggedUser) {
            alert("Você precisa estar logado para acessar esta página")
            navigate("/login")
        }
    }, [props.loggedUser, navigate]);
    
    return (
        <section className="my_account">
            {
                props.loggedUser &&
                <div>
                    <Button label={"logout"} onClick={logout}/>
                    <h2>
                        Página em construção
                    </h2>
                    <img src="./images/work-in-progress.png" alt="Página em construção"/>
                </div>
            }
        </section>
    )
}

export default MyAccount;