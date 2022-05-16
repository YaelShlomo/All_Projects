import React , {useState} from 'react'
import MaterialUIPickers from '../elements/datePicker'
import Paypal2 from '../payment/paypal2'
import BasicTextFields from '../elements/textbox'
import { Container } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import {
    validateName,
    validateEmail,
 } from '../validations/validation'
import { Button } from '@material-ui/core';
import allDates from '../../DataBase/commentJson.json';
import {post} from '../../services/httpHandler‏';


const useStyles = makeStyles(
    {
        inputTitle:{
            textAlign: 'right',
            alignItems: 'right',
            direction: 'rtl',
          },
        container:{
            marginTop:'5%',
            backgroundColor: '#ffef62',
            borderRadius:'5%',
        },
        pay:{
            color:'#2bbbad',
            fontSize:30,
            backgroundColor:'#fff59d'
        }
  }
  );

  export default function Payment1(props){
    const [isPayment, setIsPayment]=useState(false);
    const classes = useStyles();
    const [state, setState] = useState({
        name: '',
        email: '',
        city: '',
        date:'',

     });
     const [errorState, setErrorState] = useState({
        errorName: '',
        errorEmail: '',
        errorCity: '',
     })

     const addOrdre = async (order) => {
        // Connection to server at port 5000
        const newOrder = {
            "name":order.name,
            "email":order.email,
            "city":order.city,
            "date":order.date
        }
        await post('/addOrders',newOrder); 
      };

    let dates=allDates.dates;
    let notAvailableDats=dates.map(item => new Date(item.date));
    
     function updateState(field, event) {
        setState({ ...state, [field]: event.target?.value || event });
     }
     function validate(event) {
        errorState.errorName = validateName(state.name);
        errorState.errorEmail = validateEmail(state.email);
        errorState.errorCity = validateName(state.city);
        setErrorState({ ...errorState, errorState: errorState });
        if (errorState.errorName == "" && errorState.errorEmail == "" &&
           errorState.errorCity == "") {
            setIsPayment(true);
            addOrdre(state);
    
        }
     }
     
    return(
        <Container maxWidth="sm" className={classes.container}>
            <BasicTextFields caption="שם" className={classes.inputTitle} onChange={(e) => updateState('name', e)}/>
            <div className="error-message">{errorState.errorName}</div>
            <BasicTextFields caption='דוא"ל'onChange={(e) => updateState('email', e)}/>
            <div className="error-message">{errorState.errorEmail}</div>
            <BasicTextFields caption="עיר מגורים" onChange={(e) => updateState('city', e)}/>
            <div className="error-message">{errorState.errorCity}</div>
            <MaterialUIPickers from text={"בחירת תאריך ליום צילום"} notAvailableDats={notAvailableDats} onChange={updateState}/>
             {isPayment?<Paypal2 total={props.total} ></Paypal2>:<Button className={classes.pay} onClick={validate}>לתשלום</Button>}
        </Container>
    );
}
 


