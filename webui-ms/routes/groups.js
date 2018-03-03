const express = require("express");
const router = express.Router();
const Group = require("models/group");

const request = require("request");

const { execSync } = require("child_process");

count = 1;
router.post("/new_group", function(req, res) {
    console.log(" [x] Creating new group...");
    // docker service create -d --name gossip-" + count + " --network multi-cast-net nathancooper/chat:gossip
    let stdout = execSync("docker service create -d --mount type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock --mount type=bind,src=/home/ubuntu/chat/gossip-ms,dst=/home/gossip/ --name gossip-" + count + " --network chat-ms_default nathancooper/chat:gossip");
    console.log(" [x] stdout:", stdout);

    count++;
    res.json({ stdout: stdout });
});

router.post("/delete_group", function(req, res) {
    //    console.log(" [x] Deleting group", req.body.group_id);
    request.post("http://groups/delete_group", { json: req.body }, function(
        err,
        response,
        body
    ) {
        if (!err && response.statusCode == 200) {
            count--;
            res.json({ deleted: true });
        } else {
            res.json(err);
        }
    });
});

router.post("/add_room", function(req, res) {
  console.log(" [x] Adding room", req.body.room);
  request.post("http://groups/add_room", { json: req.body }, function(
    err,
    response,
    body
  ) {
    if (!err && response.statusCode == 200) {
      res.json({ created: true });
    } else {
      res.json(err);
    }
  });
});

module.exports = {
    router: router,
    contains: function(group_id, user, cb) {
	Group.find({group_id: group_id, users: user}, function (err, group) {
	    if (group.length == 0) {
		console.log("User does not exist");
		cb(null);
		request.post("http://groups/add_user", {json: {group_id: group_id, user: user}}, function(err, response, body) {
		    if (!err && response.statusCode == 200) {
			console.log("Added user to group");
		    } else console.log("Error occurred adding user to group");
		}); 
	    }
	    else {
		console.log("User exists");
		cb("User exists");
	    }
	});
    }
}
