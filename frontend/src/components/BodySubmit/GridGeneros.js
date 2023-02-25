import "./GridGeneros.css"

const GridGeneros = (props) => {
    
    return (
        <div className="grid_generos">
            <ul>{
                props.generos.map( genero =>
                    <li key={genero.nome}>
                        <input type="checkbox"/>
                        {genero.nome}
                    </li>
                )
            }</ul>
        </div>
    )
}

export default GridGeneros