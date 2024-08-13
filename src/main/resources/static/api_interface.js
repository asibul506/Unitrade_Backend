async function callApi(endpoint, method, data) {
    try {
        const response = await fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.status === 404) {
            document.getElementById('result').innerText = 'Not Found';
            return {status: 404};
        }

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        return response.json();
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('result').innerText = `Error: ${error.message}`;
    }
}

async function getAllUsers() {
    const users = await callApi('/user/allUsers', 'GET');
    document.getElementById('result').innerText = JSON.stringify(users, null, 2);
}

async function getUserByMatriculation() {
    const matriculation = document.getElementById('userMatriculation').value;
    const user = await callApi(`/user/user/${matriculation}`, 'GET');
    //console.log(user);
    document.getElementById('result').innerText = JSON.stringify(user, null, 2);
}

async function addUser() {
    const selectedRoles = Array.from(document.querySelectorAll('.role-checkbox:checked')).map(checkbox => checkbox.value);
    const password = document.getElementById('addUserPassword').value;
    const encryptedPassword = CryptoJS.AES.encrypt(password, 'your-secret-key').toString();

    const user = {
        matriculation: document.getElementById('addUserMatriculation').value,
        firstName: document.getElementById('addUserFirstName').value,
        lastName: document.getElementById('addUserLastName').value,
        email: document.getElementById('addUserEmail').value,
        password: encryptedPassword,
        dob: document.getElementById('addUserDob').value,
        role: selectedRoles
    };
    const addedUser = await callApi('/user/addUser', 'POST', user);
    document.getElementById('result').innerText = JSON.stringify(addedUser, null, 2);
}

async function updateUserByMatriculation() {
    const matriculation = document.getElementById('updateUserMatriculation').value;
    const user = {
        firstName: document.getElementById('updateUserFirstName').value,
        lastName: document.getElementById('updateUserLastName').value,
        email: document.getElementById('updateUserEmail').value,
        password: document.getElementById('updateUserPassword').value,
        dob: document.getElementById('updateUserDob').value
    };
    const updatedUser = await callApi(`/user/update/${matriculation}`, 'PUT', user);
    document.getElementById('result').innerText = JSON.stringify(updatedUser, null, 2);
}

async function deleteUserByMatriculation() {
    const matriculation = document.getElementById('deleteUserMatriculation').value;
    const response = await callApi(`/user/delete/${matriculation}`, 'DELETE');
    document.getElementById('result').innerText = JSON.stringify(response, null, 2);
}

async function getAllLeads() {
    const leads = await callApi('/leads/leads', 'GET');
    document.getElementById('result').innerText = JSON.stringify(leads, null, 2);
}

async function getLeadsByUser() {
    const matriculation = document.getElementById('lUserMatriculation').value;
    const leads = await callApi(`/leads/lead-by-user/${matriculation}`, 'GET');

    document.getElementById('result').innerText = JSON.stringify(leads, null, 2);
}

async function getLeadById() {
    const lId = document.getElementById('leadId').value;
    const lead = await callApi(`/leads/lead-by-id/${lId}`, 'GET');
    document.getElementById('result').innerText = JSON.stringify(lead, null, 2);
}

async function createLead() {
    const lead = {
        lId: parseInt(document.getElementById('lId').value),
        userMatriculation: parseInt(document.getElementById('matriculation').value),
        content: document.getElementById('createLeadContent').value,
        imageUrls: document.getElementById('createLeadImageUrls').value.split(',').map(url => url.trim()),
        comments: document.getElementById('createLeadComments').value.split(',').map(comment => comment.trim()),
        likes: document.getElementById('createLeadLikes').value.split(',').map(like => like.trim())
    };
    const newLead = await callApi('/leads/create-lead', 'POST', lead);
    document.getElementById('result').innerText = JSON.stringify(newLead, null, 2);
}

async function updateLead() {
    const lId = document.getElementById('updateLeadId').value;
    const lead = {
        content: document.getElementById('updateLeadContent').value,
        imageUrls: document.getElementById('updateLeadImageUrls').value.split(','),
        comments: document.getElementById('updateLeadComments').value.split(','),
        likes: document.getElementById('updateLeadLikes').value.split(',')
    };
    const updatedLead = await callApi(`/leads/update/${lId}`, 'PUT', lead);
    document.getElementById('result').innerText = JSON.stringify(updatedLead, null, 2);
}

async function deleteLead() {
    const matriculation = document.getElementById('deleteLeadMatriculation').value;
    const lId = document.getElementById('deleteLeadId').value;
    const response = await callApi(`/leads/delete/${matriculation}/${lId}`, 'DELETE');
    document.getElementById('result').innerText = JSON.stringify(response, null, 2);
}
