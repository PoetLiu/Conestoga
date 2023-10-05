const $ = selector => document.querySelector(selector);
const imageCache = [];
const mainImage = $("#main_image"); // the img element
const caption = $("#caption"); // the h2 element

document.addEventListener("DOMContentLoaded", () => {
    // get all the <a> tags in the ul element
    const links = $("#image_list").querySelectorAll("a");
    // process image links
    let image = null;
    for (let link of links) {
        // preload image and copy title properties
        image = new Image();
        image.src = link.href;
        image.alt = link.title;
        // add image to array
        imageCache[imageCache.length] = image;
    }

    $("#previous").addEventListener("click", goPrevious);
    $("#next").addEventListener("click", goNext);

    hideOptions();
    setTimeout(showOptions, 2000);
});

function showOptions() {
    $("#previous").classList.remove("hide");
    $("#next").classList.remove("hide");
}

function hideOptions() {
    $("#previous").classList.add("hide");
    $("#next").classList.add("hide");
}

// start slide show
let imageCounter = 0;
function goNext() {
    // calculate the index for the current image
    imageCounter = (imageCounter + 1) % imageCache.length;
    replace();
}

function goPrevious() {
    // calculate the index for the current image
    imageCounter = (imageCounter - 1) % imageCache.length;
    if (imageCounter < 0) {
        imageCounter += imageCache.length;
    }
    replace();
}

function replace() {
    // get image object from array
    image = imageCache[imageCounter];
    // set HTML img and h2 elements
    // with values from image object
    mainImage.src = image.src;
    mainImage.alt = image.alt;
    caption.textContent = image.alt;

    hideOptions();
    setTimeout(showOptions, 2000);
}