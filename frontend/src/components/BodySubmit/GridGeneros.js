import "./GridGeneros.css"

const GridGeneros = (props) => {

    let selected = new Array(props.listaGeneros.length).fill(false)
    
    for(var i = 0; i < props.listaGeneros.length; i++)
        selected[i] = props.generos.includes(props.listaGeneros[i].id)
    
    const onSelectGenre = (index) => {
        selected[index] = !selected[index]
        props.setGeneros(
            props.listaGeneros.filter((genero, index) => selected[index])
                              .map(genero => genero.id)
        )
    }
    
    return (
        <div className="grid_generos">
            <ul>{
                props.listaGeneros.map( (genero, index) =>
                    <li key={"checkbox_" + genero.id}>
                        <div>
                            <input type="checkbox" id={genero.id} onChange={() => onSelectGenre(index)}/>
                            <label htmlFor={genero.id}>{genero.nome}</label>
                        </div>
                    </li>
                )
            }</ul>
        </div>
    )
}

export default GridGeneros