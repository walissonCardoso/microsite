import "./TextoRodape.css"

const TextoRodape = (props) => {
    return (
        <div className="texto_rodape">
            <div>{props.autor}, {props.data}</div>
            <div>{props.generos}</div>
        </div>
    )
}

export default TextoRodape