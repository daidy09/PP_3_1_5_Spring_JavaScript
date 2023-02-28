let allUsers;
let allRoles;

fetch('/rest/roles').then(
    res => {
        res.json().then(
            roles => {
                allRoles = roles;
            }
        )
    }
)
// ===============форма создания таблицы юзеров в админке=================================
fetch('/rest/users').then(
    res => {
        res.json().then(
            data => {
                allUsers = data;
                createTable(allUsers);
            }
        )
    }
)

function createTable(data) {
    let temp = "";
    data.forEach(u => {
        console.log(u)
        temp += "<tr id=\"" + u.id + "\">";
        temp += "<td>" + u.id + "</td>";
        temp += "<td>" + u.name + "</td>";
        temp += "<td>" + u.lastName + "</td>";

        temp += "<td>" + u.age + "</td>";
        temp += "<td>" + u.email + "</td>";
        temp += "<td>" +u.roles.map(role => " " + role.userRole)+ "</td>";
        temp += "<td><button class=\"btn btn-info\" onclick=\"fEdit(this)\" id=\"editBtn" + u.id + "\">Edit</button></td>";
        temp += "<td><button class=\"btn btn-danger\" onclick=\"fDel(this)\" id=\"deleteBtn" + u.id + "\">Delete</button></td>" + "</tr>";
    })
    document.getElementById("usersTable").innerHTML = temp;
}
// ====================================окончание формы создания юзеров=======================
// ===============начало таблицы для ссылки на данные админа-левая боковая панель=============
fetch('/rest/user').then(
    res => {
        res.json().then(
            data => {
                let temp = "";
                temp += "<tr>";
                temp += "<td>" + data.id + "</td>";
                temp += "<td>" + data.name + "</td>";
                temp += "<td>" + data.lastName + "</td>";
                temp += "<td>" + data.age + "</td>";
                temp += "<td>" + data.email + "</td>";
                temp += "<td>" +data.roles.map(role => " " + role.userRole)+ "</td>";
                temp += "</tr>";
                document.getElementById("tableUserBody2").innerHTML = temp;
            }
        )
    }
)
// =====================окончание таблицы данных админа во вкладке юзер============================

// =========================================старое добавлению юзера=========================

let formNew = document.forms["formNewUser"]
const role_new = document.querySelector('#rolesNew').selectedOptions;
addUser();

function addUser() {
    formNew.addEventListener("submit", ev => {
        ev.preventDefault();
        let listOfRole = [];
        for (let i = 0; i < role_new.length; i++) {
            listOfRole.push({
                id: role_new[i].value
            });
        }

        fetch("http://localhost:8080/rest/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formNew.id.value,
                name: formNew.name.value,
                lastName: formNew.lastName.value,
                age: formNew.age.value,
                email: formNew.email.value,
                username: formNew.email.value,
                password: formNew.password.value,
                roles: listOfRole
            })
        }).then(() => {
            formNew.reset();
            createTable();
            $('#home-tab').click();
        });
    });
}






// ===============================окончание создания нового юзера============================

function getUserById(id) {
    let t = null;
    allUsers.forEach(u => {
        if (u.id == id) {
            t = u;
        }
    })
    return t;
}
//
// $('#editUserBtn').click(function () {
//     let id = document.getElementById("idEditModal").value;
//     let edit = {
//         id: -1,
//         name: "",
//         age: "",
//         email: "",
//         password: "",
//         roles: []
//     };
//     $('#editModal').modal('hide');
//     edit.id = document.getElementById("idEditModal").value;
//     edit.age = document.getElementById("ageEditModal").value;
//     edit.name = document.getElementById("nameEditModal").value;
//     edit.email = document.getElementById("emailEditModal").value;
//     edit.password = document.getElementById("passwordEditModal").value;
//     edit.roles = [];
//     [].slice.call(document.getElementById("rolesEditModal")).forEach(op => {
//         if (op.selected) {
//             allRoles.forEach(r => {
//                 if (r.role == op.text) {
//                     edit.roles.push(r);
//                 }
//             })
//         }
//     })
//     console.log(edit)
//     fetch('/rest/users/' + id, {
//         method: 'PUT',
//         body: JSON.stringify(edit),
//         headers: {'Content-Type': 'application/json'}
//     })
//         .then(res => {
//             if (res.ok) {
//                 allUsers.forEach(u => {
//                     if (u.id == edit.id) {
//                         u.name = edit.name;
//                         u.age = edit.age;
//                         u.email = edit.email;
//                         if (edit.password !== "") {
//                             u.password = edit.password;
//                         }
//                         u.roles = edit.roles;
//                     }
//                 })
//                 createTable(allUsers);
//             }
//         });
//
// })
//


// function fEdit(el) {
//     let idStr = el.id;
//     let id = idStr.slice(7);
//     allUsers.forEach(u => {
//         if (u.id == id) {
//             console.log(u);
//             document.getElementById("idEditModal").value = u.id;
//             document.getElementById("nameEditModal").value = u.name;
//             document.getElementById("ageEditModal").value = u.age;
//             document.getElementById("emailEditModal").value = u.email;
//             document.getElementById("passwordEditModal").value = u.password;
//             document.getElementById("rolesEditModal").size = allRoles.length;
//             let temp = "";
//             allRoles.forEach(r => {
//                 let select = "";
//                 u.roles.forEach(rUser => {
//                     if (rUser.id == r.id) {
//                         select = " selected";
//                     }
//                 })
//                 temp += "<option" + select + ">" + r.role + "</option>";
//             })
//             document.getElementById("rolesEditModal").innerHTML = temp;
//         }
//     });
//     $('#editModal').modal('show');
// }
//
// =================================удаление
// $('#delUserBtn').click(function () {
//     let id = document.getElementById("formDeleteUser").value;
//     $('#deleteModal').modal('hide');
//
//     fetch('/rest/users/' + id, {method: 'DELETE'})
//         .then(res => {
//             if (res.ok) {
//                 document.getElementById(id).remove();
//                 let u = getUserById(id);
//                 let i = allUsers.indexOf(u);
//                 delete allUsers[i];
//             }
//         });
// })
//
// function fDel(el) {
//     let idStr = el.id;
//     let id = idStr.slice(9);
//     allUsers.forEach(u => {
//         if (u.id == id) {
//             document.getElementById("idDelete").value = u.id;
//             document.getElementById("nameDelete").value = u.name;
//             document.getElementById("ageDelete").value = u.age;
//             document.getElementById("emailDelete").value = u.email;
//             document.getElementById("passDelete").value = u.password;
//             // document.getElementById("rolesDelete").size = u.roles.length.toString();
//             let temp = "";
//             u.roles.map(r => {
//                 temp += "<option>" + r.userRole + "</option>";
//             })
//             // temp += "<td>" +data.roles.map(role => " " + role.userRole)+ "</td>";
//             document.getElementById("rolesDelete").innerHTML = temp;
//         }
//     });
//     $('#deleteModal').modal('show');
// }