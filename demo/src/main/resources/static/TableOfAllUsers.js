const table = $('#usersTable');
findAll();

function findAll() {
    table.empty()
    fetch("http://localhost:8080/api/admins")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let usersTable = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>   
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => " " + role.userRole)}</td>
                            <td>
                                <button type="button" class="btn btn-info"
                                data-bs-toogle="modal"
                                data-bs-target="#edit_Modal"
                                onclick="editModalData(${user.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" 
                                data-toggle="modal"
                                data-bs-target="#deleteModal"
                                onclick="deleteModalData(${user.id})">Delete</button>
                            </td>
                        </tr>)`;
                table.append(usersTable);
            })
        })
}