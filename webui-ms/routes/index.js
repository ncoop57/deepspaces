const express = require("express");
const router = express.Router();

const Space = require("models/space");

router.get("/dashboard", function(req, res) {
    res.render("dashboard");
});

router.post("/add_space", function(req, res) {
    console.log("adding space", req.body.space_id);
    const new_space = new Space({space_id: req.body.space_id,
                                is_available: false,
                                type: req.body.type,
                                x_coord: req.body.x_coord,
                                y_coord: req.body.y_coord,
                                percentage: 0});

    new_space.save(function(err, new_space) {
        if (err) return console.error(err);
    });
});

module.exports = router;
