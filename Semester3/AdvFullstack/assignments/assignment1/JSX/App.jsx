const myStyle = { border: "1px solid silver", padding: 4 };

class EmployeeDirectory extends React.Component {
    render() {
        const employees = [
            {
                firstName: "Steven", lastName: "Jobs", age: 40, dateOfJoining: new Date('2010-08-15'),
                title: "VP", department: "Engineering", employeeType: "FullTime", currentStatus: 1
            },
            {
                firstName: "Messi", lastName: "Lionel", age: 32, dateOfJoining: new Date('2011-07-12'),
                title: "Manager", department: "Marketing", employeeType: "FullTime", currentStatus: 1
            }
        ];
        return (
            <div>
                <EmployeeSearch></EmployeeSearch>
                <EmployeeTable employees={employees}></EmployeeTable>
                <EmployeeCreate></EmployeeCreate>
            </div>
        );
    }
}

class EmployeeSearch extends React.Component {
    render() {
        return (<></>)
    };
}

class EmployeeRow extends React.Component {
    render() {
        const employee = this.props.employee;
        return (
                <tr>
                    <td style={myStyle}>{employee.firstName}</td>
                    <td style={myStyle}>{employee.lastName}</td>
                    <td style={myStyle}>{employee.age}</td>
                    <td style={myStyle}>{employee.dateOfJoining.toLocaleString('en-US')}</td>
                    <td style={myStyle}>{employee.title}</td>
                    <td style={myStyle}>{employee.department}</td>
                    <td style={myStyle}>{employee.employeeType}</td>
                    <td style={myStyle}>{employee.currentStatus}</td>
                </tr>
        );
    }
}

class EmployeeTable extends React.Component {
    render() {
        const rows = this.props.employees.map(e =>
            <EmployeeRow employee={e}></EmployeeRow>
        );
        return (
            <table>
                <thead>
                    <tr>
                        <th style={myStyle}>FirstName</th>
                        <th style={myStyle}>LastName</th>
                        <th style={myStyle}>Age</th>
                        <th style={myStyle}>DateOfJoining</th>
                        <th style={myStyle}>Title</th>
                        <th style={myStyle}>Department</th>
                        <th style={myStyle}>EmployeeType</th>
                        <th style={myStyle}>CurrentStatus</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        );
    }
}

class EmployeeCreate extends React.Component {
    render() {
        return (<></>)
    };
}

const element = <EmployeeDirectory></EmployeeDirectory>;
ReactDOM.render(element, document.getElementById('contents'));