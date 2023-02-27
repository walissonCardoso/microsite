import "./TextoRodape.css"

const TextoRodape = (props) => {
    return (
        <div className="texto_rodape">
            <div>Por {props.autor}, em {props.data}</div>
            <div>
                {props.generos.map(
                    (genero, i) => [
                        i > 0 && ", ",
                        <a key={genero} href="/">{genero}</a>
                ])}
            </div>
        </div>
    )
}

export default TextoRodape