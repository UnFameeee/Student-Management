import { URL } from "./URL.js";

const btnTranslate = document.querySelector(".btn-translate");
const btnChange = document.querySelector(".btn-change");

btnTranslate.onclick = (e) => {
    const text = document.querySelector(".source-text").value;
    const sourcelang = document.querySelector("#source-languge").value;
    const targetlang = document.querySelector("#target-languge").value;
    e.preventDefault();
    const api = URL + "/translate";
    const data = {
        text,
        sourcelang,
        targetlang,
    };
    $.ajax({
        type: "POST",
        url: api,
        contentType: "application/json",
        data: JSON.stringify(data), // access in body
    }).done((res) => {
        console.log(res);
        document.querySelector(".target-text").value = res.key;
    });
};
