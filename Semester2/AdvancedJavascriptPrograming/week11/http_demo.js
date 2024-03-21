"use strict";

const http = require("http");

const server = http.createServer((req, res) => {
   res.setHeader("Content-Type", "text/plain");
   res.write("Hello, this is a simple HTTP server!");
   console.log(req.url);
   res.end();
});

const port = 3000;

server.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});