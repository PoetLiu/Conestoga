"use strict";

$(document).ready( () => {
    $("#pickup_date").focus();
    $("#reservation_form").on("submit", validateForm);
}); // end ready

const emailPattern = /\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b/;

const validateForm = (evt) => {
    clearMsg();
    
    let valid = true;
    const pickupDate = trimGetVal("#pickup_date");
    
    if (pickupDate == '') {
        valid = false;
        displayRequiredMsg("#pickup_date");
    }
    
    const days = trimGetVal("#days");
    if (days == '') {
        valid = false;
        displayRequiredMsg("#days");
    } else if (!$.isNumeric(days)) {
        valid = false;
        displayMsg("#days", "Must be numeric.");
    }
    
    const name = trimGetVal("#name");
    if (name == '') {
        valid = false;
        displayRequiredMsg("#name");
    }
    
    const email = trimGetVal("#email");
    if (email == '') {
        valid = false;
        displayRequiredMsg("#email");
    } else if (!email.match(emailPattern)) {
        valid = false;
        displayMsg("#email", "must be a valid email address.");
    }
    
    const phone = trimGetVal("#phone");
    if (phone == '') {
        valid = false;
        displayRequiredMsg("#phone");
    }
    
    if (!valid) {
        evt.preventDefault();
    }
}

// trim value text and return the trimed text.
const trimGetVal = (sel) => {
    const valTrimed = $.trim($(sel).val());
    $(sel).val(valTrimed);
    return valTrimed;
}

const displayRequiredMsg = (sel) => {
    displayMsg(sel, "this field is required.");
}

const displayMsg = (sel, msg) => {
    $(sel).next("span").text(msg);
}

const clearMsg = () => {
    displayMsg("#pickup_date", '');
    displayMsg("#days", '');
    displayMsg("#name", '');
    displayMsg("#email", '');
    displayMsg("#phone", '');
}

