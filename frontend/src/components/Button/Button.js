import "./Button.css"

const Button = (props) => {
    return (
        <button className="button"
                disabled={props.disabled ? props.disabled : null}
                onClick={props.onClick ? props.onClick : null}>
            {props.label}
        </button>
    )
}

export default Button