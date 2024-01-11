const $ = selector => document.querySelector(selector);

const processEntry = () => {
    const value = parseInt($("#cents").value);
    if (value >= 0 && value <= 99) {
        makeChange(value);
        $("#error").textContent = "";
    } else {
        $("#error").textContent = "amount must be a number between 0 and 99";
    }
}

/* value of change and id of html elements. */
const changes = [
    { value: 25, id: "quarters" }, 
    { value: 10, id: "dimes" }, 
    { value: 5, id: "nickels" }, 
    { value: 1, id: "pennies" }
];
const makeChange = (value) => {
    for (let i = 0; i < changes.length; i++) {
        const num = Math.floor(value / changes[i].value);
        $("#" + changes[i].id).value = num;
        value %= changes[i].value;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    $("#calculate").addEventListener("click", processEntry);
});