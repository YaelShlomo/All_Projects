const mongoose = require("mongoose");
mongoose.models = {};

// Order includes name, email, city and date
const Orders = mongoose.model(
  "orders",
    new mongoose.Schema({
     name:{type:String},
     email:{type:String},
     city:{type:String},
     date:{type:String}
   })
);

module.exports = {
    Orders
};
