import TextoRodape from "./TextoRodape"
import "./Texto.css"

const Texto = (props) => {
    return (
        <section className="texto">
            <h2>
                { props.titulo }
            </h2>
            <div className="corpo">
                { props.corpo }
            </div>
            <TextoRodape
                autor={props.autor}
                data={props.data}
                generos={props.generos}
            />
        </section>
    )
}

export default Texto