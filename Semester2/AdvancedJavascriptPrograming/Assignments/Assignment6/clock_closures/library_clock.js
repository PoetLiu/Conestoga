"use strict";

export const createClock = (hourSpan, minuteSpan, secondSpan, ampmSpan) => {
    // private state
    const hourEle = $(hourSpan);
    const minuteEle = $(minuteSpan);
    const secondEle = $(secondSpan);
    const ampmEle = $(ampmSpan);
    
    const padSingleDigit = num => num.toString().padStart(2, "0");
    const displayCurrentTime = () => {
        const now = new Date();
        let hours = now.getHours();
        let ampm = "AM"; // set default value
    
        // correct hours and AM/PM value for display
        if (hours > 12) { // convert from military time
            hours = hours - 12;
            ampm = "PM";
        } else { // adjust 12 noon and 12 midnight
            switch (hours) {
                case 12: // noon
                    ampm = "PM";
                    break;
                case 0:  // midnight
                    hours = 12;
                    ampm = "AM";
            }
        }
        hourEle.text(hours);
        minuteEle.text( padSingleDigit(now.getMinutes()) );
        secondEle.text( padSingleDigit(now.getSeconds()) );
        ampmEle.text(ampm);
    };
    // public methods
    return {
        start: () => {
            // set initial clock display and then set interval timer to display
            // new time every second. Don't store timer object because it 
            // won't be needed - clock will just run.
            displayCurrentTime();
            setInterval(displayCurrentTime, 1000);
        }
    }
};







