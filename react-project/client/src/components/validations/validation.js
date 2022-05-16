function isEmpty(value) {
    return value == null || value == '';
}

function validatePhone(numberByString) {
    let numberByNum = Number(numberByString);
    // Checks for letters
    if (numberByNum == isNaN) {
        return "Click numbers only";
    }
    if (isEmpty(numberByString)) {
        return "Required field";
    }
    if (numberByString.length > 10 || numberByString.length < 9) {
        // Check the length
        return "Invalid number of digits";
    }
    if(/^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/.test(numberByString)) {
        return '';
    }
    return "Not valide phone number";
}


function validateName(inputName) {
    if (isEmpty(inputName)) {
        return "Required field";
    }
    let splitedName = inputName.split('');
    let namelength = inputName.length;
    if (namelength > 20) {
        // I decided that the name would be at most 30 characters long
        return "Too long Name";
    }
    for (let index = 0; index < namelength; index++) {
        // Name contains only letters and spaces
        let numericChar = Number(splitedName[index]);
        if (!isNaN(numericChar) && numericChar != " ") {
            return "Insert characters only";
        }
    }
    return '';
}


function validateEmail(email) 
{
    if (isEmpty(email)) {
    return "Required field";
    }
    if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email))
    // An email address contains English letters, @, and letters again
    {
    return "";
    }
    return "Not valid email";
}



export {
    validatePhone,
    validateName,
    validateEmail,
}