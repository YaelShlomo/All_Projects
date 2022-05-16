import React, {useState,useEffect} from 'react'
import './cartLine.css'
import Chip from '@material-ui/core/Chip';
import { makeStyles } from '@material-ui/core/styles';
import GridListTile from '@material-ui/core/GridListTile';
const useStyles = makeStyles(() => ({
    title:{    width: '45%',
        margin: '2%'},
    deleteIcon:{
        position: 'absolute',
    top: '2%',
    left: '4%'
    },
    chip:{height:'100%',width:'100%',backgroundColor:'#2bbbad'}
}));

function CartLine(props) {
    const {item, itemCounter,setItemCounter, updateSumAndItemCounter,setSum, sum , setCart,key}=props;
    const classes=useStyles();
    console.log(item.price);
    console.log(sum,itemCounter);
    const deletItem = (item) => {
        const newCart=props.cart.filter(i=>i!=item)
        setCart(newCart);
        setSum(sum-item.price);
        setItemCounter(itemCounter-1);
    }
    return(
<GridListTile cols={2} key={key} className={classes.title}>
        <Chip 
       className={classes.chip}
        classes={{ 
            deleteIcon:classes.deleteIcon
        }} 
         key={item.id}       
            onDelete={()=> deletItem(item)}

          label={
           <div> <div className="description">
                    <span>חבילת {props.item.title}</span>
                    <span> תמונות בחבילה {props.item.PackageSize}</span>
                </div>
               <img className="img" src={props.item.imgSrc} alt="" />
                <div className="total-price">{props.item.price} ש"ח </div>

               </div>  
         }
        />
 </GridListTile>
    );
}
export default CartLine











