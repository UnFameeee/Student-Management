const dotenv = require("dotenv");
const aws = require("aws-sdk");
const crypto = require("crypto");
const { promisify } = require("util");
const randomBytes = promisify(crypto.randomBytes);

dotenv.config();
const region = "us-west-2";
const bucketName = "upload-profile-s3-bucket";
const accessKeyId = "AKIAY5KMAK7THFZTKHHQ";
const secretAccessKey = "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";

const s3 = new aws.S3({
    region,
    accessKeyId,
    secretAccessKey,
    signatureVersion: "v4",
});

export async function generateUploadURL() {
    const rawBytes = await randomBytes(16);
    const imageName = rawBytes.toString("hex");

    const params = {
        Bucket: bucketName,
        Key: imageName,
        Expires: 60,
    };

    const uploadURL = await s3.getSignedUrlPromise("putObject", params);
    return uploadURL;
}
