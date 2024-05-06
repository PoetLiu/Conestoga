const { exit } = require('process');

const fs = require('fs').promises;

const scriptPath = process.argv[1];
console.log("This script's path is: " + scriptPath);

// read filename from the command line arguments.
const filename = process.argv[2];
if (!filename) {
    console.log("ERROR: filename is missing.");
    exit(1);
} else {
    console.log("The filename of the text file is: " + filename);
}

fs.readFile(filename, "utf8") 
    .then(list =>  console.log(list)) 
    .catch(error => console.log(error));