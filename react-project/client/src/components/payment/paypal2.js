import PayPalButton from '../payment/paypalButton'
function Paypal2(props){
console.log(props.total)
    return(
        <PayPalButton total={props.total} history={props.history} />
    )
}
export default Paypal2