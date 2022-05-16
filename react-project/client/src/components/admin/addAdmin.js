import { React, useState } from 'react'
import { MDBContainer, MDBRow, MDBCol, MDBBtn, MDBIcon, MDBInput } from 'mdbreact';
import '../../App.css'
import './addAdmin.css'
import { withRouter } from "react-router";
import { isValidName, isValidPhonenumber, isValidEmail, isValidNewPassword, isValidSecondPassword } from '../validations/validations';
import { post } from '../../services/httpHandler‏';

const LoginPage = (props) => {
    const { history } = props;


    const [state, setState] = useState({
        name: '',
        phone: '',
        email: '',
        password: '',
        validatepassword: '',
        errors: {
            name: '',
            phone: '',
            email: '',
            password: '',
            validatepassword: '',
        }
    });

    const handlerClick = () => {
        history.push('./added');
    }
    function onInputChange(field, event) {
        setState({ ...state, [field]: event.target.value });
        console.log(state);
    }

    function validate(event) {
        const { phone, email, name, password, validatepassword } = state;
        const errors = {};
        errors.phone = isValidPhonenumber(phone);
        errors.email = isValidEmail(email);
        errors.name = isValidName(name);
        errors.password = isValidNewPassword(password);
        if (!errors.password) {
            errors.validatepassword = isValidSecondPassword(password, validatepassword);
        }
        if (!errors.phone && !errors.email && !errors.name && !errors.password && !errors.validatepassword) {
            handlerClick();
            send(name, email, phone, password)
        }
        setState({ ...state, errors: errors });
        event.preventDefault();
    }
   
    const send = async (name, email, phone, password) => {
        const newAdmin = {
            "name": name,
            "email": email,
            "phone": phone,
            "password": password,
        }
        await post('/addAdmin', newAdmin);
    };
   
    return (
        <MDBContainer  >
            <MDBRow>
                <MDBCol id="page" md="6">
                    <h1 className="h5 text-center mb-4">הכנס פרטים</h1>
                    <form id="divTable" class="InsideContent" >
                        <div className="grey-text">
                            <MDBInput label=" שם" icon="user" group type="text" validate error="wrong" value={state.name} onChange={(e) => onInputChange('name', e)}
                                success="right" />
                            {state.errors.name && <div id="1" className="error">{state.errors.name}</div>}

                            <MDBInput label="מייל" icon="envelope" group type="email" validate error="wrong" value={state.email} onChange={(e) => onInputChange('email', e)}
                                success="right" />
                            {state.errors.email && <div id="2" className="error">{state.errors.email}</div>}

                            <MDBInput label="פלאפון" icon="phone-alt" group type="phone" validate error="wrong" value={state.phone} onChange={(e) => onInputChange('phone', e)} success="right" />
                            {state.errors.phone && <div id="3" className="error">{state.errors.phone}</div>}

                            <MDBInput label="סיסמה" icon="key" group type="password" validate error="wrong" value={state.password} onChange={(e) => onInputChange('password', e)} success="right" />
                            {state.errors.password && <div id="4" className="error">{state.errors.password}</div>}

                            <MDBInput label="הכנס סיסמה שוב" icon="key" group type="password" validate error="wrong" value={state.validatepassword} onChange={(e) => onInputChange('validatepassword', e)} success="right" />
                            {state.errors.validatepassword && <div id="5" className="error">{state.errors.validatepassword}</div>}

                        </div>

                        <div className="text-center">
                            <MDBBtn className="sendButton" type="submit" onClick={validate} >
                                הוספה
                                <MDBIcon far icon="paper-plane" className="ml-1" />
                            </MDBBtn>
                        </div>
                    </form>

                </MDBCol>
            </MDBRow >
        </MDBContainer >
    );



};

export default withRouter(LoginPage);






