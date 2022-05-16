import React, { useState } from "react";
import { MDBNavbar, MDBNavbarBrand, MDBNavbarNav, MDBNavItem, MDBNavLink, MDBNavbarToggler, MDBCollapse, MDBDropdown,
MDBDropdownToggle, MDBDropdownMenu, MDBDropdownItem, MDBIcon } from "mdbreact";
import logo from '../../asset/images/l.png'
import './navbar.css'
import { BrowserRouter as Router, Switch, Link} from 'react-router-dom';
import MyRouter from '../router/router'
import CustomizedBadges from '../cart/icon/CustomizedBadges'
import { makeStyles } from '@material-ui/core/styles';
const useStyles = makeStyles(() => ({
  cart:{
    position: 'absolute',
    right: 0,
    top: '70%',   
    zIndex: 5
  }
}));

function NavbarPage (props) {
 const[isOpen,setIsOpen]=useState(false);
const classes=useStyles();

const toggleCollapse = () => {
  setIsOpen(!isOpen);
}
const handleOnclick = () => {
  props.setIsAdmin(false)
}

  return (
    <Router>
            <Link className={classes.cart} to="/cart"><CustomizedBadges itemsCounter={props.itemCounter}></CustomizedBadges></Link>

      <MDBNavbar color="default-color" dark expand="md">
        <MDBNavbarBrand>
          <img src={logo} alt="Avatar" className="avatar" /> :
        </MDBNavbarBrand>
        <MDBNavbarToggler onClick={toggleCollapse} />
        <MDBCollapse id="navbarCollapse3" isOpen={isOpen} navbar>
          <MDBNavbarNav right>
            <MDBNavItem>
              <MDBNavLink className="navtext" to="/home">דף הבית</MDBNavLink>
            </MDBNavItem>
            <MDBNavItem>
              <MDBNavLink  className="navtext" to="/about">קצת עלי</MDBNavLink>
            </MDBNavItem>
            <MDBNavItem>
              <MDBNavLink className="navtext" to="/Satisfied-customers">לקוחות מרוצים</MDBNavLink>
            </MDBNavItem>
            <MDBNavItem>
              <MDBNavLink className="navtext" to="/Questions-and-Answers">שאלות נפוצות</MDBNavLink>
            </MDBNavItem>
            <MDBNavItem>
              <MDBDropdown>
                <MDBDropdownToggle nav caret>
                  <div className="d-none d-md-inline navtext">גלרייה</div>
                </MDBDropdownToggle>
                <MDBDropdownMenu className="dropdown-default ">
                  <MDBDropdownItem href="gallery-newborn">ניו בורן</MDBDropdownItem>
                  <MDBDropdownItem href="gallery-SmashCake">סמאש קייק</MDBDropdownItem>
                  <MDBDropdownItem href="gallery-Halaka">חלאקה</MDBDropdownItem>
                  <MDBDropdownItem href="gallery-children">צילומי ילדים וחוץ</MDBDropdownItem>
                  <MDBDropdownItem href="gallery-dipartment">אדריכלות</MDBDropdownItem>
                </MDBDropdownMenu>
              </MDBDropdown>
            </MDBNavItem>
            <MDBNavItem>
              <MDBNavLink className="navtext" to="/contactUs">צור קשר</MDBNavLink>
            </MDBNavItem>
            <MDBNavItem>
              <MDBNavLink to="/card" className="navtext">הזמנת חבילת צילום</MDBNavLink>
            </MDBNavItem>
          </MDBNavbarNav>
          <MDBNavbarNav left>
          <MDBNavItem>
              <MDBNavLink to="/admin" className="admin" >כניסה כמנהל</MDBNavLink>
            </MDBNavItem>
          </MDBNavbarNav>
        </MDBCollapse>
      </MDBNavbar>
      <Switch>
      <>
       <MyRouter packageList={props.packageList} cart={props.cart} setCart={props.setCart}
        itemCounter={props.itemCounter} setItemCounter={props.setItemCounter} sum={props.sum} setSum={props.setSum}
        isAdmin={props.isAdmin} setIsAdmin={props.setIsAdmin} />

        </>
      </Switch>
    </Router >
    );
  }


export default NavbarPage;