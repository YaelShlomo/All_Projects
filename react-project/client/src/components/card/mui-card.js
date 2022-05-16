import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {Link} from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const useStyles = makeStyles(
  {
  root: {
    maxWidth: 250,
  },
  media: {
    height: 140,
  },
  cardTitle:{
    textAlign: 'left',
  },
  cardButton:{
    fontSize: 32,
    backgroundColor: 'pink',
  },
  
}
);

export default function MediaCard(props) {
  const classes = useStyles();
  function updateCart(item){
    let newlist=props.cart.concat(item);
    props.setCart(newlist);
    props.setItemCounter(props.itemCounter+1);
    props.setSum(props.sum+item.price);
    // Notice that the package has been added to the cart, the toast component is imported from a separate file
   toast("!החבילה נוספה לסל בהצלחה", {position: "bottom-center"})
  
  }
  // Image, description, and price are obtained as parameters, thus creating different cards with the same component
  return (
    <div>
    <Card className={classes.root}>
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image={props.item.imgSrc}
          title="Contemplative Reptile"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2" className={classes.cardTitle}>
          {props.item.title}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
          {props.item.desc}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button classes={classes.cardButton} onClick={()=>{updateCart(props.item)}}>
          הוספה לסל
        </Button>
        <Button size="small" color="primary" ><Link to={props.item.galleryLink}>מעבר לגלריית {props.item.title}</Link>
          
        </Button>
      </CardActions>
    </Card>
    <ToastContainer position="bottom-center" />
    </div>
  );
}













