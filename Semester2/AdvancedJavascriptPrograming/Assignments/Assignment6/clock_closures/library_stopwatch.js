"use strict";

export const createStopwatch = (minuteSpan, secondSpan, msSpan) => {
    // private state
    //stop watch timer variable and elapsed time object
    let stopwatchTimer = null;
    let elapsed = { minutes:0, seconds:0, milliseconds:0 };

    const padSingleDigit = num => num.toString().padStart(2, "0");

    const tickStopwatch = () => {    
        // increment milliseconds by 10 milliseconds
        elapsed.milliseconds = elapsed.milliseconds + 10;

        // if milliseconds total 1000, increment seconds by one and reset milliseconds to zero
        if (elapsed.milliseconds == 1000) {
            elapsed.seconds++;
            elapsed.milliseconds = 0;
        }
        // if seconds total 60, increment minutes by one and reset seconds to zero
        if (elapsed.seconds == 60) {
            elapsed.minutes++;
            elapsed.seconds = 0;
        }

        //display new stopwatch time
        $(minuteSpan).text( padSingleDigit(elapsed.minutes) );
        $(secondSpan).text( padSingleDigit(elapsed.seconds) );
        $(msSpan).text(elapsed.milliseconds);
    };
    // public methods
    return {
        start: () => {
            // do first tick of stop watch and then set interval timer to tick 
            // stop watch every 10 milliseconds. Store timer object in stopwatchTimer 
            // variable so next two functions can stop timer.
            tickStopwatch();
            stopwatchTimer = setInterval(tickStopwatch, 10);
        },
        stop: () => {
            // stop timer
            clearInterval(stopwatchTimer);
        },
        reset: () => {  
            // stop timer
            clearInterval(stopwatchTimer);

            // clear elapsed object and stopwatch display
            elapsed = { minutes:0, seconds:0, milliseconds:0 }; 

            $(minuteSpan).text("00");
            $(secondSpan).text("00");
            $(msSpan).text("000");
        }
    }
};


