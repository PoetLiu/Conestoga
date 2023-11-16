$(document).ready(() => {
    $("#faq_rollovers h2").on("mouseover mouseleave", (function() {
        $(this).next().toggleClass("hidden");
    }));
}); // end ready