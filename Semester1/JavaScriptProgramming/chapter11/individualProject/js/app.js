/* 
  Name: Peng Liu
  Student Number: 8903532
 */
$(document).ready(() => {
    $("#focusTime").spinner();
    $("#breakTime").spinner();

    $("#focus").on("click", setToFocus);
    $("#break").on("click", setToBreak);

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Save": saveSettings,
            Cancel: function () {
                dialog.dialog("close");
            }
        }
    });
    $("#settings").button().on("click", function () {
        dialog.dialog("open");
    });


    $("#start").on("click", startTimer);
    $("#stop").on("click", stopTimer);
    $("#resume").on("click", startTimer);
    $("#reset").on("click", resetTimer);

    setToFocus();
    setState(states.STANDBY);
});

const modes = {
    FOCUS: 0,
    BREAK: 1,
}

// -1 means that there is no mode specified.
let mode = -1;
const setToFocus = () => {
    // if it's on focus mode already, ignore it.
    if (mode == modes.FOCUS) {
        return;
    }
    mode = modes.FOCUS;
    $("#focus").addClass("selected");
    $("#break").removeClass("selected");
    resetTotalTime(false);
}

const setToBreak = () => {
    // if it's on break mode already, ignore it.
    if (mode == modes.BREAK) {
        return;
    }
    mode = modes.BREAK;
    $("#break").addClass("selected");
    $("#focus").removeClass("selected");
    resetTotalTime(false);
}

// by default, the focus time is 25min and break time is 5min.
let settings = {
    focusTimeSec: 1500,
    breakTimeSec: 300
};

const saveSettings = () => {
    let valid = true;
    valid = checkNum($("#focusTime"), "focusTime", 1, 99) &&
        checkNum($("#breakTime"), "breakTime", 1, 99);

    if (valid) {
        dialog.dialog("close");
        settings = {
            focusTimeSec: parseInt($("#focusTime").val()) * 60,
            breakTimeSec: parseInt($("#breakTime").val()) * 60
        };
        resetTotalTime(true);
    }
    return valid;
}

function updateTips(t) {
    $(".validateTips")
        .text(t)
        .addClass("ui-state-highlight");
    setTimeout(function () {
        $(".validateTips").removeClass("ui-state-highlight", 1500);
    }, 500);
}


const checkNum = (obj, name, min, max) => {
    const value = parseInt(obj.val());
    if (value >= min && value <= max) {
        return true;
    } else {
        obj.addClass("ui-state-error");
        updateTips("Value of " + name + " must be between " + min + " and " + max + ".");
        return false;
    }
}

let timerIntervalId = null;
const startTimer = (evt) => {
    // prevent defualt aciton
    evt.preventDefault();

    // do first tick and then tick every 1 second.
    tickTimer();
    timerIntervalId = setInterval(tickTimer, 1000);

    // change state to running
    setState(states.RUNNING);
}

let totalTimeSec = 300;
const tickTimer = () => {
    // count down by 1 seconds
    totalTimeSec--;

    if (totalTimeSec == 0) {
        stopTimer();
        setState(states.TIMEOUT);
    }

    updateTimerDisplay(true);
}

const stopTimer = () => {
    // just do stop the timer
    clearInterval(timerIntervalId);
    setState(states.STOPPED);
}

const resetTotalTime = (animate) => {
    totalTimeSec = mode == modes.FOCUS ? settings.focusTimeSec : settings.breakTimeSec;
    updateTimerDisplay(animate);
}

const resetTimer = () => {
    clearInterval(timerIntervalId);
    resetTotalTime();
    setState(states.STANDBY);
    updateTimerDisplay();
}

const padSingleDigit = num => num.toString().padStart(2, "0");

const updateTimerDisplay = (animate) => {
    const mm = padSingleDigit(Math.floor(totalTimeSec / 60));
    const ss = padSingleDigit(totalTimeSec % 60);
    const newText = `${mm}:${ss}`;

    $("#timer").text(newText);
    if (animate) {
        $("#timer").toggleClass("active");
    }
}

const states = {
    STANDBY: 0,
    RUNNING: 1,
    STOPPED: 2,
    TIMEOUT: 3
}

const setState = (newState) => {
    let start = false;
    let resume = false;
    let stop = false;
    let reset = false;
    let focus = false;
    let breakVisiable = false;
    let settings = false;

    switch (newState) {
        case states.STANDBY:
            start = true;
            settings = true;
            focus = true;
            breakVisiable = true;
            break;
        case states.RUNNING:
            settings = false;
            stop = true;
            break;
        case states.STOPPED:
            resume = true;
            reset = true;
            break;
        case states.TIMEOUT:
            reset = true;
            break;
    }

    if (!states.STANDBY) {
        // when it's not standby, it's not allowed to change mode.
        if (mode == modes.FOCUS) {
            focus = true;
        } else {
            breakVisiable = true;
        }
    }

    toggleButton("#focus", focus);
    toggleButton("#break", breakVisiable);
    toggleButton("#settings", settings);
    toggleButton("#start", start);
    toggleButton("#resume", resume);
    toggleButton("#stop", stop);
    toggleButton("#reset", reset);
}

const toggleButton = (id, show) => {
    $(id).toggleClass("hide", !show);
}