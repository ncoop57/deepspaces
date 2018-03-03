const express = require("express");
const app = express();
const port = 80;

const path = require("path");
const body = require("body-parser");
const cookie = require("cookie-parser");
const request = require("request");

app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");
app.use(body.json());
app.use(cookie());
app.use(express.static(path.join(__dirname, "public")));

const index = require("./routes/index");
app.use("/", index);

app.listen(port, function() {
    console.log("Listening on port:", port);
});
