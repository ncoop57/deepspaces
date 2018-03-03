const express = require("express");
const router = express.Router();

const Space = require("models/space");

router.get("/dashboard", function(req, res) {
    res.render("dashboard");
});

router.post("/add_space", function(req, res) {
    console.log("adding space", req.body);
    res.send({'SUCCESS': true});
    for(var space in req.body){
        const new_space = new Space({space_id: space.space_id,
                                    is_available: false,
                                    type: space.type,
                                    x_coord: space.x_coord,
                                    y_coord: space.y_coord,
                                    percentage: 0});
        new_space.save(function(err, new_space) {
            if (err) return console.error(err);
            console.log(new_space);
        });
    }
});

module.exports = router;
