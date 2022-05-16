import emailjs from 'emailjs-com';

const sendEmail=(serviceID, templateID, templateParams, userID)=>{
     emailjs.send(serviceID,
         templateID,
         templateParams,
         userID)
         
 }
 
 export default sendEmail;
 




