import { React, useState, useEffect } from "react";
import '../admin/contact.css';
import '../admin/login.css'
import { IsValidAdminDetails } from '../validations/validations'
import { MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn } from 'mdbreact';
import { withRouter } from "react-router";
import { post } from '../../services/httpHandler‏';


function Login (props) {
  const { history } = props;
  const [state, setState] = useState({
    name: '',
    password: '',
    admins: '',
    errors: {
      name: '',
      password: '',
      admins: '',
    }
  });
  const [adminsList, setAdminsList] =useState([]);
    async function getAdmin(){
      console.log("getAdmin")
  return  await post("/adminLogin", {"name":state.name,"password": state.password});
    
  };

  const  handlerClick = async() => {
    debugger
   await getAdmin().then((data)=>{
     if(data.accessToken==null)
     console.log(data.message)
     else{
       localStorage.setItem("isAdmin",data.isAdmin==true)
       history.push('./AdminEnterPage')

     }
   })
  }
  function onInputChange(field, event) {
    setState({ ...state, [field]: event.target.value });
  }

  function validate(event ) {
    const { name, password } = state;
    const errors = {};
    errors.admins = IsValidAdminDetails(name, password, adminsList)
    props.setIsAdmin(true);
    handlerClick();
    setState({ ...state, errors: errors });
    event.preventDefault();
  }
 
  return (
    <MDBContainer>
      <MDBRow>
        <MDBCol md="6">
          <form id="login">
            <p className="h5 text-center mb-4">כניסה</p>
            <div className="grey-text">
              <MDBInput label="שם מנהל" icon="user" group type="text" validate error="wrong" value={state.name} onChange={(e) => onInputChange('name', e)}
                success="right" />
              <MDBInput label="סיסמה" icon="lock" group type="password" validate value={state.password} onChange={(e) => onInputChange('password', e)} />
            </div>

            <br />
            {state.errors.admins && <div className="error">{state.errors.admins}</div>}
            <div className="text-center" onClick={validate}>
              <MDBBtn className="sendButton" type="submit">התחבר</MDBBtn>
            </div>
          </form>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
};




export default withRouter(Login);


