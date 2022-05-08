import getUser from './GetUser.js';
import { URL } from './URL.js';

const btnMenu = document.querySelector('.btn-menu');
const btnLogout = document.querySelector('.btn-logout');
const main = document.querySelector('#main');
const navUser = document.querySelector('.nav-user');
const subnavUser = document.querySelector('.subnav-user');
const mainMenus = document.querySelectorAll('.main-menu');

if (btnMenu)
    btnMenu.onclick = () => {
        main.classList.toggle('mini-sidebar');
    }
if (btnLogout)
    btnLogout.onclick = () => {
        console.log(URL);
        $.ajax({
            type: 'DELETE',
            url: URL + '/account/deleteGlobalId',
            dataType: 'json',
            headers: {
                "Content-Type": "application/json",
            },
            success: function(data) {
                console.log(data);
                location.href = "Login.html";
            },
            error: function() {
                console.log("The following error occured: ");
            }
        });
    }

if (navUser)
    navUser.onclick = () => {
        subnavUser.classList.toggle('active');
    }

if (mainMenus)
    mainMenus.forEach((mainMenu) => {
        mainMenu.onclick = () => {
            console.log("The following");
            const subMenu = mainMenu.querySelector('.sub-menu');
            if (subMenu)
                subMenu.classList.toggle('active');
        }
    })

const username = document.querySelector('.user-name');
const userPosition = document.querySelector('.user-position');

function getProfile() {
    getUser()
        .then(user => {
            const api = URL + '/' + user.key_userrole + '/' + user.key_userid;
            $.get(api)
                .done(function(data) {
                    if (username)
                        username.innerHTML = data.firstname + ' ' + data.lastname;
                    if (userPosition)
                        userPosition.innerHTML = user.key_userrole;
                })
                .fail(function() {
                    console.log("error");
                })
        })
}
getProfile();