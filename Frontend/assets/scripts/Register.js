import { URL } from './URL.js';

const btnAdd = document.querySelector(".btn-submit");

btnAdd.addEventListener("click", (Event) => {
    Event.preventDefault();

    var Model = {
        username: document.getElementById("username").value,
        password: 1,
        role: document.getElementById("list-type").value,
    };

    var requestJSON = JSON.stringify(Model);

    $.ajax({
        type: 'POST',
        url: URL + '/account/' + Model.role,
        dataType: 'json',
        headers: {
            "Content-Type": "application/json",
        },
        data: requestJSON,
        success: function(data) {
            alert("Create account success");
        },
        error: function() {
            console.log("The following error occured: ");
        }
    });
}, false);