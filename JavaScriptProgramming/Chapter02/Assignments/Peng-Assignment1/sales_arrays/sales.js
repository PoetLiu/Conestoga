"use strict";

// Q1, Q2, Q3, Q4
const region1 = [1540, 1130, 1580, 1105];
const region2 = [2010, 1168, 2305, 4102];
const region3 = [2450, 1847, 2710, 2391];
const region4 = [1845, 1491, 1284, 1575];
const region5 = [2120, 1767, 1599, 3888];
const quaterNum = 4;
const regionNum = 5;

// Calculate Sales by Quarter
document.write(`
    <h2>Sales by Quarter</h2>
`);
for (let i = 0; i < quaterNum; i++) {
    const salesQuater = region1[i] + region2[i] + region3[i] + region4[i] + region5[i];
    const q = i + 1;
    const salesTemplate = `
        Q${q}: $${salesQuater}<br>
    `;
    document.write(salesTemplate);
}

// Calculate Sales by Region
document.write(`
    <h2>Sales by Region</h2>
`);
let totalSales = 0;
for (let i = 0; i < regionNum; i++) {
    const region = i + 1;
    let data = null;
    switch (region) {
        case 1:
            data = region1;
            break;
        case 2:
            data = region2;
            break;
        case 3:
            data = region3;
            break;
        case 4:
            data = region4;
            break;
        case 5:
            data = region5;
            break;
        default:
            alert("unknown region");
    }

    let salesRegion = 0
    for (let j = 0; j < data.length; j++) {
        salesRegion += data[j];
    }
    totalSales += salesRegion;

    const salesTemplate = `Region${region}: $${salesRegion}<br>`;
    document.write(salesTemplate);
}

// For Total sales
document.write(`
    <h2>Total Sales</h2>
`);
const salesTemplate = `
$${totalSales}
<br>
`;
document.write(salesTemplate);