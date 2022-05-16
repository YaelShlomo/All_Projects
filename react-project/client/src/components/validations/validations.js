import React, {useEffect} from 'react'
import {get} from '../../services/httpHandler‏';


function isValidEmail(email) {
    if (email.length === 0) {
        return "שדה חובה";
    }
    const emailre = /^[\w-.]+@([\w-]+.)+[\w-]{2,4}$/;
    if (!email.match(emailre))
        return "כתובת מייל שגויה";
    return;

}

function isValidPhonenumber(phonenumber) {
    if (phonenumber.length === 0) {
        return "שדה חובה";
    }
    if (phonenumber.length > 10 || phonenumber.length < 9) {
        return "מספר באורך לא מתאים";
    }
    const num = /^0?(([23489]{1}\d{7})|[5]{1}\d{8})$/
    if (!phonenumber.match(num))
        return "מספר פלאפון שגוי";
    return;
}

function IsValidAdminDetails(name, password, adminsList) {
    console.log(adminsList)
debugger
    for (let index = 0; index < adminsList.length; index++) {
        if (password.length === 0 && name.length === 0) {
            return "שם וסיסמה חובה";
        }
        else if (name.length === 0) {
            return "שדה חובה";
        }
        else if (password.length === 0) {
            return "שדה חובה";

        }
        if (adminsList[index].name === name) {
            if (adminsList[index].password === password) {
                return;
            }
            else
                return "סיסמה שגויה"
        };

    }
    return "שגיאה בשם או בסיסמה"
}

function isValidNewPassword(password) {
    if (password.length === 0) {
        return "שדה חובה";
    }
    var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}/;
    if (!password.match(passw))
        return "סיסמה חייבת להיות באנגלית ולכלול לפחות אות קטנה אחת, אות גדולה אחת, ומספר אחד,אורך בין 6-12 סך הכל"
    return;

}

function isValidName(username) {
    if (username.length === 0) {
        return "שדה חובה";
    }
    if (username.length < 2)
        return "אורך לא חוקי";
    if (typeof username !== "undefined") {
        if (username.match("^[A-Za-z\u0590-\u05FF ]+$")) {
            return null;
        }
        return "שם לא סטנדרטי";
    }
    return "שם לא סטנדרטי";
}

function isValidSecondPassword(password, validatepassword) {
    if (validatepassword.length === 0) {
        return "שדה חובה";
    }
    if (password !== validatepassword) {
        return "שגיאה"
    }
    return;
}






export {
    isValidName,
    isValidPhonenumber,
    isValidEmail,
    isValidNewPassword,
    isValidSecondPassword,
    IsValidAdminDetails
}