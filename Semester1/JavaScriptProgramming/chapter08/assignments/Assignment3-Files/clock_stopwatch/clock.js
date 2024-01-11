"use strict";
const $ = selector => document.querySelector(selector);

const padSingleDigit = num => num.toString().padStart(2, "0");

const padToThreeDigit = num => num.toString().padStart(3, "0");

const displayCurrentTime = () => {
    const now = new Date();
    const hours = now.getHours() % 12 || 12;
    const ampm = now.getHours() >= 12 ? "PM" : "AM";
    
    $("#hours").textContent = hours;
    $("#minutes").textContent = padSingleDigit(now.getMinutes());
    $("#seconds").textContent = padSingleDigit(now.getSeconds());
    $("#ampm").textContent = ampm;
};


//global stop watch timer variable and elapsed time object
let stopwatchTimer = null;
let elapsedMinutes = 0;
let elapsedSeconds = 0;
let elapsedMilliseconds = 0;

const tickStopwatch = () => {    
    // increment milliseconds by 10 milliseconds
    elapsedMilliseconds += 10;
    
    // if milliseconds total 1000, increment seconds by one and reset milliseconds to zero
    if (elapsedMilliseconds == 1000) {
        elapsedSeconds++;
        elapsedMilliseconds = 0;
    }
    
    // if seconds total 60, increment minutes by one and reset seconds to zero
    if (elapsedSeconds == 60) {
        elapsedMinutes++;
        elapsedSeconds = 0;
    }
    
    //display new stopwatch time
    updateStopwatch();
};

const updateStopwatch = () => {
    $("#s_minutes").textContent = padSingleDigit(elapsedMinutes);
    $("#s_seconds").textContent = padSingleDigit(elapsedSeconds);
    $("#s_ms").textContent = padToThreeDigit(elapsedMilliseconds);
}

// event handler functions
const startStopwatch = evt => {
    // prevent default action of link
    evt.preventDefault();
        
    // do first tick of stop watch and then set interval timer to tick 
    // stop watch every 10 milliseconds. Store timer object in stopwatchTimer 
    // variable so next two functions can stop timer.
    tickStopwatch();
    stopwatchTimer = setInterval(tickStopwatch, 10);
    setState(states.RUNNING);
};

const resumeStopwatch = evt => {
    startStopwatch(evt);
}

const stopStopwatchTimer = () => {
    clearInterval(stopwatchTimer);
}

const stopStopwatch = evt => {
    // prevent default action of link
    evt.preventDefault();
        
    // stop timer
    stopStopwatchTimer();
    setState(states.STOPPED);
};

const resetStopwatch = evt => {
    // prevent default action of link
    evt.preventDefault();
        
    // stop timer
    stopStopwatchTimer();
        
    // reset elapsed variables and clear stopwatch display
    elapsedMinutes = 0;
    elapsedSeconds = 0;
    elapsedMilliseconds = 0; 
    updateStopwatch();

    setState(states.STANDBY);
};

const states = {
    STANDBY: 0,
    RUNNING: 1,
    STOPPED: 2
}

const setState = (newState) => {
    let start = false;
    let resume = false;
    let stop = false;
    let reset = false;

    switch (newState) {
        case states.STANDBY:
            start = true;
            break;
        case states.RUNNING:
            stop = true;
            break;
        case states.STOPPED:
            resume = true;
            reset = true;
            break;
    }

    toggleButton("#start", start);
    toggleButton("#resume", resume);
    toggleButton("#stop", stop);
    toggleButton("#reset", reset);
}

const toggleButton = (id, show) => {
    if (show) {
        $(id).classList.remove("hide");
    } else {
        $(id).classList.add("hide");
    }
}

document.addEventListener("DOMContentLoaded", () => {
	// set initial clock display and then set interval timer to display
    // new time every second. Don't store timer object because it 
    // won't be needed - clock will just run.
    displayCurrentTime();
    setInterval(displayCurrentTime, 1000);
	
	// set up stopwatch event handlers
    $("#start").addEventListener("click", startStopwatch);
    $("#resume").addEventListener("click", resumeStopwatch);
    $("#stop").addEventListener("click", stopStopwatch);
    $("#reset").addEventListener("click", resetStopwatch);
    setState(states.STANDBY);
});
