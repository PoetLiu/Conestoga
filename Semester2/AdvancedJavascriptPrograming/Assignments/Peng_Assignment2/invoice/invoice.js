"use strict";

const calculateDiscount = (customer, subtotal) => {
    if (customer == "reg") {
        if (subtotal >= 100 && subtotal < 250) {
            return .1;
        } else if (subtotal >= 250 && subtotal < 500) {
            return  .25;
        } else if (subtotal >= 500) {
            return .3;
        } else {
            return 0;
        }        
    }
    else if (customer == "loyal") {
        return .3;        
    }
    else if (customer == "honored") {
        if (subtotal < 500) {
            return .4;
        }
        else {
            return .5;
        }    
    }
};

// format date object to a string with MM/DD/YYYY format.
const formatDate = (date) => {
    let year = date.getFullYear();
    // month is 0-indexed, so add 1 there.
    let month = date.getMonth() + 1;
    let day = date.getDate();
    return month.toString().padStart(2, '0') + "/" +
        day.toString().padStart(2, '0') + "/" +
        year.toString();
}

// add days to date and then return a new date.
const addDaysToDate = (date, days) => {
    let newDay = new Date(date);
    newDay.setDate(newDay.getDate() + days);
    return newDay;
}

$( document ).ready( () => {

    $("#calculate").click( () => {
        const customerType = $("#type").val();
        let subtotal = $("#subtotal").val();
        subtotal = parseFloat(subtotal);
        if ( isNaN(subtotal) || subtotal <= 0) {
            alert("Subtotal must be a number greater than zero.");
            $("#clear").click();
            $("#subtotal").focus();
            return;
        }
        
        let invoiceDate = $("#invoice_date").val();
        let invoiceDateObj = new Date(invoiceDate);
        if (invoiceDate != "" && isNaN(invoiceDateObj)) {
            alert("Invoice Date must be a valid date.");
            $("#clear").click();
            $("#invoice_date").focus();
            return;
        }
        if (invoiceDate == "") {
            invoiceDateObj = new Date();
        }
        invoiceDate = formatDate(invoiceDateObj);
        const dueDate = addDaysToDate(invoiceDateObj, 30);

        const discountPercent = calculateDiscount(customerType, subtotal);
        const discountAmount = subtotal * discountPercent;
        const invoiceTotal = subtotal - discountAmount;
        
        $("#invoice_date").val(invoiceDate);
        $("#subtotal").val( subtotal.toFixed(2) );
        $("#percent").val( (discountPercent * 100).toFixed(2) );
        $("#discount").val( discountAmount.toFixed(2) );
        $("#total").val(  invoiceTotal.toFixed(2) );
        $("#due_date").val(formatDate(dueDate));

        // set focus on type drop-down when done  
        $("#type").focus();

    });
    
    $("#clear").click( () => {

        $("#type").val("reg");
        $("#subtotal").val("");
        $("#invoice_date").val("");
        $("#percent").val("");
        $("#discount").val("");
        $("#total").val("");
        $("#due_date").val("");

        // set focus on type drop-down when done
        $("#type").focus();
    })

    // set focus on type drop-down on initial load
    $("#type").focus();
});














