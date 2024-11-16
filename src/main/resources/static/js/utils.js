// Trigger form submission when Enter is pressed in the password input
function submitOnEnter(event) {
    // Check if the key pressed is "Enter"
    if (event.key === "Enter") {
        event.preventDefault(); // Prevent the default action of the Enter key
        document.getElementById("login").submit(); // Submit the form
    }
}