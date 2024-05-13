const myStyle = {
  border: "1px solid silver",
  padding: 4
};
class FilterIssue extends React.Component {
  render() {
    return /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement("input", {
      type: "text"
    }), /*#__PURE__*/React.createElement("input", {
      type: "submit",
      value: "Filter"
    }));
  }
}
class AddIssue extends React.Component {
  render() {
    return /*#__PURE__*/React.createElement(React.Fragment, null, "  ", /*#__PURE__*/React.createElement("h2", null, "This is Add issues"), /*#__PURE__*/React.createElement("input", {
      type: "text",
      placeholder: "Title"
    }), /*#__PURE__*/React.createElement("input", {
      type: "text",
      placeholder: "Owner"
    }), /*#__PURE__*/React.createElement("input", {
      type: "submit",
      value: "Add"
    }));
  }
}
class RowIssue extends React.Component {
  render() {
    const issue = this.props.issue;
    return /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, issue.title), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, issue.owner), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, issue.status)));
  }
}
class TableIssue extends React.Component {
  render() {
    const rows = this.props.mytempissues.map(i => /*#__PURE__*/React.createElement(RowIssue, {
      issue: i
    }));
    return /*#__PURE__*/React.createElement("table", null, /*#__PURE__*/React.createElement("thead", null, /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Title"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Owner"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Status"))), /*#__PURE__*/React.createElement("tbody", null, rows));
  }
}
class ListIssue extends React.Component {
  render() {
    const issues = [{
      title: "This is bug1",
      owner: "jone",
      status: "New"
    }, {
      title: "This is bug2",
      owner: "Bob",
      status: "Open"
    }];
    return /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("h1", null, "This is List Issues"), /*#__PURE__*/React.createElement(FilterIssue, null), /*#__PURE__*/React.createElement(TableIssue, {
      mytempissues: issues
    }), /*#__PURE__*/React.createElement(AddIssue, null));
  }
}
class Hello extends React.Component {
  constructor() {
    super();
  }
  render() {
    const continents = ['Africa', 'America', 'Asia', 'Australia', 'Europe'];
    const helloContinents = Array.from(continents, c => `Hello ${c}!`);
    const message = this.props.message ?? helloContinents.join(' ');
    return (
      /*#__PURE__*/
      /* This is a comment */
      React.createElement("div", {
        title: "Outer div"
      }, message)
    );
  }
}
const element = /*#__PURE__*/React.createElement(ListIssue, null);
// const element = <Hello message="Good Morning"></Hello>
ReactDOM.render(element, document.getElementById('contents'));