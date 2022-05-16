const mongoose = require("mongoose");
const connect = () => {
  const url='mongodb+srv://dbUser:ys1234y$@cluster0.46uij.mongodb.net/Photo?retryWrites=true&w=majority';
  try {
    const mongoose = require("mongoose");
          mongoose.connect(url
      , {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useFindAndModify: false,
        useCreateIndex: true,
        autoIndex: true,
      }).then(console.log("success"));
         } catch (error) {
    console.log(error);
  }
};


module.exports = {
  connect,
};
