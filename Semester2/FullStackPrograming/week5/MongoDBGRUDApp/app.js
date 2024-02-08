console.log("Welcome from MongoDBCrudApp");

const express = require("express");

const app = express();

const PORT = 7080;

app.listen(PORT, () => {
    console.log("Server is listening at port: " + PORT);
});