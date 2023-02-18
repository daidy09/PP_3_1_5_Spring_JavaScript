const userurl = '/api/user';

const authUser = fetch(userurl).then(response => response.json())
authUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += ' '
            roles += role.userRole
        })
        document.getElementById("Email").innerHTML = user.email
        document.getElementById("Roles").innerHTML = roles
    }
)

async function getUserPage() {
    let page = await fetch(userurl);

    if(page.ok) {
        let user = await  page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function  getInformationAboutUser(user) {
    let tr = document.createElement("tr");
    let roles = [];
    for (let role of user.roles) {
        roles.push(" " + role.userRole.toString().replaceAll("ROLE_", ""))
    }
    tr.innerHTML =
        `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles}</td>   
</tr>`

    document.getElementById(`tbody`).append(tr);
}
getUserPage();