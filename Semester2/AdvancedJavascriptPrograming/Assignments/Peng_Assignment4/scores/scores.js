"use strict";

const allScores = [];
const allStudents = [];
const displayScores = (scores, scoresString) => {
    let total = 0;
    for (let score of scores) {
        total += score;
    }
    const avg = scores.length > 0 ? total / scores.length : 0;
    $("#avr_score").text(avg.toString());
    
    $("#scores").val(scoresString.join("\n"));
};

$(document).ready( () => {
    $("#add_button").click( () => {
        let valid = true;
        const score = parseInt($("#score").val());
        if (isNaN(score)) {
            valid = false;
        }
        
        const firstName = $("#first_name").val();
        const lastName = $("#last_name").val();
        if (firstName === "" || lastName === "") {
            valid = false;
        }
        
        if (valid) {
            allScores.push(score);
            allStudents.push(
                `${lastName}, ${firstName}: ${score}`    
            );
            displayScores(allScores, allStudents);
        } else {
            return;
        }
        
        // get the add form ready for next entry
        $("#first_name").val( "" );
        $("#last_name").val( "" );
        $("#score").val( "" );
        $("#first_name").focus();
    }); // end click()
    
    $("#clear_button").click( () => {
        allScores.length = 0;
        allStudents.length = 0;

        // remove the score data from the web page
        $("#avr_score").val( "" );
        $("#scores").val( "" );

        $("#first_name").focus();
    }); // end click()
       
    $("#sort_button").click( () => {
        allStudents.sort((a, b) => {
            const aLastName = a.split(",").pop();
            const bLastName = b.split(",").pop();
            return aLastName.localeCompare(bLastName);
        });
        
        displayScores(allScores, allStudents);
    }); // end click()
    
    $("#first_name").focus();
}); // end ready()
















