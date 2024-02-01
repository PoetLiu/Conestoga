"use strict";

const getCookieByName = name => {
    let cookieName = name + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let cookies = decodedCookie.split(";");
    for (let cookie of cookies) {
        cookie = cookie.trim();
        if (cookie.indexOf(cookieName) === 0) {
            return cookie.substring(cookieName.length);
        }
    }
    return "";
};

const setCookie = (name, value, days) => {
    let expires = "";
    if (days) {
        let date = new Date();
        const daysMiliseconds = days * 1000 * 60 * 60 * 24;
        date.setTime(date.getTime() + daysMiliseconds);
        expires = buildCookieKv("expires", date.toUTCString());
    }
    document.cookie = buildCookieKv(name, value) + expires + 
        buildCookieKv("path", "/");
};

const buildCookieKv = (name, value) => {
    return `${name}=${value};`;
}


const deleteCookie = name => {
    setCookie(name, "", -1);
};

const goToPage = url => {
    window.location.replace(url);
};


