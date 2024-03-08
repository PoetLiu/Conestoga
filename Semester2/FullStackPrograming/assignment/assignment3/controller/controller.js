import User from "../models/user_model.js";
import bcrypt from "bcrypt";
import { body, validationResult } from "express-validator";

class Controller {
    static home_get = (req, res) => {
        res.send(`
            <h1>Welcome to Home Page</h1>
        `);
    };

    static g_get = async (req, res) => {
        try {
            const user = await User.findById(req.session.userId);
            console.log(user);
            if (user) {
                if (user.licenseNumber === 'DEFAULT') {
                    res.redirect("/g2");
                } else {
                    res.render("g.ejs", { user: user });
                }
            } else {
                res.render("message.ejs", {userType: req.session.userType, message: `No User Found.` });
            }
        } catch (err) {
            console.log(`User Not Featched from db due to the error below\n${err}`);
            res.send(err);
        }
    };

    static g2_get = async (req, res) => {
        try {
            const user = await User.findById(req.session.userId);
            console.log(user);
            if (user) {
                if (user.licenseNumber === 'DEFAULT') {
                    // if user infos are 'DEFAULT', display an empty page.
                    res.render("g2.ejs", { user: { userType: user.userType } });
                } else {
                    res.render("g2.ejs", { user: user });
                }
            } else {
                res.render("message.ejs", {userType: req.session.userType, message: `No User Found.` });
            }
        } catch (err) {
            console.log(`User Not Featched from db due to the error below\n${err}`);
            res.send(err);
        }
    };

    static edit_post = async (req, res) => {
        const data = req.body;
        console.log(data);
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(`Validate failed, errors: \n${errors.array()}`);
            return res.send({ errors: errors.array() });
        }

        try {
            const user = await User.findByIdAndUpdate(
                req.session.userId,
                {
                    carDetails: {
                        make: data.make,
                        model: data.model,
                        year: data.year,
                        platno: data.platno,
                    }
                }, {
                new: true
            });
            console.log(user);
            res.render(`message.ejs`, {userType: req.session.userType, message: `Your Car Details Updated Successfully!` });
        } catch (err) {
            console.log(`User not updated to MongoDB, due to err: \n${err}`);
            res.send(err);
        }
    };


    static g2_post = async (req, res) => {
        try {
            const data = req.body;
            console.log(data);

            const hashedLicenseNumber = await bcrypt.hash(data.licenseNumber, 10);
            const user = await User.findByIdAndUpdate(req.session.userId, {
                firstName: data.firstName,
                lastName: data.lastName,
                age: data.age,
                licenseNumber: hashedLicenseNumber,
                carDetails: {
                    make: data.make,
                    model: data.model,
                    year: data.year,
                    platno: data.platno,
                }
            });

            console.log(user);
            res.render(`message.ejs`, {userType: req.session.userType, message: `Your G2 Test Booked Successfully!` });
        } catch (err) {
            console.log(`User not saved to MongoDB, due to err: \n${err}`);
            res.send(err);
        }
    };

    static dashboard_get = (req, res) => {
        res.render(`dashboard.ejs`, { userType: req.session.userType });
    };

    static login_get = (req, res) => {
        res.render(`login.ejs`, { userType: req.session.userType });
    };

    static login_post = async (req, res) => {
        try {
            const form = req.body;
            console.log(form);

            let user = await User.findOne({
                username: form.username
            })
            if (!user) {
                res.redirect("/signup");
                return;
            }

            const matched = await bcrypt.compare(form.password, user.password);
            if (matched) {
                req.session.userId = user._id;
                req.session.userType = user.userType;
                res.redirect("/dashboard");
            } else {
                res.redirect("/login");
            }

        } catch (error) {
            res.send(error);
            console.log(error);
        }
    }

    static signup_get = (req, res) => {
        res.render(`signup.ejs`, { userType: req.session.userType });
    };

    static signup_post = async (req, res) => {
        try {
            const data = req.body;
            console.log(data);

            if (data.password !== data.confirmPassword) {
                res.render(`message.ejs`, { message: `The passwords you entered doesn't match.` });
                return;
            }

            let user = await User.findOne({
                username: form.username
            })
            if (user) {
                res.render(`message.ejs`, { message: `The username already existed.` });
                return;
            }

            const hashedPwd = await bcrypt.hash(data.password, 10);
            const newUser = new User({
                username: data.username,
                password: hashedPwd,
                userType: data.userType
            });

            const userSaved = await newUser.save();
            console.log(userSaved);
            res.redirect("/login");
        } catch (err) {
            console.log(`User not saved to MongoDB, due to err: \n${err}`);
            res.send(err);
        }
    };


    static logout_get = (req, res) => {
        res.render(`logout.ejs`, { userType: req.session.userType });
    };

    static logout_post = (req, res) => {
        req.session.destroy((err) => {
            if (err) {
                throw err;
            } else {
                res.redirect("/dashboard");
            }
        })
    };
}

export default Controller;