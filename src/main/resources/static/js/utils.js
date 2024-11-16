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