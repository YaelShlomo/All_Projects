const { RespondsList } = require("../models/respondsList");
const { Admin ,Contact} = require("../models/users");
const {Questions_ans,Question } = require("../models/question_ans")
const { Orders} = require("../models/order");

// add new respond to the database Admin
// Add a new manager only in case the name + password no longer exist in the system
const addAdmin = async (addAdmin) => {
  try {
      findAdmin=await Admin.find({name:addAdmin.name});

      var passwordIsValid = 
        addAdmin.password==
        findAdmin.password
     
    if(findAdmin.length==0&&!passwordIsValid)
    console.log("new admin",addAdmin)
    return await Admin.create( addAdmin);
    return findAdmin;
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database Contact
// Add a new contact only if the email address does not exist in the system
const addContact = async (addContact) => {
  try {
    findConact=await Contact.find({email:addContact.email});
    if(findConact.length==0) 
      return Contact.create( addContact);
    else return findConact;
  } catch (error) {
    console.log(error);
  }
};

// add new respond to the database Orders
const addOrders = async (addOrders) => {
  try {
      console.log("hi")
      return Orders.create(addOrders);
  } catch (error) {
    console.log("kk",error);
  }
};
// add new respond to the database Questions_ans
const addQuestionAns = async (respond) => {
  try {
    return     Questions_ans
    .create( respond);
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database Question
const addQuestion = async (respond) => {
  try {
    return Question.create( respond);
  } catch (error) {
    console.log(error);
  }
};

// add new respond to the database Admin
const getAdmin = async () => {
  try {
    return Admin.find( {});
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database RespondsList
const getRespond = async () => {
  try {
    return RespondsList.find( {});
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database Contact
const getContact = async () => {
  try {
    return Contact.find( {});
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database Orders
const getOrders = async () => {
  try {
    return Orders.find( {});
  } catch (error) {
    console.log(error);
  }
};
// add new respond to the database Questions_ans
const getQuestionAns = async () => {
  try {
    return Questions_ans.find( {});
  } catch (error) {
    console.log(error);
  }
};

// add new respond to the database Question
const getQuestion = async () => {
  try {
    return Question.find( {});
  } catch (error) {
    console.log(error);
  }
};

const addRespond = async (respond) => {
  try {
    return RespondsList.create( respond);
  } catch (error) {
    console.log(error);
  }
};

module.exports = {
  addAdmin,
  addContact,
  addOrders,
  addRespond,
  addQuestionAns,
  addQuestion,
  getAdmin,
  getContact,
  getOrders,
  getRespond,
  getQuestionAns,
  getQuestion
};
