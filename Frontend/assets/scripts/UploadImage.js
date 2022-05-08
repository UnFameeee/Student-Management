import { URL } from "./URL.js";
import getUser from "./GetUser.js";

const imageBtn = document.querySelector(".btn-avatar");
const imageInput = document.querySelector("#image-avatar");
const avatar = document.querySelector("#avatar");

import { getStorage, ref as sRef, uploadBytesResumable, getDownloadURL } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-storage.js";
console.log(imageInput);

imageInput.onchange = async () => {
    console.log(imageInput.value);

    const file = imageInput.files[0];

    // get secure url from our server
    const { url } = await fetch("hhttp://127.0.0.1:8080/s3Url").then((res) => res.json());
    console.log(url);

    // post the image direclty to the s3 bucket
    await fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "multipart/form-data",
        },
        body: file,
    });

    const imageUrl = url.split("?")[0];
    console.log(imageUrl);

    // post requst to my server to store any extra data
    getUser().then((user) => {
        const api = URL + "/" + user.key_userrole + "/uploadImage";
        const data = { imageUrl: imageUrl };
        $.ajax({
            type: "POST",
            url: api,
            contentType: "application/json",
            data: JSON.stringify(imageUrl),
        }).done((res) => {
            console.log(res);
            avatar.src = imageUrl;
        });
    });
};

// imageForm.addEventListener("submit", (event) => {
//     event.preventDefault();
//     const file = imageInput.files[0];
//     uploadFileImage(file);
// });

function uploadFileImage(file) {
    const url = generateUploadURL();
    console.log(url);
}

function uploadFile(file) {
    const metadata = {
        contentType: file.type,
    };
    const storage = getStorage();
    const storageRef = sRef(storage, "Musics/" + file.name);
    const UploadTask = uploadBytesResumable(storageRef, file, metadata);
    const modal = document.querySelector(".js-modal");

    UploadTask.on(
        "state-changed",
        () => {},
        (error) => {
            console.error;
        },
        () => {
            getUser().then((user) => {
                getDownloadURL(UploadTask.snapshot.ref).then((firebaseURL) => {
                    console.log(firebaseURL);
                    // const api = URL + "/music/insert";
                    // $.post(api, { name: file.name, link: firebaseURL, userId: user.id, singer: file.singer })
                    //     .done(function (data) {
                    //         console.log(data);
                    //         mainApp.songs = [];
                    //         // mainApp.getSongs();
                    //         uploadFileImage({ link: firebaseURL, image: file.image });
                    //         mainHome.getNewMusic();
                    //         mainHome.getTopMusic(user.pageCurrent);
                    //         modal.classList.remove("open");
                    //     })
                    //     .fail(function () {
                    //         console.log("error");
                    //     });
                });
            });
        }
    );
}
