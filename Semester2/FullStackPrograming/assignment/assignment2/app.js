console.log("App is starting..");

const express = require("express");
const {body, validationResult} = require("express-validator");
const app = new express();
const path = require("path");
const user = require("./models/user_model");
const { error } = require("console");

app.set("view-engine", "ejs");
app.use(express.static("public"));
app.use(express.urlencoded({extended: true}));

const port = 9090;
app.listen(port, () => {
    console.log("App is listening on port " + port);
});

// define routes
app.get("/", (req, res) => {
    res.send(`
        <h1>Welcome to Home Page</h1>
    `);
});

app.get("/g", (req, res) => {
    res.render(`g.ejs`, {user: null});
});

app.get("/g2", (req, res) => {
    res.render(`g2.ejs`);
});

app.post("/search", async (req, res) => {
    const data = req.body;
    console.log(data);
    try {
        const userInDb = await user.findOne({
            licenseNumber: data.licenseNumber,
        });

        console.log(userInDb);
        if (userInDb) {
            res.render("g.ejs", {user: userInDb});
        } else {
            res.render("message.ejs", {message: `No User Found.`});
        }
    } catch (err) {
        console.log(`Employees Not Featched from db due to the error below\n${err}`);
        res.send(err);
    }
});

const carDetailsValidator = [
    body('make', 'The minimum make length is 4 characters').isLength({min: 4}),
    body('model', 'The minimum model length is 4 characters').isLength({min: 4}),
    body('year', 'Year must be Alphanumeric').isAlphanumeric(),
    body('platno', 'The minimun make length is 6 characters').isLength({min: 4}),
];

app.post("/edit/:license_number", carDetailsValidator, async (req, res) => {
    const licenseNumber = req.params.license_number;
    const data = req.body;
    console.log(data);
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        console.log(`Validate failed, errors: \n${errors.array()}`);
        return res.send({ errors: errors.array() });
    }

    try {
        const userUpdated = await user.findOneAndUpdate({
            licenseNumber: licenseNumber 
        }, {
            carDetails: {
                make: data.make,
                model: data.model,
                year: data.year,
                platno: data.platno,
            }
        }, {
            new: true
        });
        console.log(userUpdated);
        res.render(`message.ejs`, {message: `Your Car Details Updated Successfully!`});
    } catch (err) {
        console.log(`User not updated to MongoDB, due to err: \n${err}`);
        res.send(err);
    } 
});


app.post("/g2", async (req, res) => {
    try {
        const data = req.body;
        console.log(data);
        const newUser = new user({
            firstName: data.firstName,
            lastName: data.lastName,
            age: data.age,
            licenseNumber: data.licenseNumber,
            carDetails: {
                make: data.make,
                model: data.model,
                year: data.year,
                platno: data.platno,
            }
        });

        const userSaved = await newUser.save();
        console.log(userSaved);
        res.render(`message.ejs`, {message: `Your G2 Test Booked Successfully!`});
    } catch (err) {
        console.log(`User not saved to MongoDB, due to err: \n${err}`);
        res.send(err);
    } 
});

app.get("/dashboard", (req, res) => {
    res.render(`dashboard.ejs`);
});

app.get("/login", (req, res) => {
    res.render(`login.ejs`);
});