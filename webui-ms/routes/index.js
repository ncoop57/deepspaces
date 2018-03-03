const express = require("express");
const router = express.Router();

const Space = require("models/space");

// Servers the dashboard to the Administrator
router.get("/dashboard", function(req, res) {
    res.render("dashboard");
});

// The Web Dashboard creates a list os Parking Spaces and sends over that array of JSONs
router.post("/add_space", function(req, res) {
    console.log("adding space", req.body);
    res.send({'SUCCESS': true});
    for(var i = 0; i < req.body.length; i++){
        const new_space = new Space({space_id: req.body[i].space_id,
                                    is_available: false,
                                    type: req.body[i].type,
                                    x_coord: req.body[i].x_coord,
                                    y_coord: req.body[i].y_coord,
                                    percentage: 0});
        new_space.save(function(err, new_space) {
            if (err) return console.error(err);
            console.log(new_space);
        });
    }
});

// Python script calls this POST request
router.post('/update_spaces', function(req, res){
    for(var i = 0; i<req.body.length; i++){
        Space.findOneAndUpdate({'space_id': req.body[i].space_id}, {is_available: req.body[i].is_available}, {safe: true, upsert: true}, function(err, model) {
        if (err) return console.log(err);
        return res.send("What's up MaskRCNN? GreatScotts!");
    });
    }
});

router.get('/get_spaces', function(req, res){
    Space.find({}).exec(function(err, spaces) {
        if (err) res.send(err);
        res.send({ spaces: spaces});
    });
});

module.exports = router;\

