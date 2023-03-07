import "./ListaPaginas.css"

const ListaPaginas = (props) => {
    
    let numPages = Math.min(5, props.totalPages)
    
    let start = Math.max(1, props.pageNumber-1)
    
    if(props.totalPages > numPages)
        start = Math.min(start, props.totalPages-numPages+1)
        
    let end = start + numPages - 1
    
    var paginas = [];
    for (var i = start; i <= end; i++) {
        paginas.push(i);
    }
    
    const previousPage = () => {
        let target = Math.max(props.pageNumber-1, 0)
        props.setPageNumber(target)
    }
    
    const setCurrentPage = (number) => {
        props.setPageNumber(number)
    }
    
    const nextPage = () => {
        let target = Math.min(props.pageNumber+1, props.totalPages-1)
        props.setPageNumber(target)
    }

    return (
        <section className="lista_paginas">
            <a onClick={() => previousPage()} href={"#"+(props.pageNumber+1)}>
                &lt;&lt; Anterior
            </a>
            
            {paginas.map(pagina => {
                return <a key={pagina}
                          onClick={() => setCurrentPage(pagina-1)}
                          href={"#"+pagina}
                          className = {pagina === (props.pageNumber+1) ? "underline" : null}
                       >
                            {pagina}
                       </a>
            })}
            
            <a onClick={() => nextPage()} href={"#"+(props.pageNumber+1)}>
                Próxima &gt;&gt;
            </a>
        </section>
    )
}

export default ListaPaginas