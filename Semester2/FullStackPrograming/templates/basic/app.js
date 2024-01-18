console.log("App is starting..");

// create express server
const express = require("express");
const app = new express();

const port = 9090;
app.listen(port, () => {
    console.log("App is listening on port " + port);
});

// define routes
app.get("/home", (req, res) => {
    res.send(`
        <h1 style="color: red; text-align: center">Welocm form Home App !!</h1>
    `);
});