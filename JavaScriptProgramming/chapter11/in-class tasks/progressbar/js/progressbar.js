let totalTime = 10;
let time = 0;
let interval = 1;

$(document).ready(function () {
    $("#startTimer").on("click", () => {
        setInterval(() => {
            time += interval;
            $("#progressbar").progressbar({
                value:  
            }), 
            interval*1000
        })
    });
});