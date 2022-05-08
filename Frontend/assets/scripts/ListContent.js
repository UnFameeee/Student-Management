import { URL } from './URL.js';
import getUser from './GetUser.js';


const formEdit = document.querySelector('#form-edit');
const btnCloseForm = document.querySelector('.form-close');
let type = document.querySelector('input[type="hidden"]').value;
const typeOriginal = type;
const registered = document.querySelector('input[type="hidden"]').getAttribute('id');
const tableBody = document.querySelector('table tbody');
const tableBodyModal = document.querySelector('.modal-container table tbody');
const listType = document.querySelector('.list-type');

console.log('hidden: ', typeOriginal);

if (btnCloseForm != null) {
    btnCloseForm.onclick = () => {
        formEdit.classList.remove('active');
    }
}

function getContent() {
    getUser()
        .then(user => {
            let api;
            if (user.key_userrole == 'student' && type === 'score' || user.key_userrole == 'teacher' && type == 'course') {
                api = URL + '/' + type + '/' + user.key_userid;

            } else if (user.key_userrole == 'student' && type == 'course' && registered) {
                api = URL + '/' + type + '/registered/' + user.key_userid;
            } else
                api = URL + "/" + type + "/";
            console.log(api);
            $.get(api)
                .done(function(contents) {
                    const ths = document.querySelectorAll('.page-body th');
                    const trs = document.querySelectorAll('tr:not(tr:first-child)');

                    for (let tr of trs) {
                        tr.innerHTML = '';
                    }
                    contents.forEach(content => {
                        let tr = document.createElement('tr');
                        for (let key of ths) {
                            if (key.classList.value != 'action') {
                                let td = document.createElement('td');
                                td.classList.add(key.classList.value + '-td');
                                td.innerHTML = content[key.classList];
                                tr.append(td);
                            }
                        }
                        let td = document.createElement('td');
                        if (user.key_userrole != 'admin' && user.key_userrole) {
                            if (user.key_userrole == 'student' && type == 'score') {
                                if (content.scores == null) {
                                    tableBody.append(tr);
                                    return;
                                } else if (content.scores >= 5) {
                                    td.innerHTML = `
                                    <td>
                                        <i class="ri-checkbox-circle-line"></i>
                                    </td>`
                                } else if (content.scores < 5) {
                                    td.innerHTML = `
                                    <td>
                                        <i class="ri-close-circle-line"></i>
                                    </td>`
                                }
                            } else if (user.key_userrole == 'teacher' && type == 'course') {
                                td.innerHTML = `
                            <td>
                                <button class="btn btn-mark" data-index="${content.course_id}" data-course="${content.name}">
                                    <i class="ri-calendar-check-line"></i>
                                </button>
                                <button class="btn btn-delete" data-index="${content.course_id}">
                                    <i class="ri-delete-bin-4-fill"></i>
                                </button>
                            </td>`
                            } else if (user.key_userrole == 'student' && type == 'course') {
                                if (registered) {
                                    td.innerHTML = `
                                <td>
                                    <button class="btn btn-delete" data-index="${content.course_id}">
                                        <i class="ri-delete-bin-4-fill"></i>
                                    </button>
                                </td>`
                                } else {
                                    td.innerHTML = `
                                <td>
                                    <button class="btn btn-edit" data-index="${content.course_id}" data-course="${content.name}">
                                        <i class="ri-pencil-fill"></i>
                                    </button>
                                </td>`
                                }
                            } else {
                                td.innerHTML = `
                            <td>
                                <button class="btn btn-edit" data-index="${content.course_id}">
                                    <i class="ri-pencil-fill"></i>
                                </button>
                                <button class="btn btn-delete" data-index="${content.course_id}">
                                    <i class="ri-delete-bin-4-fill"></i>
                                </button>
                            </td>`
                            }
                        } else if (user.key_userrole == 'admin' && typeOriginal == 'account') {
                            td.innerHTML = `
                            <td>
                                <button class="btn btn-reset" data-index="${content.account_id}" data-user-id="${content.user_id}">
                                    <i class="ri-restart-fill"></i>
                                </button>
                                <button class="btn btn-delete" data-index="${content.account_id}" data-user-id="${content.user_id}" data-username="${content.username}">
                                    <i class="ri-delete-bin-4-fill"></i>
                                </button>
                            </td>`
                        }
                        console.log('role', user.key_userrole);
                        tr.append(td);
                        tableBody.append(tr);
                    });
                    const btnEdits = document.querySelectorAll('.btn-edit');
                    if (btnEdits != null) {
                        if (type === 'course' && user.key_userrole == 'student') {
                            btnEdits.forEach(btnEdit => {
                                btnEdit.onclick = () => {
                                    const courseId = btnEdit.getAttribute('data-index');
                                    let api = URL + '/score/check';
                                    let data = {
                                        'student_id': user.key_userid,
                                        'course_id': courseId,
                                        'score': '',
                                    }
                                    $.ajax({
                                            type: 'POST',
                                            url: api,
                                            contentType: 'application/json',
                                            data: JSON.stringify(data), // access in body
                                        })
                                        .done(contents => {
                                            console.log(contents);
                                            if (contents === 'Registered')
                                                alert('Can\'t Register');
                                            else {
                                                let api = URL + '/score/';
                                                $.ajax({
                                                        type: 'POST',
                                                        url: api,
                                                        contentType: 'application/json',
                                                        data: JSON.stringify(data), // access in body
                                                    })
                                                    .done(contents => {
                                                        alert('Register Success');
                                                    })
                                            }

                                        })
                                }
                            })
                        } else {
                            btnEdits.forEach(btnEdit => {
                                btnEdit.onclick = () => {
                                    formEdit.classList.add('active');
                                }
                            })
                        }
                    }
                    const btnMarks = document.querySelectorAll('.btn-mark');
                    if (btnMarks != null) {
                        btnMarks.forEach(btnMark => {
                            console.log(btnMark);
                            const courseId = btnMark.getAttribute('data-index');
                            btnMark.onclick = () => {
                                const formModalTitleID = document.querySelector('.modal-container .form-title .course-id');
                                formModalTitleID.innerHTML = `${btnMark.getAttribute('data-index')}`;
                                const formModalTitleCourse = document.querySelector('.modal-container .form-title .course-name');
                                formModalTitleCourse.innerHTML = `${btnMark.getAttribute('data-course')}`;
                                formEdit.classList.add('active');
                                api = URL + '/score/course/' + courseId;
                                console.log(api);
                                $.get(api)
                                    .done(function(contents) {
                                        const ths = document.querySelectorAll('.modal-container th');
                                        const trs = document.querySelectorAll('.modal-container tr:not(tr:first-child)');
                                        for (let tr of trs) {
                                            tr.innerHTML = '';
                                        }
                                        contents.forEach(content => {
                                            let tr = document.createElement('tr');

                                            for (let key of ths) {
                                                if (key.classList.value != 'scores') {
                                                    let td = document.createElement('td');
                                                    td.classList.add(key.classList.value + '-td');
                                                    td.innerHTML = content[key.classList];
                                                    tr.append(td);
                                                }
                                            }
                                            let td = document.createElement('td');
                                            td.innerHTML = `
                                                <td>
                                                    <input type="number" id="quantity" class='scores-td' name="quantity" min="0" max="10" value="${content['scores']}">
                                                </td>`;
                                            tr.append(td);
                                            tableBodyModal.append(tr);
                                        })
                                    })
                                    .fail(function() {
                                        console.log("error");
                                    })
                            }
                        })
                    }
                    const btnResets = document.querySelectorAll('.btn-reset');
                    if (btnResets != null) {
                        btnResets.forEach(btnReset => {
                            console.log(btnReset);
                            const accountId = btnReset.getAttribute('data-index');
                            const username = btnReset.getAttribute('data-username');
                            let role;
                            if (listType.value === 'teacherList')
                                role = 'teacher';
                            else if (listType.value === 'studentList')
                                role = 'student';
                            btnReset.onclick = () => {
                                api = URL + '/account/update';
                                let data = {};
                                Object.assign(data, {
                                    username,
                                    password: 123,
                                    role,
                                    account_id: accountId
                                });
                                $.ajax({
                                        type: 'PUT',
                                        url: api,
                                        contentType: 'application/json',
                                        data: JSON.stringify(data), // access in body
                                    })
                                    .done(function(data) {
                                        alert('Reset password success');
                                    })
                                    .fail(function() {
                                        console.log("error");
                                    })
                            }
                        })
                    }
                    const btnDeletes = document.querySelectorAll('.btn-delete');
                    if (btnDeletes != null) {
                        if (type === 'course' && user.key_userrole == 'student') {
                            btnDeletes.forEach(btnDelete => {
                                btnDelete.onclick = () => {
                                    const courseId = btnDelete.getAttribute('data-index');
                                    let api = URL + '/score';
                                    let data = {
                                        'student_id': user.key_userid,
                                        'course_id': courseId,
                                        'score': '',
                                    }
                                    $.ajax({
                                            type: 'DELETE',
                                            url: api,
                                            contentType: 'application/json',
                                            data: JSON.stringify(data), // access in body
                                        })
                                        .done(contents => {
                                            location.reload();
                                        })
                                }
                            })
                        } else if (user.key_userrole == 'admin') {
                            btnDeletes.forEach(btnDelete => {
                                btnDelete.onclick = () => {
                                    const accountId = btnDelete.getAttribute('data-index');
                                    const userId = btnDelete.getAttribute('data-user-id');

                                    let api = URL + '/account/';
                                    if (listType.value === 'teacherList')
                                        api = api + 'teacher/' + accountId + '/' + userId;
                                    else if (listType.value === 'studentList')
                                        api = api + 'student/' + accountId + '/' + userId;
                                    $.ajax({
                                            type: 'DELETE',
                                            url: api,
                                            contentType: 'application/json'
                                        })
                                        .done(contents => {
                                            getContent();
                                        })
                                }
                            })
                        } else {
                            btnDeletes.forEach(btnDelete => {
                                btnDelete.onclick = () => {
                                    const courseId = btnDelete.getAttribute('data-index');
                                    let api = URL + '/' + type + '/' + courseId;
                                    $.ajax({
                                            type: 'DELETE',
                                            url: api,
                                            contentType: 'application/json',
                                        })
                                        .done(contents => {
                                            location.reload();
                                        })
                                }
                            })
                        }
                    }
                })
                .fail(function() {
                    console.log("error");
                })
        })
}

if (listType)
    listType.onchange = () => {
        type = typeOriginal + '/' + listType.value;
        getContent();
    }
else
    getContent();