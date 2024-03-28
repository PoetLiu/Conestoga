const express = require("express");
const fs = require("fs");

const app = express();
const PORT = 8080;

app.get('/', (req, res) => {
    fs.readFile("index.html", (err, data) => {
        res.setHeader("Content-Type", "text/html");
        res.setHeader("Cache-Control", "private,no-cache,no-store,must-revalidate");
        if (err) {
            res.send(err);
        } else {
            res.send(data);
        }
    })
});

app.get("/api/weather/:city", (req, res) => {
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "private,no-cache,no-store,must-revalidate");

    fs.readFile(`weather/${req.params.city}.json`, (err, data) => {
        if (err) {
            res.send(JSON.stringify({
                "status": "error",
                "error": "City not supported."
            }));
        } else {
            const weatherData = JSON.parse(data);
            const randomIndex = Math.floor(Math.random() * weatherData.length);
            const currentWeather = weatherData[randomIndex];
            currentWeather.status = "ok";
            res.send(JSON.stringify(currentWeather));
        } 
    });

});

app.listen(PORT, (err) => {
    console.log(`The server is running: http://localhost:${PORT}`);
});