import "./SelecaoMenu.css"

const SelecaoMenu = (props) => {
    return (
        <div className="selecao_menu">
            <div className="dropdown">
                <button>
                    <img src="/images/Menu.png" alt="Menu hambúrguer"/>
                </button>
                <div className="dropdown-content">
                    {props.optionsMenu.map(option =>
                        <a href={option.uri} key={option.uri}>{option.texto}</a>
                    )}
                </div>
            </div>
        </div>
    )
}

export default SelecaoMenu