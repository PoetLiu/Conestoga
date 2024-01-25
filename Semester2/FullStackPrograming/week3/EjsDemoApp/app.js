console.log("Welcom EJS！");

const express = require("express");
const app = express();

// write this line to use ejs engine.
app.set("view-engine", "ejs");

// write this line to use public folder for static files.
app.use(express.static("public"));

const port = 6080;
app.listen(port, () => {
    console.log("Server is listening at port: " + port);
});

app.get("/", (req, res) => {
    res.send(`
        <h2>Welcom From EJS Demo</h2>
    `);
});

app.get("/home", (req, res) => {
    res.render(`home.ejs`);
});

app.get("/about", (req, res) => {
    const company = "Conestoga Apps";
    res.render(`about.ejs`, {name: company});
});

app.get("/contact", (req, res) => {
    res.render(`contact.ejs`);
});

app.get("/support", (req, res) => {
    res.render(`support.ejs`);
});