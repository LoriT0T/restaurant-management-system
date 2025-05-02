let selectedRole = null;

function selectRole(role, element) {
  selectedRole = role;
  localStorage.setItem("role", role);

  // Highlight selected
  document.querySelectorAll(".role").forEach(div => div.classList.remove("selected"));
  element.classList.add("selected");
}

function handleLogin(e) {
  e.preventDefault();

  if (!selectedRole) {
    alert("Please select a role before logging in.");
    return false;
  }

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();

  if (username && password) {
    switch (selectedRole) {
      case "admin":
      case "manager":
        window.location.href = "reservationdashboard.html";
        break;
      case "waiter":
        window.location.href = "orders.html";
        break;
      case "kitchen":
        window.location.href = "kitchen.html";
        break;
      case "customer":
        window.location.href = "createorder.html";
        break;
      default:
        alert("Invalid role selected.");
    }
  }

  return false;
}

