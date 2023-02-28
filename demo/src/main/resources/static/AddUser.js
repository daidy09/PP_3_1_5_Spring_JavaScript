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

        fetch("http://localhost:8080/api/admins/newAddUser", {
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
            findAll();
            $('#home-tab').click();
        });
    });
}
