console.log("Welcome to UserAuthentication App.");

import express from "express";
import MongoStore from "connect-mongo";
import session from "express-session";
import router from "./routes/routes.js";

const uri = "mongodb+srv://albertliumr:TO5ZyE9PjM9yIxYx@cluster0.idyc212.mongodb.net/CostCoUsers?retryWrites=true&w=majority";

const session_store = MongoStore.create({
    mongoUrl: uri,
    dbName: "CostCoUsers",
    collectionName: "CostCoSessions"
})

const port = 8080;
const app = express();

app.use(express.urlencoded({extented: true}));
app.use(session({
    secret: "A secret Key to sign the cookie",
    saveUninitialized: false,
    resave: false,
    store: session_store
}));
app.set('view-engine', 'ejs');

app.use(express.static("public"));

app.listen(port, () => {
    console.log("App is listening port:" + port);
});

app.use("/", router);