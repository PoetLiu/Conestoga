
const myStyle = { border: "1px solid silver", padding: 4 };
const animals = ["Dog", "Cat", "Raccon", "Tigger", "Dog", "Raccon", "Dog"];

function toMap(data) {
    const map = new Map(); 
    data.forEach(element => {
        if (map.has(element)) {
            map.set(element, map.get(element)+1);
        } else {
            map.set(element, 1);
        }
    });
    return map;
}

const animalsMap = toMap(animals);

const rows = Array.from(animalsMap, ([name, count]) => 
    <tr>
        <td style={myStyle}>{name}</td>
        <td style={myStyle}>{count}</td>
    </tr>
    );

const element = (
    <div title="Outer div">
        <h1>Hello World!</h1>
        <table>
            <thead>
                <tr>
                    <th style={myStyle}>Name</th>
                    <th style={myStyle}>Count</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    </div>
);

ReactDOM.render(element, document.getElementById('contents'));