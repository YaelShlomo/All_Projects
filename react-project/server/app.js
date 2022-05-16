const cors = require("cors");
const express = require("express");
const bodyParser = require("body-parser");
const router = require("express").Router();
const controller = require("./controllers/controller");
const { connect } = require("./connect");
connect();
const app = express();
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.post("/addRespondsList", controller.addRespond);
app.post("/addOrders", controller.addOrders);
app.post("/addQuestionAns", controller.addQuestionAns);
app.post("/addQuestion", controller.addQuestion);
app.post("/addAdmin", controller.addAdmin);
app.post("/addContact", controller.addContact);
app.post("/adminLogin",controller.adminLogin)
app.get("/getRespondsList", controller.getRespond);
app.get("/getOrders", controller.getOrders);
app.get("/getQuestionAns", controller.getQuestionAns);
app.get("/getQuestion", controller.getQuestionAns);
app.get("/getAdmin", controller.getAdmin);
app.get("/getContact", controller.getContact);



app.listen(5000, () => {});
