"use strict"

$(document).ready(() => {
	$("#start_timer").click(startTimer);
	$("#totalTime").focus();
	$("#progressbar").progressbar({
		value: 0,
		change: changeTimerDisplay,
		complete: stopTimer
	});
});

const validate = () => {
	let totalTime = $("#time").val();
	let interval = $("#interval").val();
	let isValid = true;
	// validate the time
	if (totalTime == "") {
		$("#time_error").text("This field is required.");
		isValid = false;
	} else if (isNaN(totalTime)) {
		$("#time_error").text("Time must be a number.");
		isValid = false;
	} else {
		$("#time_error").text("");
	}

	// validate the interval
	if (interval == "") {
		$("#interval_error").text("This field is required.");
		isValid = false;
	} else if (isNaN(interval)) {
		$("#interval_error").text("Interval must be a number.");
		isValid = false;
	} else {
		$("#interval_error").text("");
	}

	return isValid;
}

const resetTimerDisplay = () => {
	$("#complete span").text("");
	$("#progressbar").progressbar("value",  0);
}

const changeTimerDisplay = () => {
    $("#complete span").text( $("#progressbar").progressbar( "value" ) + "%" );
}


const startTimer = () => {
	resetTimerDisplay();
	// if it's failed to validate, we don't start timer.
	if (!validate()) {
		return;
	}

	// disable the start button in case of errors.
	$("#start_timer").prop("disabled", true);
	tickTimer();
}

let timer = null;
const tickTimer = () => {
	let totalTime = parseInt($("#time").val());
	let interval = parseInt($("#interval").val());

	let elapsedTime = 0;
	timer = setInterval(() => {
		elapsedTime += interval;
		const progressVal = Math.floor((elapsedTime / totalTime) * 100);
		$("#progressbar").progressbar("value",  progressVal);
	},
	interval * 1000);
}

const stopTimer = () => {
	clearInterval(timer);
	$("#start_timer").prop("disabled", false);
	$("#complete span").text("Time is up!");
}