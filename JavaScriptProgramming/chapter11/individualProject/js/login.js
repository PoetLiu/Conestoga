/* 
  Name: Peng Liu
  Student Number: 8903532
 */
$(document).ready(() => {
	$.validator.setDefaults({
		submitHandler: function () {
			// don't submit
			login();
		}
	});

	// validate loginForm on keyup and submit
	$("#loginForm").validate({
		rules: {
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 5
			},
		},
		messages: {
			email: "Please enter a valid email address",

			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
		}
	});

	$("#username").focus();
});

const login = () => {
	// we don't have a backend service, so assume it's valid for simplicity.
	window.location.href = "./index.html";
}