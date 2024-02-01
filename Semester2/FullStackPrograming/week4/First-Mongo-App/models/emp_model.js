const mongoose = require('mongoose');

const uri = "mongodb+srv://albertliumr:TO5ZyE9PjM9yIxYx@cluster0.idyc212.mongodb.net/LeonsEmployees?retryWrites=true&w=majority";

mongoose.connect(uri)
.then(() => {
    console.log("Connected to MongoDB Successfully.");
}).catch((err) => {
    console.log(`Connection Failed due to errors: ${err}`);
});

const emp_schema = mongoose.Schema({
    name: {type: String, required: true},
    age: {type: Number, required: true},
    contact: {type: String, required: true},
    email: {type: String, required: true},
});

const emp_model = mongoose.model("employee", emp_schema);

module.exports = emp_model;