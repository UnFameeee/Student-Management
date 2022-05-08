import { URL } from './URL.js';
import getUser from './GetUser.js';

const spanProfiles = document.querySelectorAll('span');
const type = document.querySelector('input[type="hidden"]').value;


function getProfile() {
    getUser()
        .then(user => {
            const api = URL + '/' + user.key_userrole + '/' + user.key_userid;
            $.get(api)
                .done(function(data) {
                    spanProfiles.forEach(spanProfile => {
                        for (let key in data) {
                            if (spanProfile.classList[0] === key) {
                                spanProfile.innerText = data[key];
                            } else if (spanProfile.classList[0] === 'fullname') {
                                const fullname = data.firstname + ' ' + data.lastname
                                if (data.firstname)
                                    spanProfile.innerText = fullname;
                            }
                        }

                    });
                })
                .fail(function() {
                    console.log("error");
                })
        })
}
getProfile();