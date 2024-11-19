
//if (window.location.hostname === "localhost" && window.location.pathname === "/") {
//    document.getElementById("email").value = "riki@mail.com";
//    document.getElementById("password").value = "12345";
//}

// Sembunyikan elemen setelah 5 detik
document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
        const messageDiv = document.getElementById("flashMessage");
        if (messageDiv) messageDiv.style.display = "none";
    }, 2000); // 5000 ms = 5 detik
});

// Trigger form submission when Enter is pressed in the password input
function submitOnEnterLogin(event) {
    // Check if the key pressed is "Enter"
    if (event.key === "Enter") {
        event.preventDefault(); // Prevent the default action of the Enter key
        document.getElementById("login").submit(); // Submit the form
    }
}

function submitOnEnterRegister(event) {
    // Check if the key pressed is "Enter"
    if (event.key === "Enter") {
        event.preventDefault(); // Prevent the default action of the Enter key
        document.getElementById("register").submit(); // Submit the form
    }
}

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