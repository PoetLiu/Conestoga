console.log("App is starting..");

const express = require("express");
const app = new express();

const path = require("path");

app.use(express.static("public"));

const port = 9090;
app.listen(port, () => {
    console.log("App is listening on port " + port);
});

// define routes
app.get("/", (req, res) => {
    res.send(`
        <h1>Welcome to Home Page</h1>
    `);
});

app.get("/g", (req, res) => {
    res.send(`
        <h1>Welcome to G Page</h1>
    `);
});

app.get("/g2", (req, res) => {
    res.send(`
        <h1>Welcome to G2 Page</h1>
    `);
});

app.get("/dashboard", (req, res) => {
    res.send(`
        <h1>Welcome to Dashboard Page</h1>
    `);
});

app.get("/login", (req, res) => {
    res.send(`
        <h1>Welcome to Login Page</h1>
    `);
});