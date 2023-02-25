import "./ListaPaginas.css"

const ListaPaginas = () => {
    
    const paginas = [
        '1','2','3','4'
    ]

    return (
        <section className="lista_paginas">
            <a href="www.google.com.br">&lt;&lt; Anterior</a>
            {
                paginas.map(pagina => {
                    return <a key={pagina} href={pagina}>{pagina}</a>
                })
            }
            <a href="www.google.com.br">Próxima &gt;&gt;</a>
        </section>
    )
}

export default ListaPaginas