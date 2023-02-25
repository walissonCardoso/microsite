import './Header.css'
import SelecaoMenu from './SelecaoMenu'

const Header = () => {

    return (
        <section>
            <div className="header">
                <div className='header_text'>
                    Micropensamento
                </div>
            </div>
            <SelecaoMenu/>
        </section>
    )
}

export default Header