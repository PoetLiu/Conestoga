"use strict";

$(document).ready( () => {
 // display data from session storage
 displayProfile(["email", "phone", "postal", "dob"]);
 
 $("#back").on("click", goBack); // end of click()
 
}); // end of ready()

const getData = (name) => {
    return sessionStorage.getItem(name);
}

const displayData = (name) => {
    const value = getData(name);
    $(`#${name}`).text(value);
}

const displayProfile = (names) => {
    for (let name of names) {
        displayData(name);
    }
}

const goBack = () => {
    window.history.back();
}





