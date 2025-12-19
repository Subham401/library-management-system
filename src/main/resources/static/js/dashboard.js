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

loadDashboard();
