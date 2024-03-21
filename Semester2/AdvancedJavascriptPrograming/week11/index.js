"use strict"

console.log(process.argv);

const fs = require("fs");

function readFile(filePath) {
    fs.readFile(filePath, "utf8", (err, data) => {
        if (err) {
            console.log("Error reading file: ", err);
        } else {
            console.log("File content: ");
            console.log(data);
        }
    });
}

const filePath = "package.json";
/*
 * If the element 2 of process.argv is valid,
 * print the contents of that file. otherwise, 
 * use the filePath constant.
 */
readFile(process.argv[2] ?? filePath);