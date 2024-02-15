const mongoose = require('mongoose');
const uri = "mongodb+srv://albertliumr:TO5ZyE9PjM9yIxYx@cluster0.idyc212.mongodb.net/assignment2?retryWrites=true&w=majority";

mongoose.connect(uri)
.then(() => {
    console.log("Connected to MongoDB successfully.")
}).catch((err) => {
    console.log(`Connection Failed due to error: \n${err}`);
});

const carDetailsSchema = mongoose.Schema({
    make: {type: String, required: true},
    model: {type: String, required: true},
    year: {type: Number, required: true},
    platno: {type: String, required: true},
});

const userSchema = mongoose.Schema({
    firstName: {type: String, required: true},
    lastName: {type: String, required: true},
    age: {type: Number, required: true},
    licenseNumber: {type: String, required: true},
    carDetails: {type: carDetailsSchema, required: true},
});

const userModel = mongoose.model("user", userSchema);

module.exports =  userModel;