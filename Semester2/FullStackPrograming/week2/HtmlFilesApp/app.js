console.log("App is starting..");

// create express server
const express = require("express");
const app = new express();

// require path package to get abusolute paths for static files.
const path = require("path");

app.use(express.static("public"));

const port = 9090;
app.listen(port, () => {
    console.log("App is listening on port " + port);
});

// define routes
app.get("/", (req, res) => {
    res.send(`
        <h1 style="color: red; text-align: center">Welocm form Home App !!</h1>
    `);
});

app.get("/home", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "home.html"));
});

app.get("/about", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "about.html"));
});

app.get("/support", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "support.html"));
});

app.get("/contact", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "contact.html"));
});