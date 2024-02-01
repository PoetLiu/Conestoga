console.log("App is starting..");

const express = require("express");
const app = new express();

const path = require("path");

app.set("view-engine", "ejs");
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
    res.render(`g.ejs`);
});

app.get("/g2", (req, res) => {
    res.render(`g2.ejs`);
});

app.get("/dashboard", (req, res) => {
    res.render(`dashboard.ejs`);
});

app.get("/login", (req, res) => {
    res.render(`login.ejs`);
});