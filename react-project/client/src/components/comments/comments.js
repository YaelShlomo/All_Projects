import React from 'react'
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Rating from '@material-ui/lab/Rating';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import ListItemText from '@material-ui/core/ListItemText';
import {post} from '../../services/httpHandler‏';
import Button from '@material-ui/core/Button';
import Avatar from '@material-ui/core/Avatar';
function Comments(props) {  
     const {addToCommentList}=props;
    const [value, setValue] = React.useState(0);
    const [comment, setComment] = React.useState("")
    const [imgSrc, setImgSrc] = React.useState(0);
    const addcomment = async (newComment) => {
        // Connection to server at port 5000
        await post('/addRespondsList',newComment); 
        console.log(newComment)   
        addToCommentList(newComment);
      };
       const onImageChange = (event) => {
        var file = event.target.files[0];

        var reader = new FileReader();
        reader.onload = function(event) {
          // The file's text will be printed here
          setImgSrc(event.target.result);
        };
        reader.readAsDataURL(file);
       }
       const send=()=>{
        // At the click of a button all the fields are updated
        // Therefore there will be a connection to the server again and the response will appear on the screen
        debugger
            console.log({ comment:comment, value:value, imgSrc:imgSrc})
           addcomment({ comment:comment, value:value, imgSrc:imgSrc});
           setValue(0);
           setComment("");
           setImgSrc(0);
       }

    return (
        
         <div style={{width:'100%'}}>
         <ListItemText color="primary" primary={'התגובה שלך'}/>            
                
         <div style={{display:'flex'}}>           
         
  <Box component="fieldset" mb={3} borderColor="transparent">
     <Typography component="legend" color="secondary" > דרוג חווית צילום </Typography>
     <Rating
         name="simple-controlled"
         value={value}
         onChange={(event, newValue) => {
             setValue(newValue);
         }}
     />
 </Box>
 </div>
 <TextareaAutosize style={{width:'100%'}} rowsMax={3} rows={3} aria-label="empty textarea" placeholder="תגובה" onChange={(e) => { setComment(e.target.value) }} />
 {/* <span style={{display:'inline-flex'}}>            
 <Avatar style={{marginLeft:4}} variant="rounded" alt="Generic placeholder image" src={imgSrc} />
              
            <Button  variant="outlined" color="secondary"  component="label">הוספת תמונת פרופיל
                <input type="file" hidden  onChange={onImageChange}  accept="image/png, image/jpeg, image/jpg" />
            </Button></span> */}
<Button style={{float:'left',height:40}}  variant="outlined" color="primary" onClick={send}>הוספת תגובה</Button>
</div>
    );
}
export default Comments

