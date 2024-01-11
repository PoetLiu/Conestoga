"use strict";
$("#faqs h2").click(evt => {
    const target = evt.currentTarget;
    $(target).toggleClass("minus");
    if ($(target).attr("class") == "minus") {
        $(target).next().slideDown(
            1000, "easeOutBounce");
    }
    else {
        $(target).next().slideUp(
            1000, "easeInBounce");
    }
});