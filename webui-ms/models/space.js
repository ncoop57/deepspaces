const mongoose = require("mongoose");
mongoose.Promise = require("bluebird");
let Schema = mongoose.Schema;

const options = {
    useMongoClient: true,
};
const url = "mongodb://spaces-db/spaces";
let conn = mongoose.connect(url, options);

conn.on('error', function(err) {
  if (err.message && err.message.match(/failed to connect to server .* on first connect/)) {
    console.log(new Date(), String(err));

    setTimeout(function() {
      console.log("Retrying first connect...");
      conn.openUri(url).catch(() => {});
    }, 20 * 1000);
  } else {
    console.error(new Date(), String(err));
  }
});

conn.once('open', function() {
  console.log('Connection to db established.');
});

let spaceSchema = new Schema({
    space_id: String,
    is_available: Boolean,
    type: String,
    x_coord: Number,
    y_coord: Number,
    percentage: Number
});

module.exports = mongoose.model("Space", spaceSchema);
