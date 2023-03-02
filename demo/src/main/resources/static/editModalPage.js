const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('id_ed');
const name_ed = document.getElementById('name_ed');
const lastName_ed = document.getElementById('lastname_ed');
const age_ed = document.getElementById('age_ed');
const email_ed = document.getElementById('email_ed');
const password_ed = document.getElementById('password_ed');


async function editModalData(id) {
    $('#editModal').modal('show');
    const  urlDataEd = 'rest/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(user => {
                id_ed.value = `${user.id}`;
                name_ed.value = `${user.name}`;
                lastName_ed.value = `${user.lastName}`;
                age_ed.value = `${user.age}`;
                email_ed.value = `${user.email}`;
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function editUser() {
    let urlEdit = 'rest/users/' + id_ed.value;
    let listOfRole = [];
    for (let i=0; i<form_ed.rolesForEditing.options.length; i++) {
        if (form_ed.rolesForEditing.options[i].selected) {
            listOfRole.push("ROLE_" + form_ed.rolesForEditing.options[i].value);
        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: form_ed.name.value,
            lastName: form_ed.lastname.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: listOfRole
        })
    }
    await fetch(urlEdit,method).then(() => {
        $('#editCloseBtn').click();
        getAdminPage();
    })
}