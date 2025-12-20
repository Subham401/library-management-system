async function loadDashboard() {
    try {
        const books = await apiRequest("/books");
        const members = await apiRequest("/members");

        document.getElementById("bookCount").innerText = books.length;
        document.getElementById("memberCount").innerText = members.length;
    } catch (err) {
        console.error(err.message);
    }
}

async function loadIssuedBooks() {
    const issuedBooks = await apiRequest("/issues");

    const tbody = document.querySelector("#issuedTable tbody");
    tbody.innerHTML = "";

    issuedBooks.forEach(issue => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${issue.bookTitle}</td>
            <td>${issue.memberName}</td>
            <td>${issue.issueDate}</td>
            <td>${issue.returnDate ? "Returned" : "Issued"}</td>
        `;

        tbody.appendChild(row);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    loadDashboard();
    loadIssuedBooks();
});



