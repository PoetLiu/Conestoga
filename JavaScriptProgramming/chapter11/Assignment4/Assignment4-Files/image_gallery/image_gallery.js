$(document).ready(() => {
    $("#image_list a").each((i, a) => {
        // preload images
        const image = new Image();
        image.src = $(a).attr("href");
        image.alt = $(a).attr("title");

        $(a).on("click", (evt) => {
            evt.preventDefault();
            // we must change attributes after the previous motion has completed.
            $("#caption").slideUp({duration: 2000, complete: () => {
                $("#caption").text(image.alt).slideDown({duration: 2000});
            }});
            $("#image").slideUp({duration: 2000, complete: () => {
                $("#image").attr("src", image.src).slideDown({duration: 2000});
            }});
        });
    });

    $("#image_list a").first().focus();
});