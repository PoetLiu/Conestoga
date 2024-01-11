/* 
  Name: Peng Liu
  Student Number: 8903532
 */
$(document).ready(() => {
  $('.autoplay').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    // whether display dots to navigate.
    dots: true,
    // play infinitely.
    infinite: true,
    // hide arrows.
    arrows: false
  });

  $("#focusNow").on("click", () => {
    window.location.href = "./index.html";
  });
});