console.log("Welcome from MongoDBCrudApp");

const express = require("express");

const app = express();

const PORT = 7080;

app.use(express.static("public"));

app.set("view-engine", "ejs");

// use this line to get the form data from request body.
app.use(express.urlencoded({extended: true}));

const emp = require('./models/emp_model.js');

app.listen(PORT, () => {
    console.log("Server is listening at port: " + PORT);
});

app.get("/insert", (req, res) => {
    res.render("insert.ejs");
});

// Form: e_name=&e_con=&e_salary=&e_email=&e_jdate=
app.post("/insert", async (req, res) => {
    try {
        const form_data = req.body;
        console.log(form_data);
        const empToSave = new emp({
            name: form_data.e_name,
            contact: form_data.e_con,
            salary: form_data.e_salary,
            email: form_data.e_email,
            joiningDate: form_data.e_jdate,
        });

        const empSaved = await empToSave.save();
        console.log(empSaved);
        res.send(empSaved);
    } catch (err) {
        console.log(`Employee not saved to MongoDB, due to err: \n${err}`);
        res.send(err);
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

app.get("/search", (req, res) => {
    res.render("search.ejs");
});

app.post("/search", async (req, res) => {
    const form_data = req.body;
    console.log(form_data);
    try {
        const emp_from_db = await emp.findOne({
           email: form_data.e_email,
        });
        console.log(emp_from_db);
        res.render("emp_record.ejs", {emp: emp_from_db});
    } catch (err) {
        console.log(`Employees Not Featched from db due to the error below\n${err}`);
        res.send(err);
    }
});

app.get("/edit/:eemail", async (req, res) => {
    const eemail = req.params.eemail;
    try {
        const emp_from_db = await emp.findOne({
           email: eemail,
        });
        console.log(emp_from_db);
        res.render("emp_record.ejs", {emp: emp_from_db});
    } catch (err) {
        console.log(`Employee Not Edited from db due to the error below\n${err}`);
        res.send(err);
    } 
});

app.post("/edit", async (req, res) => {
    try {
        const form_data = req.body;
        console.log(form_data);
        const empToSave = new emp({
            name: form_data.e_name,
            contact: form_data.e_con,
            salary: form_data.e_salary,
            email: form_data.e_email,
            joiningDate: form_data.e_jdate,
        });

        const empSaved = await empToSave.save();
        console.log(empSaved);
        res.send(empSaved);
    } catch (err) {
        console.log(`Employee not saved to MongoDB, due to err: \n${err}`);
        res.send(err);
    }
});