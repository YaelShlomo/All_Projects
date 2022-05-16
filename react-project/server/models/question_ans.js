const mongoose = require("mongoose");
mongoose.models = {};

const Questions_ans = mongoose.model(
  "questions_ans",
    new mongoose.Schema({
     question:{type:String},
     answer:{type:String},
   })
);

const Question = mongoose.model(
    "questions",
      new mongoose.Schema({
        question:{type:String},
     })
  );

module.exports = {
    Question,
    Questions_ans
};
