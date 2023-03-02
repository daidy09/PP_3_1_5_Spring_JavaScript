const form_del = document.getElementById('formDeleteUser');
const id_del = document.getElementById('idDelete');
const name_del = document.getElementById('nameDelete');
const lastname_del = document.getElementById('lastNameDelete');
const age_del = document.getElementById('ageDelete');
const email_del = document.getElementById('emailDelete');
const password_del = document.getElementById('passDelete');
const role_del = document.getElementById("rolesDelete");

async function deleteModalData(id) {
    const  urlForDel = 'http://localhost:8080/rest/users/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                name_del.value = `${user.name}`;
                lastname_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
                password_del.value = `${user.password}`;
                role_del.value = user.roles.map(r=>r.userRole).join(", ");
            })
        $('#deleteModal').modal('show');

    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}
async function deleteUser() {
    form_del.addEventListener('submit', deletingUser);

    function deletingUser(event) {
        event.preventDefault();
        let urlDel = 'http://localhost:8080/rest/users/' + id_del.value;
        let method = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        }


        fetch(urlDel, method).then(() => {
            $('#deleteCloseBtn').click();
            getAdminPage();
        })
    }
}
