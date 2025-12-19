async function loadBooks() {
    try {
        const books = await apiRequest("/books");
        const table = document.getElementById("bookTable");
        table.innerHTML = "";

        books.forEach(b => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${b.title}</td>
                <td>${b.author}</td>
                <td>${b.availableCopies}</td>
            `;
            table.appendChild(row);
        });
    } catch (err) {
        document.getElementById("error").innerText = err.message;
    }
}

async function addBook() {
    const book = {
        title: title.value,
        author: author.value,
        isbn: isbn.value,
        totalCopies: parseInt(copies.value)
    };

    try {
        await apiRequest("/books", {
            method: "POST",
            body: JSON.stringify(book)
        });

        loadBooks();
    } catch (err) {
        document.getElementById("error").innerText = err.message;
    }
}

loadBooks();
