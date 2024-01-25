"use strict";

$( document ).ready( () => { 

    $("#countdown").click( () => {
        const eventName = $("#event").val();
        const eventDate = $("#date").val();  
        const messageLbl = $("#message");  
        
        // make sure user entered task and event date 
        if (eventName.length == 0 || eventDate.length == 0) {
            messageLbl.text( "Please enter both a name and a date." );
            return;
        }  
      
        // make sure event date string has two slashes 
        const dateParts = eventDate.split("/");
        if (dateParts.length != 3) {   
            messageLbl.text( "Please enter the date in MM/DD/YYYY format." );
            return;
        } 
        // make sure event date string has a 4-digit year
        const year = eventDate.substring(eventDate.length - 4); 
        if (isNaN(year)) {
            messageLbl.text( "Please enter the date in MM/DD/YYYY format." );
            return;
        }     
        
        // make sure the month string is a number and within valid range.
        const month = parseInt(dateParts[0]);
        if (isNaN(month)) {
            messageLbl.text( "Please enter a valid month number." );
            return;
        }
        if (month < 1 || month > 12) {
            messageLbl.text( "Please enter a valid month between 1 and 12." );
            return;
        }
        
        // make sure the day string is a number and within valid range.
        const day = parseInt(dateParts[1]);
        if (isNaN(day)) {
            messageLbl.text( "Please enter a valid day number." );
            return;
        }
        if (day < 1 || day > getDayMax(year, month)) {
            messageLbl.text( "Please enter a valid day." );
            return;
        }
        
        
        // convert event date string to Date object and check for validity
        let date = new Date(eventDate);
        if (date == "Invalid Date") {
            messageLbl.text( "Please enter the date in MM/DD/YYYY format." );
            return;
        }

        // capitalize each word of event name
        let formattedName = "";
        const words = eventName.split(" ");
        for (const i in words) {
            const firstLetter = words[i].substring(0,1).toUpperCase();
            const word = firstLetter + words[i].substring(1).toLowerCase();
            formattedName += word.padEnd(word.length + 1);
        } 
        formattedName = formattedName.trimEnd();     

        // calculate days
        const today = new Date();
        const msFromToday = date.getTime() - today.getTime();
        const msForOneDay = 24 * 60 * 60 * 1000; // hrs * mins * secs * milliseconds  
        let daysToDate = Math.ceil( msFromToday / msForOneDay ); 

        // create and display message 
        let msg = "";
        date = date.toDateString();
        if (daysToDate == 0) {
            msg = `Hooray! Today is ${formattedName}! (${date})`;
        }
        else if (daysToDate > 0) {
            msg = `${daysToDate} day(s) until ${formattedName}! (${date})`;
        }
        else if (daysToDate < 0) {
            daysToDate = Math.abs(daysToDate);
            msg = `${formattedName} happened ${daysToDate} day(s) ago. 
                  (${date})`;
        }
        messageLbl.text(msg);
    });
    
    $("#event").focus();
});

const isLeapYear = (year) => {
    return (year % 4 == 0 && year % 100 != 0) || 
        year % 400 == 0;
}

const getDayMax = (year, month) => {
    let maxDay = month % 2 > 0 ? 31 : 30;
    if (month == 2) {
        maxDay = isLeapYear(year) ? 29 : 28;
    }
    return maxDay;
}
