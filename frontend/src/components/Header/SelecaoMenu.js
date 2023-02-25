import "./SelecaoMenu.css"

const SelecaoMenu = () => {

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
        <div className="selecao_menu">
            <div className="dropdown">
                <button>
                    <img src="/images/Menu.png" alt="Menu hambúrguer"/>
                </button>
                <div className="dropdown-content">
                    {optionsMenu.map(option =>
                        <a href={option.uri} key={option.uri}>{option.texto}</a>
                    )}
                </div>
            </div>
        </div>
    )
}

export default SelecaoMenu