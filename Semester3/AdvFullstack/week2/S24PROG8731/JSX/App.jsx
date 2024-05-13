const myStyle = { border: "1px solid silver", padding: 4 };
class FilterIssue extends React.Component {
    render() {
        return (
            <>
                <input type="text"></input>
                <input type="submit" value="Filter"></input>
            </>
        );
    }
}

class AddIssue extends React.Component {
    render() {
        return (
            <>  <h2>This is Add issues</h2>
                <input type="text" placeholder="Title"></input>
                <input type="text" placeholder="Owner"></input>
                <input type="submit" value="Add"></input>
            </>
        );
    }
}

class RowIssue extends React.Component {
    render() {
        const issue = this.props.issue;
        return (
            <>
                <tr>
                    <td style={myStyle}>{issue.title}</td>
                    <td style={myStyle}>{issue.owner}</td>
                    <td style={myStyle}>{issue.status}</td>
                </tr>
            </>
        );
    }
}


class TableIssue extends React.Component {
    render() {
        const rows = this.props.mytempissues.map(i =>
            <RowIssue issue={i}></RowIssue>
        );

        return (
            <table>
                <thead>
                    <tr>
                        <th style={myStyle}>Title</th>
                        <th style={myStyle}>Owner</th>
                        <th style={myStyle}>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        );
    }
}

class ListIssue extends React.Component {
    render() {
        const issues = [
            { title: "This is bug1", owner: "jone", status: "New" },
            { title: "This is bug2", owner: "Bob", status: "Open" }
        ];
        return (
            <div>
                <h1>This is List Issues</h1>
                <FilterIssue></FilterIssue>
                <TableIssue mytempissues={issues}></TableIssue>
                <AddIssue></AddIssue>
            </div>
        );
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
            /* This is a comment */
            <div title="Outer div">
                {message}
            </div>
        );
    }
}

const element = <ListIssue></ListIssue>;
// const element = <Hello message="Good Morning"></Hello>
ReactDOM.render(element, document.getElementById('contents'));