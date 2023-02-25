import "./Footer.css"

const Footer = (props) => {
    return (
        <div className="footer">
            <div className="footer_textos">
                <div>{props.titulo}</div>
                <div>{props.autor}, {props.ano}</div>
            </div>
        </div>
    )
}

export default Footer;