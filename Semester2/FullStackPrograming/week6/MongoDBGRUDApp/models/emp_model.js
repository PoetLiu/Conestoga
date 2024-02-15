const mongoose = require('mongoose');

const uri = "mongodb+srv://albertliumr:TO5ZyE9PjM9yIxYx@cluster0.idyc212.mongodb.net/Cestar_Employees?retryWrites=true&w=majority";

mongoose.connect(uri)
.then(() => {
    console.log("Connected to MongoDB Successfully.");
}).catch((err) => {
    console.log(`Connection Failed due to errors: ${err}`);
});

const emp_schema = mongoose.Schema({
    name: {type: String, required: true},
    contact: {type: String, required: true},
    salary: {type: Number, required: true},
    email: {type: String, required: true},
    joiningDate: {type: String, required: true},
});

const emp_model = mongoose.model("Cestar_Worker", emp_schema);

module.exports = emp_model;