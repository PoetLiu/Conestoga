// the event handler for the click event
// of each h2 element
const toggle = evt => {
    // get the clicked h2
    const h2Element = evt.currentTarget;
    // get h2's sibling div
    const divElement = h2Element.nextElementSibling;
    h2Element.classList.toggle("minus");
    divElement.classList.toggle("open");
    // cancel default action of h2's child <a>
    evt.preventDefault();
};
document.addEventListener("DOMContentLoaded", () => {
    // get the h2 tags
    const h2Elements =
        document.querySelectorAll("#faqs h2");
    // attach event handler for each h2 tag
    for (let h2Element of h2Elements) {
        h2Element.addEventListener("click", toggle);
    }
    // set focus on first h2 tag's <a> tag
    h2Elements[0].firstChild.focus();
});
