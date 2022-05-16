import React from 'react'
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Rating from '@material-ui/lab/Rating';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import ListItemText from '@material-ui/core/ListItemText';
import {post} from '../../services/httpHandler‏';
import Button from '@material-ui/core/Button';
import Avatar from '@material-ui/core/Avatar';
function Add(props) {  
     const {addToQuestionsList}=props;
   
    const [question, setQuestion] = React.useState("")
   
    const AddQuestion = async (newQuestion) => {
        // Connection to server at port 5000
        await post('/addQuestion',newQuestion);    
        // addToQuestionsList(newQuestion);
      };
       const send=()=>{
        // At the click of a button all the fields are updated
        // Therefore there will be a connection to the server again and the response will appear on the screen
        AddQuestion({ question:question});
        setQuestion("");
        alert("השאלה נשלחה בהצלחה")
       }

    return (
        
         <div style={{width:'100%'}}>
         <ListItemText color="primary" primary={'השאלה שלך'}/>            
                
         <div style={{display:'flex'}}>           
         
  
 </div>
 <TextareaAutosize style={{width:'100%'}} rowsMax={3} rows={3} aria-label="empty textarea" placeholder="שאלה" onChange={(e) => { setQuestion(e.target.value) }} />
<Button style={{float:'left',height:40}}  variant="outlined" color="primary" onClick={send}>הוספת שאלה</Button>
</div>
    );
}
export default Add

