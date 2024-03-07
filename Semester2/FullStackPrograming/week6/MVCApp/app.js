console.log("Welcome from MongoDBCrudApp");

const express = require("express");

const router = require("./web/web.js");

const app = express();

const PORT = 7080;

app.use(express.static("public"));

app.set("view-engine", "ejs");

// use this line to get the form data from request body.
app.use(express.urlencoded({ extended: true }));

app.listen(PORT, () => {
    console.log("Server is listening at port: " + PORT);
});

app.use(router);