import React from 'react'
import CarouselPage from '../carousel/carousel'
import MediaObjectPage from '../comments/recommendation'
import LightboxPage from '../lightBox/mdb-lb1'
import MediaCard from '../card/mui-card'
import ControlledAccordions from '../q&a/qlist'
import Payment1 from '../payment/payment'
import Cart from '../cart/cart/cart'
import Login from '../admin/login';
import AboutMe from '../about/about'
import Enter from '../admin/adminEnterPage'
import LoginPage from '../admin/addAdmin'
import AddMessage from '../admin/added'
import Admin from '../admin/administartorsDetails' 


import n1 from '../../asset/images/n1.png'
import n2 from '../../asset/images/n2.png'
import n3 from '../../asset/images/n3.png'
import n4 from '../../asset/images/n4.png'
import n5 from '../../asset/images/n5.png'
import n6 from '../../asset/images/n6.png'
import n7 from '../../asset/images/n7.png'
import n8 from '../../asset/images/n8.png'
import n9 from '../../asset/images/n9.png'
import p1 from '../../asset/images/p1.png'
import p2 from '../../asset/images/p2.jpg'
import p3 from '../../asset/images/p3.jpg'
import p4 from '../../asset/images/p4.jpg'
import p5 from '../../asset/images/p5.png'
import p6 from '../../asset/images/p6.jpg'
import p7 from '../../asset/images/p7.jpg'
import p8 from '../../asset/images/p8.png'
import p9 from '../../asset/images/p9.png'
import s1 from '../../asset/images/s1.png'
import s2 from '../../asset/images/s2.png'
import s3 from '../../asset/images/s3.png'
import s4 from '../../asset/images/s4.png'
import s5 from '../../asset/images/s5.png'
import s6 from '../../asset/images/s6.png'
import s7 from '../../asset/images/s7.png'
import s8 from '../../asset/images/s8.png'
import s9 from '../../asset/images/s8.png'
import c1 from '../../asset/images/c1.png'
import c2 from '../../asset/images/c2.png'
import c3 from '../../asset/images/c3.png'
import c4 from '../../asset/images/c4.png'
import c5 from '../../asset/images/c5.png'
import c6 from '../../asset/images/c6.png'
import c7 from '../../asset/images/c7.png'
import c8 from '../../asset/images/c8.png'
import c9 from '../../asset/images/c8.png'
import h1 from '../../asset/images/h1.jpg'
import h2 from '../../asset/images/h2.jpg'
import h3 from '../../asset/images/h3.jpg'
import h4 from '../../asset/images/h4.jpg'
import h5 from '../../asset/images/h5.jpg'
import h6 from '../../asset/images/h6.jpg'
import h7 from '../../asset/images/h7.jpg'
import h8 from '../../asset/images/h8.jpg'
import h9 from '../../asset/images/h9.jpg'
import logo from '../../asset/images/l.png'
import '../cardList/cardsList.css'
import './router.css'
import ContactUs from '../contactUc/contactUs'
import { BrowserRouter as Router, Switch, Route , Link} from 'react-router-dom';

function MyRouter(props){
    const title1="פרטי מנהלים";
    const title2="מועדון לקוחות";
    const title3="פרטי מזמינים";
    const type1="/getAdmin";
    const type2="/getContact";
    const type3="/getOrders";

    

    return(
    <div className="container">
        <Route exact path="/">
            <CarouselPage />
        </Route>
        <Route path="/home">
            <CarouselPage />
        </Route>
        <Route path="/about">
            <AboutMe />
        </Route>
        <Route path="/Satisfied-customers">
            <MediaObjectPage />
        </Route>
        <Route path="/Questions-and-Answers">
            <ControlledAccordions />
        </Route>
        <Route path="/gallery-newborn">
            <LightboxPage images={[n1,n2,n3,n4,n5,n6,n7,n8,n9]}  />
        </Route>
        <Route path="/gallery-SmashCake">
            <LightboxPage images={[s1,s2,s3,s4,s5,s6,s7,s8,s9]}  />
        </Route>
        <Route path="/gallery-Halaka">
            <LightboxPage images={[c1,c2,c3,c4,c5,c6,c7,c8,c9]}  />
        </Route>
        <Route path="/gallery-children">
            <LightboxPage images={[p1,p2,p3,p4,p5,p6,p7,p8,p9]}  />
        </Route>
        <Route path="/gallery-dipartment">
            <LightboxPage images={[h1,h2,h3,h4,h5,h6,h7,h8,h9]}  />
        </Route>
        <Route path="/contactus">
            <ContactUs logo={logo} title="צור קשר"/>
        </Route>
        <Route path="/Admin">
            <Login isAdmin={props.isAdmin} setIsAdmin={props.setIsAdmin}/>
            {/* {!localStorage.getItem("isAdmin") && <Login isAdmin={props.isAdmin} setIsAdmin={props.setIsAdmin}/>}
            {localStorage.getItem("isAdmin") &&  <Enter /> } */}
        </Route>
        <Route path="/card">
            <div className="grid-container">
             {props.packageList&&props.packageList.map(item=><MediaCard className="MediaCard" item={item} cart={props.cart} setCart={props.setCart}
             itemCounter={props.itemCounter} setItemCounter={props.setItemCounter}
             sum={props.sum} setSum={props.setSum} isToast={false}/>)}  
            </div>
        </Route>
        <Route path="/cart">
            <Cart cart={props.cart} setCart={props.setCart} itemCounter={props.itemCounter} setItemCounter={props.setItemCounter}
            sum={props.sum} setSum={props.setSum}/>
        </Route>
        <Route path="/payment">
            <Payment1 total={props.sum}/>
        </Route>
        <Route path="/AdminEnterPage">
          <Enter />
        </Route>
        <Route path="/addadmin">
          <LoginPage />
        </Route>
        <Route path="/added">
          <AddMessage />
        </Route>
        <Route path="/administratorsdetails">
          <Admin title1={title1} type1={type1}/>
        </Route>
        <Route path="/contacts">
          <Admin title1={title2} type1={type2}/>
          
        </Route>
        <Route path="/OrderDetails">
          <Admin title1={title3} type1={type3}/>
        </Route>
        
    </div> 
    );
}
export default MyRouter