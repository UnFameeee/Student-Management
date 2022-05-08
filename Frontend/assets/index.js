const express = require("express");
const { generateUploadURL } = require("./scripts/s3.js");

const app = express();

// app.use(express.static("front"));

app.get("/s3Url", async (req, res) => {
    const url = await generateUploadURL();
    res.send({ url });
});

app.listen(8080, () => console.log("listening on port 8080"));
