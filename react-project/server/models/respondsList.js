const mongoose = require("mongoose");
mongoose.models = {};

const RespondsList = mongoose.model(
  "responds_lists",
    new mongoose.Schema({
     comment:{type:String},
     value:{type:String},
     imgSrc:{type:String},
   })
);

module.exports = {
    RespondsList
};
