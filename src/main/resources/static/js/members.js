async function loadMembers() {
    try {
        const members = await apiRequest("/members");
        const table = document.getElementById("memberTable");
        table.innerHTML = "";

        members.forEach(m => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${m.name}</td>
                <td>${m.email ?? ""}</td>
                <td>${m.phone}</td>
            `;
            table.appendChild(row);
        });
    } catch (err) {
        document.getElementById("error").innerText = err.message;
    }
}

async function addMember() {
    const member = {
        name: memberName.value,
        email: email.value,
        phone: phone.value
    };

    try {
        await apiRequest("/members", {
            method: "POST",
            body: JSON.stringify(member)
        });

        memberName.value = "";
        email.value = "";
        phone.value = "";

        loadMembers();
    } catch (err) {
        document.getElementById("error").innerText = err.message;
    }
}

loadMembers();
