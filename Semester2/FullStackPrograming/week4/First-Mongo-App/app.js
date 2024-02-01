console.log("Welcome From First Mongo App !");

const express = require('express');

const app = express()

app.use(express.static("public"));

app.set("view-engine", "ejs");

app.use(express.urlencoded({extended: true}));

const emp = require('./models/emp_model.js');

const port = 5050;
app.listen(port, () => {
    console.log("Server is listening at port: " + port);
});

app.get("/add_emp", (req, res) => {
    res.render("insert.ejs");
});

app.post("/add_emp", async (req, res) => {
    try {
        const form_data = req.body;
        console.log(form_data);
        const empToSave = new emp({
            name: form_data.e_name,
            age: form_data.e_age,
            contact: form_data.e_con,
            email: form_data.e_email
        });

        const empSaved = await empToSave.save();
        console.log(empSaved);
        res.send(empSaved);
    } catch (err) {
        console.log(`Employee not saved to MongoDB, due to err: \n${err}`);
    }
});

app.get("/show_all", async (req, res) => {
    try {
        const emps_from_db = await emp.find({});
        console.log(emps_from_db);
        res.render("show_all.ejs", {emps: emps_from_db});
    } catch (err) {
        console.log(`Employees Not Featched from db due to the error below\n${err}`);
    }
});

/*
app.get("/add_emp", async (req, res) => {
    try {
        const empToSave = new emp({
            name: "Rojer Bacon",
            age: 26,
            contact: "415-223-3345",
            email: "Rojer@nowhere.com",
        });

        const empSaved = await empToSave.save();
        console.log(empSaved);
        res.send(empSaved);
    } catch (err) {
        console.log(`Employee not saved to MongoDB, due to err: \n${err}`);
    }
});
*/