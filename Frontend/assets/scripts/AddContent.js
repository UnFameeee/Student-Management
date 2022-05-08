import getUser from "./GetUser.js";
import { URL } from "./URL.js";

const btnSubmit = document.querySelector(".btn-submit");
const inputs = document.querySelectorAll(".form-input");

btnSubmit.onclick = (e) => {
    e.preventDefault();
    var value = {};
    inputs.forEach((input) => {
        Object.assign(value, {
            [input.getAttribute("name")]: input.value,
        });
    });
    addObjects(value);
};

function addObjects(data) {
    getUser().then((idUser) => {
        Object.assign(data, {
            teacher_id: idUser.key_userid,
        });
        const api = URL + "/" + data.hidden;
        delete data.hidden;
        $.ajax({
            type: "POST",
            url: api,
            contentType: "application/json",
            data: JSON.stringify(data), // access in body
        })
            .done(function (data) {
                alert("Submit success");
            })
            .fail(function () {
                console.log("error");
            });
    });
}
