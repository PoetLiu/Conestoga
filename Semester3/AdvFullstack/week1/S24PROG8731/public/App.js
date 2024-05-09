const myStyle = {
  border: "1px solid silver",
  padding: 4
};
const animals = ["Dog", "Cat", "Raccon", "Tigger", "Dog", "Raccon", "Dog"];
function toMap(data) {
  const map = new Map();
  data.forEach(element => {
    if (map.has(element)) {
      map.set(element, map.get(element) + 1);
    } else {
      map.set(element, 1);
    }
  });
  return map;
}
const animalsMap = toMap(animals);
const rows = Array.from(animalsMap, ([name, count]) => /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("td", {
  style: myStyle
}, name), /*#__PURE__*/React.createElement("td", {
  style: myStyle
}, count)));
const element = /*#__PURE__*/React.createElement("div", {
  title: "Outer div"
}, /*#__PURE__*/React.createElement("h1", null, "Hello World!"), /*#__PURE__*/React.createElement("table", null, /*#__PURE__*/React.createElement("thead", null, /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("th", {
  style: myStyle
}, "Name"), /*#__PURE__*/React.createElement("th", {
  style: myStyle
}, "Count"))), /*#__PURE__*/React.createElement("tbody", null, rows)));
ReactDOM.render(element, document.getElementById('contents'));