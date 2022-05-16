
import React, { useState } from 'react';
import {
   validatePhone,
   validateName,
   validateEmail,
} from '../validations/validation'
import sendEmail from '../email/email'
import { post } from '../../services/httpHandler‏';
// import { get } from '../../services/httpHandler‏';

function ContactUs(props) {
   const [state, setState] = useState({
      name: '',
      phone: '',
      email: '',
   });
   const [errorState, setErrorState] = useState({
      errorName: '',
      errorPhone: '',
      errorEmail: '',
   })
   function updateState(field, event) {
      setState({ ...state, [field]: event.target.value });
   }
   // Checking the correctness of the inserted fields and printing an appropriate message
   const sendEmail1 = () => {
      // sendEmail(serviceId, templateId, details, userId) All details are entered in advance
      sendEmail('service_cu5ir0t', 'template_joapq2b', {
         from_mail: state.email,
         from_name: state.name1,
         from_tel: state.phone
      }, 'user_wiyaOa9pmNGkaJEuWKWz3');
   }

   const [contact, setContact] = useState([]);

   // async function lookForContant() {
   //    var data = await get("/addContact");
   //    setContact(data);
   //    console.log(data)
   // }

   const writeToDB = async (name, phone, email) => {
      let customer = {
         "name": name,
         "email": email,
         "phone_number": phone
      }
       // Connection to server at port 5000
      await post('/addContact', customer);
   };

   function validate(event) {
      errorState.errorName = validateName(state.name);
      errorState.errorPhone = validatePhone(state.phone);
      errorState.errorEmail = validateEmail(state.email);
      setErrorState({ ...errorState, errorState: errorState });
      if (errorState.errorName == "" && errorState.errorPhone == "" &&
         errorState.errorEmail == "") {
         // When all validation tests are correct he will send an email with the details
         sendEmail1();
         alert("יצרתם קשר בהצלחה!")
         // Prevents the defiant behavior that is refresh the screen
         writeToDB(state.name, state.phone, state.email);
         event.preventDefault();
      }
      else {
         event.preventDefault();
      }
   }
   return (
      <div>
         <br></br>
         <br></br>
         <form onSubmit={validate}>
            <img src={props.logo} alt="Avatar" className="avatar" />
            {/* Validation fields: */}
            {/* user name field */}
            <div className="inpt-div">
               <h3>שם</h3>
               <input className="inpt" type="text" placeholder={"שם"}
                  onChange={(e) => updateState('name', e)} >
               </input>
               <div className="error-message">{errorState.errorName}</div>
            </div>
            {/* phone field */}
            <div>
               <h3>מספר פלאפון</h3>
               <input className="inpt" type="text" placeholder={"מספר פלאפון"}
                  onChange={(e) => updateState('phone', e)}  >
               </input>
               <div className="error-message">{errorState.errorPhone}</div>
            </div>
            <div>
               {/* email field */}
               <h3>דוא"ל</h3>
               <input className="inpt" type="email" placeholder={'דוא"ל'}
                  onChange={(e) => updateState('email', e)} >
               </input>
               <div className="error-message">{errorState.errorEmail}</div>
            </div>
            {/* send the form   */}
            <button type="submit" className="sign" >צור קשר</button>
         </form>
      </div>
   );
}

export default ContactUs;
