const $ = selector => document.querySelector(selector);
const processEntries = () => {
    // get form controls to check for validity
    const email = $("#email_address");
    const phone = $("#phone");
    const country = $("#country");
    const terms = $("#terms");
    // create array for error messages
    const msgs = [];
    // check user entries for validity
    if (email.value == "") {
        msgs[msgs.length] = "Please enter an email address.";
    }
    if (phone.value == "") {
        msgs[msgs.length] =
            "Please enter a mobile phone number.";
    }
    if (country.value == "") {
        msgs[msgs.length] = "Please select a country.";
    }
    if (terms.checked == false) {
        msgs[msgs.length] =
            "You must agree to the terms of service.";
    }
    // submit the form or notify user of errors
    if (msgs.length == 0) { // no error messages
        $("form").submit();
    } else {
        displayErrorMsgs(msgs);
    }
};

const resetForm = () => {
    $("form").reset(); // don't need to clear span elements
    $("ul").remove(); // remove the error messages
    $("#email_address").focus();
};
document.addEventListener("DOMContentLoaded", () => {
    $("#register").addEventListener("click", processEntries);
    $("#reset_form").addEventListener("click", resetForm);
    $("#email_address").focus();
});

const displayErrorMsgs = msgs => {
    // create new ul tag
    const ul = document.createElement("ul");
    ul.classList.add("messages");
    // create li tag for each error message and add to ul tag
    for (let msg of msgs) {
        const li = document.createElement("li");
        const text = document.createTextNode(msg);
        li.appendChild(text);
        ul.appendChild(li);
    }
    // If no ul element yet, add it before form tag.
    // Otherwise, replace it.
    const node = $("ul");
    if (node == null) {
        const form = $("form");
        form.parentNode.insertBefore(ul, form);
    }
    else {
        node.parentNode.replaceChild(ul, node);
    }
};

document.addEventListener("DOMContentLoaded", () => {
    $("#register").addEventListener("click", processEntries);
    $("#reset_form").addEventListener("click", resetForm);
    $("#email_address").focus();
});

