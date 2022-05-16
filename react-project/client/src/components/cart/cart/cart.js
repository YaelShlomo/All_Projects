
import React, { useState } from 'react'
import './cart.css'
import CartLine from '../cartLine/cartLine'
import { Link, useHistory } from 'react-router-dom';
import Paper from '@material-ui/core/Paper';
import GridList from '@material-ui/core/GridList';
function Cart(props) {
    const cart = props.cart;
    const setCart = props.setCart;
    let history = useHistory();
    const handelClick=()=>{
        props.sum>0 ? history.push("/payment"): alert("הסל ריק")
    }
    return (
        <div className="shopping-cart">
               <Paper  className="title">
                             {cart.lenght==0? 'הסל שלי'  :
                             <GridList> 
                                 {cart.map((item,key) => {
               return(
                   <CartLine key={key} style={{    height: '100%'}}
                   cart={cart} setCart={setCart} item={item}  sum={props.sum} setSum={props.setSum}
                 itemCounter={props.itemCounter} setItemCounter={props.setItemCounter}></CartLine>)
           } )}  </GridList>               
}  
            </Paper>
            <button id="payment" onClick={()=>handelClick()}>מעבר לתשלום</button>
       
        </div>
    );
}

export default Cart
