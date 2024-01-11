$(document).ready(() => {
  $("#calculate").on("click", calculate);

  daysLeft();
});

// Program to display area of triangle
const calculate = () => {
  const base = parseFloat($("#base").val());
  const height = parseFloat($("#height").val());
  const area = ((base * height) / 2).toFixed(2);
  $("#result").text("Area is: " + area);
};

// Write a JavaScript program to calculate days left until next Christmas
const daysLeft = () => {
  const today = new Date();
  today.setHours(0, 0, 0, 0);
  $("#today").text(formatDate(today));

  const nextChristmasDate = new Date(today.getFullYear(), 11, 25);
  if (today.getFullYear() == 11 && today.getDay() > 25) {
    nextChristmasDate.setFullYear(today.getFullYear() + 1);
  }
  $("#nextChristmas").text(formatDate(nextChristmasDate));

  $("#daysLeft").text(daysBetween(today, nextChristmasDate));
}

const formatDate = (date) => {
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();

  return `${day}-${month}-${year}`;
}

const daysBetween = (start, end) => {
  return (end - start) / 1000 / (60 * 60 * 24);
}