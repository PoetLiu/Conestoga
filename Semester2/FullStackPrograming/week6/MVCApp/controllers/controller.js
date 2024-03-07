const emp = require('../models/emp_model.js');

class Controller {
    static display = async (req, res) => {
        try {
            const emps_from_db = await emp.find({});
            console.log(emps_from_db);
            res.render("display.ejs", { emps: emps_from_db });
        } catch (err) {
            console.log(`Employees Not Featched from db due to the error below\n${err}`);
        }
    }

    static insert_form_get = (req, res) => {
        res.render("insert.ejs");
    }

    // Form: e_name=&e_con=&e_salary=&eemail=&e_jdate=
    static insert_form_post = async (req, res) => {
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
            res.redirect("/show");
        } catch (err) {
            console.log(`Employee not saved to MongoDB, due to err: \n${err}`);
            res.send(err);
        }
    }

    static search_get = (req, res) => {
        res.render("search.ejs");
    };

    static search_post = async (req, res) => {
        const form_data = req.body;
        console.log(form_data);
        try {
            const emp_from_db = await emp.findOne({
                email: form_data.e_email,
            });

            console.log(emp_from_db);
            if (emp_from_db) {
                res.render("emp_record.ejs", { emp: emp_from_db });
            } else {
                res.send("No Record Found.")
            }
        } catch (err) {
            console.log(`Employees Not Featched from db due to the error below\n${err}`);
            res.send(err);
        }
    };

    static edit_form_get = async (req, res) => {
        const eemail = req.params.eemail;
        try {
            const emp_from_db = await emp.findOne({
                email: eemail,
            });
            console.log(emp_from_db);
            res.render("edit.ejs", { emp: emp_from_db });
        } catch (err) {
            console.log(`Employee Not Edited from db due to the error below\n${err}`);
            res.send(err);
        }
    };

    static edit_form_post = async (req, res) => {
        try {
            const email = req.params.eemail;
            const form = req.body;
            console.log(form);
            const empSaved = await emp.findOneAndUpdate(
                { email: email },
                {
                    name: form.e_name,
                    contact: form.e_con,
                    salary: form.e_salary,
                    email: form.e_email,
                    joiningDate: form.e_jdate,
                },
                { new: true }
            );
            console.log(empSaved);
            res.redirect("/show");
        } catch (err) {
            console.log(`Employee not updated to MongoDB, due to err: \n${err}`);
            res.send(err);
        }
    }

    static delete_get = async (req, res) => {
        const eemail = req.params.eemail;
        try {
            const emp_from_db = await emp.findOneAndDelete({
                email: eemail,
            });
            console.log(emp_from_db);
            res.redirect("/show");
        } catch (err) {
            console.log(`Employee Not Deleted from db due to the error below\n${err}`);
            res.send(err);
        }
    };
}

module.exports = Controller;