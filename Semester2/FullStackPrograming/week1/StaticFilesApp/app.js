console.log("Welcome================");

const express = require("express");

const path = require("path");

const app = express();

const port = 8090;
app.listen(port, () => {
    console.log("Server is listening at port " + port);
});

app.use(express.static("public"));

app.get("/", (req, res) => {
    res.send("From default page.");
});

app.get("/home", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "home.html"));
});

app.get("/support", (req, res) => {
    res.sendFile(path.join(process.cwd(), "public", "pages", "support.html"));
});