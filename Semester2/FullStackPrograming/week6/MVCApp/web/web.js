const express = require("express");
const router = express.Router(); 
const controler = require("../controllers/controller.js");

router.get("/show", controler.display);
router.get("/insert", controler.insert_form_get);
router.post("/insert", controler.insert_form_post);
router.get("/search", controler.search_get);
router.post("/search", controler.search_post);
router.get("/edit/:eemail", controler.edit_form_get);
router.post("/edit/:eemail", controler.edit_form_post);
router.get("/delete/:eemail", controler.delete_get);

module.exports = router;