import './Header.css'
import SelecaoMenu from '../SelecaoMenu'

const Header = () => {
    
    const optionsMenu = [
        {
            uri: "/login",
            texto:"Login"
        },{
            uri: "/submit",
            texto:"Enviar um texto"
        },{
            uri: "/filter",
            texto:"Filtrar textos"
        },{
            uri: "/",
            texto:"Pagina inicial"
        }
    ]

    return (
        <section>
            <div className="header">
                <div className='header_text'>
                    Micropensamento
                </div>
                <div className='header_subtitle'>
                    - contos em pequenas doses -
                </div>
            </div>
            <SelecaoMenu
                optionsMenu={optionsMenu}
            />
        </section>
    )
}

export default Header