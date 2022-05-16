const service = require("../services/service");
var jwt = require("jsonwebtoken");
const { Admin ,Contact} = require("../models/users");
const config = require("../config/auth.config");

// Add admin to the system
const addAdmin = async (req, res) => {
  try {
    const respond = await service.addAdmin(req.body);
    console.log(addAdmin);
    return res.status(200).json(addAdmin);
  } catch (err) {
    return res.status(500).send(err);
  }
};
// Add contact to the system
const addContact = async (req, res) => {
  try {
    const addContact = await service.addContact(req.body);
    console.log(addContact);
    return res.status(200).json(addContact);
  } catch (err) {
    return res.status(500).send(err);
  }
};
// Add respond to the system
const addRespond = async (req, res) => {
  try {
    const respond = await service.addRespond(req.body);
    console.log(respond);
    return res.status(200).json(respond);
  } catch (err) {
    return res.status(500).send(err);
  }
};
// Add order to the system
const addOrders = async (req, res) => {
  try {
    const addOrders = await service.addOrders(req.body);
    console.log("by",addOrders);
    return res.status(200).json("xxx",addOrders);
  } catch (err) {
    return res.status(500).send(err);
  }
};
// Add question to the system
const addQuestion = async (req, res) => {
  try {
    const addQuestion = await service.addQuestion(req.body);
    console.log(addQuestion);
    return res.status(200).json(addQuestion);
  } catch (err) {
    return res.status(500).send(err);
  }
};
const addQuestionAns = async (req, res) => {
  try {
    const addQuestionAns = await service.addQuestionAns(req.body);
    console.log(addQuestionAns);
    return res.status(200).json(addQuestionAns);
  } catch (err) {
    return res.status(500).send(err);
  }
};
const adminLogin = async (addAdmin,res) => {
  console.log("in lo");
  try {
    addAdmin=addAdmin.body
    console.log("add admin",addAdmin);
      findAdmin=await Admin.find({name:addAdmin.name});
      if(findAdmin.lengh==0)
      return res.status(401).send({
        accessToken: null,
        message: "שם משתמש אינו קים.",
      });
      console.log("find admin",findAdmin);
      let passwordIsValid =findAdmin.find((admin)=>admin.password==addAdmin.password)
      if (passwordIsValid==undefined) {
        return res.status(401).send({
          accessToken: null,
          message: "הסיסמה שגויה.",
        });
      }

    if(passwordIsValid!=undefined){
console.log("status 200");
    let token = jwt.sign({ id: findAdmin._id }, config.secret, {
      expiresIn: 86400,
    });
  return  res.status(200).send({
      id: findAdmin._id,
     name: findAdmin.username,
      email: findAdmin.email,
      phone_number:findAdmin.phone_number,
      isAdmin: true,
      accessToken: token,
    });
   }
  } catch (error) {
    console.log(error);
  }
};

//return all the admins from the DB
const getAdmin = async (req, res) => {
  try {
    const admins = await service.getAdmin();
    return res.status(200).json(admins);
  } catch (err) {
    return res.status(500).send(err);
  }
};
//return all the contacts from the DB
const getContact = async (req, res) => {
  try {
    const  Contacts = await service.getContact();
    return res.status(200).json( Contacts);
  } catch (err) {
    return res.status(500).send(err);
  }
};
//return all the responds from the DB
const getRespond = async (req, res) => {
  try {
    const respond = await service.getRespond();
    return res.status(200).json(respond);
  } catch (err) {
    return res.status(500).send(err);
  }
};
//return all the orders from the DB
const getOrders = async (req, res) => {
  try {
    const orders = await service.getOrders();
    return res.status(200).json(orders);
  } catch (err) {
    return res.status(500).send(err);
  }
};
//return all the questions from the DB
const getQuestion = async (req, res) => {
  try {
    const question = await service.getQuestion();
    return res.status(200).json(question);
  } catch (err) {
    return res.status(500).send(err);
  }
};
//return all the questions_ans from the DB
const getQuestionAns = async (req, res) => {
  try {
    const questionAns = await service.getQuestionAns();
    return res.status(200).json(questionAns);
  } catch (err) {
    return res.status(500).send(err);
  }
};


module.exports = {
  addAdmin,
  adminLogin,
  addContact,
  addOrders,
  addRespond,
  addQuestion,
  addQuestionAns,
  getAdmin,
  getContact,
  getOrders,
  getRespond,
  getQuestion,
  getQuestionAns,

};
