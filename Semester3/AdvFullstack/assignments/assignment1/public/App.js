const myStyle = {
  border: "1px solid silver",
  padding: 4
};
class EmployeeDirectory extends React.Component {
  render() {
    const employees = [{
      firstName: "Steven",
      lastName: "Jobs",
      age: 40,
      dateOfJoining: new Date('2010-08-15'),
      title: "VP",
      department: "Engineering",
      employeeType: "FullTime",
      currentStatus: 1
    }, {
      firstName: "Messi",
      lastName: "Lionel",
      age: 32,
      dateOfJoining: new Date('2011-07-12'),
      title: "Manager",
      department: "Marketing",
      employeeType: "FullTime",
      currentStatus: 1
    }];
    return /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement(EmployeeSearch, null), /*#__PURE__*/React.createElement(EmployeeTable, {
      employees: employees
    }), /*#__PURE__*/React.createElement(EmployeeCreate, null));
  }
}
class EmployeeSearch extends React.Component {
  render() {
    return /*#__PURE__*/React.createElement(React.Fragment, null);
  }
}
class EmployeeRow extends React.Component {
  render() {
    const employee = this.props.employee;
    return /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.firstName), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.lastName), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.age), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.dateOfJoining.toLocaleString('en-US')), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.title), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.department), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.employeeType), /*#__PURE__*/React.createElement("td", {
      style: myStyle
    }, employee.currentStatus));
  }
}
class EmployeeTable extends React.Component {
  render() {
    const rows = this.props.employees.map(e => /*#__PURE__*/React.createElement(EmployeeRow, {
      employee: e
    }));
    return /*#__PURE__*/React.createElement("table", null, /*#__PURE__*/React.createElement("thead", null, /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "FirstName"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "LastName"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Age"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "DateOfJoining"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Title"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "Department"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "EmployeeType"), /*#__PURE__*/React.createElement("th", {
      style: myStyle
    }, "CurrentStatus"))), /*#__PURE__*/React.createElement("tbody", null, rows));
  }
}
class EmployeeCreate extends React.Component {
  render() {
    return /*#__PURE__*/React.createElement(React.Fragment, null);
  }
}
const element = /*#__PURE__*/React.createElement(EmployeeDirectory, null);
ReactDOM.render(element, document.getElementById('contents'));