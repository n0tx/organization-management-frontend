
//if (window.location.hostname === "localhost" && window.location.pathname === "/") {
//    document.getElementById("email").value = "riki@mail.com";
//    document.getElementById("password").value = "12345";
//}

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