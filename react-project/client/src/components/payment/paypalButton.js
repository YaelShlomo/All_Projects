import React from 'react';
import PaypalExpressBtn from 'react-paypal-express-checkout';
import sendEmail from '../email/email'
export default class MyApp extends React.Component {
    constructor(props){
        super(props);
    }
    render() {
        const onSuccess = (payment) => {
            //window.emailjs.send('gmail', templateId,variables);
            // Congratulation, it came here means everything's fine!
            		console.log("The payment was succeeded!", payment);
                    // You can bind the "payment" object's value to your state or props or whatever here, please see below for sample returned data
                    sendEmail('service_cu5ir0t', 'template_rcgn8qb', {
                        customer_address:this.props.email,
                         to_name:this.props.name ,
                        }, 'user_wiyaOa9pmNGkaJEuWKWz3');
                        alert("התשלום בוצע בהצלחה");
        }
 
        const onCancel = (data) => {
            
            // User pressed "cancel" or close Paypal's popup!
            console.log('The payment was cancelled!', data);
            // You can bind the "data" object's value to your state or props or whatever here, please see below for sample returned data
        }
 
        const onError = (err) => {
            // The main Paypal's script cannot be loaded or somethings block the loading of that script!
            console.log("Error!", err);
            // Because the Paypal's main script is loaded asynchronously from "https://www.paypalobjects.com/api/checkout.js"
            // => sometimes it may take about 0.5 second for everything to get set, or for the button to appear
        }
        //let shipping = 3;
        let env = 'sandbox'; // you can set here to 'production' for production
        let currency = 'ILS'; // or you can set this value from your props or state
        let total = this.props.total;
        // same as above, this is the total amount (based on currency) to be paid by using Paypal express checkout
        // Document on Paypal's currency code: https://developer.paypal.com/docs/classic/api/currency_codes/
 
        const client = {
            sandbox: "AdNW3n2qOFeHnGbATbyssIOSpjSMrGB8DE482Gpy5RTT31dNw2t2JcT1cuihmIreUb1AC1G_M9JcX3L3",
            production: 'YOUR-PRODUCTION-APP-ID',
        }
        // In order to get production's app-ID, you will have to send your app to Paypal for approval first
        // For sandbox app-ID (after logging into your developer account, please locate the "REST API apps" section, click "Create App"):
        //   => https://developer.paypal.com/docs/classic/lifecycle/sb_credentials/
        // For production app-ID:
        //   => https://developer.paypal.com/docs/classic/lifecycle/goingLive/
 
        // NB. You can also have many Paypal express checkout buttons on page, just pass in the correct amount and they will work!
        return (
            <PaypalExpressBtn env={env}
            //shipping={shipping} 
            client={client} 
            currency={currency} 
            total={total} 
            onError={onError} 
            onSuccess={onSuccess}
            onCancel={onCancel} />
        );
    }
}