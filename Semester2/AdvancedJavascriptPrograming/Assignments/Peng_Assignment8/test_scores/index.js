"use strict";

const scores = [];

const displayScores = () => scores.join(", ");

const calculateAverage = () => {
	const total = scores.reduce( (prev, curr) => prev + parseInt(curr), 0);
	return total / scores.length;
};

// load user entries in scores array
// skip the first two arguments.
for (let i = 2; i < process.argv.length; i++) {
	scores.push(process.argv[i]);
}

// display all scores
console.log("All scores: " + displayScores());

// display average score
console.log("Average score: " + calculateAverage());

console.log("Peng, 8903532");
