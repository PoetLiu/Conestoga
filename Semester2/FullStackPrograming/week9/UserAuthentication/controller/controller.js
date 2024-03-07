import userModel from "../model/userModel.js";
import bcrypt, { hash } from "bcrypt";

class Controller {
    static login_get = (req, res) => {
        console.log(req.session);
        res.render('login.ejs');
    }

    static login_post = async (req, res) => {
        try {
            const form = req.body;
            console.log(form);

            let user = await userModel.findOne({
                email: form.email,
            })
            if (!user) {
                res.send("user is not found");
                return;
            } 
            
            const matched = await bcrypt.compare(form.pwd, user.pwd);
            if (matched) {
                req.session.isValid = true;
                res.redirect("/dashboard");
            } else {
                res.send("pwd is incorrect.");
            }
            
        } catch (error) {
            res.send(error);
            console.log(error);
        } 
    }

    static home_get = (req, res) => {
        res.render('home.ejs');
    }
    
    static dashboard_get = (req, res) => {
        res.render('dashboard.ejs');
    }
    
    static logout_post = (req, res) => {
        req.session.destroy((err) => {
            if (err) {
                throw err;
            } else {
                res.redirect("/home");
            }
        })
    }

    static signup_post = async (req, res) => {
        try {
            const form = req.body;
            console.log(form);

            let user = await userModel.findOne({
                email: form.email,
            })
            const hashedPwd = await bcrypt.hash(form.pwd, 10);
        
            if (!user) {
                user = new userModel({
                    name: form.name, 
                    email: form.email, 
                    pwd: hashedPwd
                });
                const userSaved = await user.save();
                res.redirect("/login");
            } else {
                res.send("user with the same email already existed.")
            }
        } catch (error) {
            res.send(error);
            console.log(error);
        }
    }

    static signup_get = (req, res) => {
        res.render('signup.ejs');
    }
}

export default Controller;