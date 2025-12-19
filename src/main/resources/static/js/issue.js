async function loadBooks() {
    const books = await apiRequest("/books");

    const bookSelect = document.getElementById("bookSelect");
    const returnBookSelect = document.getElementById("returnBookSelect");

    // Clear existing options
    bookSelect.innerHTML = "";
    returnBookSelect.innerHTML = "";

    // ----- Placeholder for ISSUE -----
    const issuePlaceholder = document.createElement("option");
    issuePlaceholder.textContent = "- Select Book -";
    issuePlaceholder.value = "";
    issuePlaceholder.disabled = true;
    issuePlaceholder.selected = true;
    bookSelect.appendChild(issuePlaceholder);

    // ----- Placeholder for RETURN -----
    const returnPlaceholder = document.createElement("option");
    returnPlaceholder.textContent = "- Select Book -";
    returnPlaceholder.value = "";
    returnPlaceholder.disabled = true;
    returnPlaceholder.selected = true;
    returnBookSelect.appendChild(returnPlaceholder);

    // ----- Actual book options -----
    books.forEach(b => {
        const option = document.createElement("option");
        option.value = b.id;
        option.textContent = `${b.title} (Available: ${b.availableCopies})`;
        bookSelect.appendChild(option);

        const returnOption = option.cloneNode(true);
        returnBookSelect.appendChild(returnOption);
    });
}

async function loadMembers() {
    const members = await apiRequest("/members");

    const memberSelect = document.getElementById("memberSelect");
    const returnMemberSelect = document.getElementById("returnMemberSelect");

    // Clear existing options
    memberSelect.innerHTML = "";
    returnMemberSelect.innerHTML = "";

    // ----- Placeholder for ISSUE -----
    const issuePlaceholder = document.createElement("option");
    issuePlaceholder.textContent = "- Select Member -";
    issuePlaceholder.value = "";
    issuePlaceholder.disabled = true;
    issuePlaceholder.selected = true;
    memberSelect.appendChild(issuePlaceholder);

    // ----- Placeholder for RETURN -----
    const returnPlaceholder = document.createElement("option");
    returnPlaceholder.textContent = "- Select Member -";
    returnPlaceholder.value = "";
    returnPlaceholder.disabled = true;
    returnPlaceholder.selected = true;
    returnMemberSelect.appendChild(returnPlaceholder);

    // ----- Actual member options -----
    members.forEach(m => {
        const option = document.createElement("option");
        option.value = m.id;
        option.textContent = `${m.name} (${m.phone})`;
        memberSelect.appendChild(option);

        const returnOption = option.cloneNode(true);
        returnMemberSelect.appendChild(returnOption);
    });
}


async function issueBook(event) {
    event.preventDefault(); // ⛔ stop page reload

    try {
        const bookId = document.getElementById("bookSelect").value;
        const memberId = document.getElementById("memberSelect").value;

        await apiRequest(`/issues/issue?bookId=${bookId}&memberId=${memberId}`, {
            method: "POST"
        });

        document.getElementById("issueError").innerText = "";
        alert("Book issued successfully");
        loadBooks();
        loadMembers();
    } catch (err) {
        document.getElementById("issueError").innerText = err.message;
    }
}

async function returnBook(event) {
    event.preventDefault(); // ⛔ stop page reload

    try {
        const bookId = document.getElementById("returnBookSelect").value;
        const memberId = document.getElementById("returnMemberSelect").value;

        await apiRequest(`/issues/return?bookId=${bookId}&memberId=${memberId}`, {
            method: "POST"
        });

        document.getElementById("returnError").innerText = "";
        alert("Book returned successfully");
        loadBooks();
        loadMembers();
    } catch (err) {
        document.getElementById("returnError").innerText = err.message;
    }
}


loadBooks();
loadMembers();

