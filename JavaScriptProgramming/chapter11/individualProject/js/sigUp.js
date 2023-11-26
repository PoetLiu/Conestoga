/* 
  Name: Peng Liu
  Student Number: 8903532
 */
$(document).ready(() => {
	$.validator.setDefaults({
		submitHandler: function () {
			signUp();
		}
	});

	// validate signup form on keyup and submit
	$("#signUpForm").validate({
		rules: {
			username: {
				required: true,
				minlength: 2
			},
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 5
			},
			confirmPassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
		},
		messages: {
			username: {
				required: "Please enter a username",
				minlength: "Your username must consist of at least 2 characters"
			},
			email: "Please enter a valid email address",

			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			confirmPassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
		}
	});

	$("#username").focus();

	$("#successDialog").dialog({
		autoOpen: false,
		resizable: false,
		height: "200",
		width: 400,
		modal: true,
		buttons: {
			"Login now": function () {
				$(this).dialog("close");
				window.location.href = "./index.html";
			},
		}
	});

});

const signUp = () => {
	$("#successDialog").dialog("open");
}