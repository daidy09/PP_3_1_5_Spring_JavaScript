
const id_del = document.getElementById('idDelete');
const name_del = document.getElementById('nameDelete');
const lastname_del = document.getElementById('lastNameDelete');
const age_del = document.getElementById('ageDelete');
const email_del = document.getElementById('emailDelete');
const role_del = document.getElementById("rolesDelete")
const deleteModal = document.getElementById("deleteModal");
const closeDeleteButton = document.getElementById("deleteFormCloseButton")
const bsDeleteModal = new bootstrap.Modal(deleteModal);

async function deleteModalData(id) {
    const  urlForDel = 'api/admins/users/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                name_del.value = `${user.name}`;
                lastname_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
                role_del.value = user.roles.map(r=>r.userRole).join(", ");
            })

        bsDeleteModal.show();
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}
async function deleteUser() {
    let urlDel = 'api/admins/users/' + id_del.value;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(urlDel, method).then(() => {
        closeDeleteButton.click();
        findAll();
    })
}
