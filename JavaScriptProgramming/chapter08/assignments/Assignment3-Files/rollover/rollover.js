"use strict";

document.addEventListener("DOMContentLoaded", () => {
    const images = document.querySelectorAll("#image_rollovers img");

    // process each img tag
    for (let image of images) {
        const oldURL = image.src;
        const newURL = image.id;

        // preload rollover image
        const newImage = new Image();
        newImage.src = newURL;

        // set up event handlers for hovering an image
        image.addEventListener("mouseover", () => {
            image.src = newURL;
        });
        image.addEventListener("mouseout", () => {
            image.src = oldURL;
        });

        // set up two timers for an image
        const rolloverTimer = setTimeout(() => showImage(image, rolloverTimer, newURL), 1000);
        const recoverTimer = setTimeout(() => showImage(image, recoverTimer, oldURL), 2000);
    }
});

// show an image from url
const showImage = (image, timer, url) => {
    clearTimeout(timer);
    image.src = url;
}