"use strict";
const $ = selector => document.querySelector(selector);

/*********************
*  helper functions  *
**********************/
const calculateCelsius = temp => (temp-32) * 5/9;
const calculateFahrenheit = temp => temp * 9/5 + 32;

const toggleDisplay = (label1Text, label2Text) => {
	$("#degree_label_1").textContent = label1Text;
	$("#degree_label_2").textContent = label2Text;
	$("#degrees_computed").value = "";

	// move focus and select
	$("#degrees_entered").select();
}

/****************************
*  event handler functions  *
*****************************/
const convertTemp = () => {   
	const value = $("#degrees_entered").value;
	if (isNaN(value)) {
		$("#message").textContent = "You must enter a valid number for degrees";	
		return;
	} else {
		$("#message").textContent = "";	
	}
	let result;
	if ($("#to_celsius").checked) {
		result = calculateCelsius(value);
	} else {
		result = calculateFahrenheit(value);
	}
	$("#degrees_computed").value = Math.floor(result);
};

const toCelsius = () => toggleDisplay("Enter F degrees:", "Degrees Celsius:");
const toFahrenheit = () => toggleDisplay("Enter C degrees:", "Degrees Fahrenheit:");

document.addEventListener("DOMContentLoaded", () => {
	// add event handlers
	$("#convert").addEventListener("click", convertTemp);
    $("#to_celsius").addEventListener("click", toCelsius);
    $("#to_fahrenheit").addEventListener("click", toFahrenheit);
	
	// move focus
	$("#degrees_entered").focus();
});