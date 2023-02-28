fetch('/rest/user').then(
    res => {
        res.json().then(
            data => {
                    $('#headerUserName').append(data.username);
                    let roles = data.roles.map(role => " " + role.userRole);
                    $('#headerRole').append(roles);
                    let user = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.lastName}</td>
                    <td>${data.age}</td>
                    <td>${data.email}</td>
                    <td>${roles}</td>
                </tr>)`;
                    $('#userTable').append(user);
            }
        )
    }
)

