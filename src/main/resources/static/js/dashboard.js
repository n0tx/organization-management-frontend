// Sembunyikan elemen setelah 5 detik
document.addEventListener("DOMContentLoaded", function() {
    // input search hanya muncul pada list
    const currentPath = window.location.pathname;
    const searchForm = document.getElementById("search");

    if (currentPath === "/employee/list") {
        searchForm.classList.remove("d-none");
    } else {
        searchForm.classList.add("d-none");
    }

    setTimeout(function() {
        const messageDiv = document.getElementById("flashMessage");
        if (messageDiv) messageDiv.style.display = "none";
    }, 2000); // 5000 ms = 5 detik
});

function toggleUploadButton() {
    const fileInput = document.getElementById("file");
    const uploadButton = document.getElementById("uploadButton");

    // Periksa apakah ada file yang dipilih
    if (fileInput.files && fileInput.files.length > 0) {
        uploadButton.disabled = false; // Aktifkan tombol
    } else {
        uploadButton.disabled = true; // Nonaktifkan tombol
    }
}