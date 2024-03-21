"use strict"

console.log("Hello, this is a simple Express server!");
const express = require("express");

const app = express();

const port = 3000;
app.listen(port, (err) => {
    console.log(`Server is running on http://localhost:${port}`);
});

app.get("/", (req, res) => {
    const html = `
    <doctype html>
    <html>
        <body>
            <p>Hello, this is a simple Express server!</p>
            <a href="/about">About</a>
        </body>
    </html>
    `;

    res.send(html);
});

app.get("/about", (req, res) => {
    const html = `
    <doctype html>
    <html>
        <body>
            <p>This is the about page.</p>
            <a href="/">Home</a>
        </body>
    </html>
    `;

    res.send(html); 
});