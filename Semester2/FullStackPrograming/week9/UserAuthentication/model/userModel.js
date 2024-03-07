import mongoose from "mongoose";

const uri = "mongodb+srv://albertliumr:TO5ZyE9PjM9yIxYx@cluster0.idyc212.mongodb.net/CostCoUsers?retryWrites=true&w=majority";
mongoose.connect(uri)
.then(() => {
    console.log("Connected to MongoDB Successfully.");
}).catch((err) => {
    console.log(`Connection Failed due to errors: ${err}`);
});

const userSchema = mongoose.Schema({
    name: {type: String, required: true},
    email: {type: String, required: true},
    pwd: {type: String, required: true}
});

const userModel = mongoose.model("GeneralUser", userSchema);

export default userModel;