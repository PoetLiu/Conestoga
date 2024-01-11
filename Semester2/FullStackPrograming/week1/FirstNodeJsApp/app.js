console.log("Welcom From First Node Application !!!!");

const express = require("express");

const app = express();

const port = 7080;
app.listen(port, () => {
    console.log("Server is listening at port " + port)
})


app.get("/home", (req, res) => {
    res.send(`
    <body style="background-color: blanchedalmond;">
    <nav>
        <ul>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/home">Home</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/about">About</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/support">Support</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/contact">Contact</a></li>
        </ul>
    </nav>

    <h2 style="color: red; text-align: center;">Welcome From Home Page !!!</h2>

    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;"></div>

    </body>
    `);
})

app.get("/about", (req, res) => {
    res.send(`
    <body style="background-color: #138D75;"> 
    <nav>
    <ul>
        <li style="float: left; margin-left: 50px; list-style: none;"><a href="/home">Home</a></li>
        <li style="float: left; margin-left: 50px; list-style: none;"><a href="/about">About</a></li>
        <li style="float: left; margin-left: 50px; list-style: none;"><a href="/support">Support</a></li>
        <li style="float: left; margin-left: 50px; list-style: none;"><a href="/contact">Contact</a></li>
    </ul>
    </nav>

    <h2 style="color: yellow; text-align: center;">Welcome from about us page !!!</h2>

    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    </body>
    `);
})

app.get("/support", (req, res) => {
    res.send(`
    <body style="background-color: #138D75;"> 
    <nav>
        <ul>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/home">Home</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/about">About</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/support">Support</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/contact">Contact</a></li>
        </ul>
    </nav>

    <h2 style="color: yellow; text-align: center;">Welcome from support page !!!</h2>

    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    </body> 
    `);
})

app.get("/contact", (req, res) => {
    res.send(`
    <body style="background-color: #138D75;"> 
    <nav>
        <ul>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/home">Home</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/about">About</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/support">Support</a></li>
            <li style="float: left; margin-left: 50px; list-style: none;"><a href="/contact">Contact</a></li>
        </ul>
    </nav>

    <h2 style="color: yellow; text-align: center;">Welcome from Contact us page !!!</h2>

    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    <div style="height: 400px; width: 350px; border: 5px solid green; float: left; margin-left: 25px;background-color: yellow;"></div>
    </body> 
    `);
})