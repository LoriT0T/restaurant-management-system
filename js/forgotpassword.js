function handleForgotPassword(event) {
  event.preventDefault();

  const input = document.getElementById("userInput").value.trim();
  const message = document.getElementById("message");

  if (input === "") {
    message.textContent = "Please enter a valid email or username.";
    message.style.color = "red";
    return;
  }

  // Placeholder for actual reset logic
  message.textContent = "If an account exists for the entered info, a reset link will be sent.";
  message.style.color = "green";

  // Clear the input field
  document.getElementById("userInput").value = "";
}
