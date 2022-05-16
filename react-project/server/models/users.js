const mongoose = require("mongoose");
mongoose.models = {};

const Admin = mongoose.model(
  "admins",
    new mongoose.Schema({
     name:{type:String},
     email:{type:String},
     phone:{type:String},
     password:{type:String},

   })
);
const Contact = mongoose.model(
    "contacts",
      new mongoose.Schema({
       name:{type:String},
       email:{type:String},
       phone_number:{type:Number},
 
     })
  );
module.exports = {
    Admin,
    Contact
};
