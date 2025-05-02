// access.js
const role = localStorage.getItem("role");
const page = window.location.pathname.split("/").pop();

if (!role) {
  window.location.href = "login.html"; // Force login
}

// Role-based access logic
const accessMatrix = {
  "createorder.html":      ["admin", "manager", "waiter", "customer"],
  "inventory.html":        ["admin", "manager", "kitchen"],
  "kitchen.html":          ["admin", "manager", "kitchen", "waiter"],
  "orders.html":           ["admin", "manager", "waiter", "kitchen", "customer"],
  "reservationdashboard.html": ["admin", "manager", "waiter", "customer"],
  "reservationform.html":  ["admin", "manager", "waiter", "customer"]
};

if (accessMatrix[page] && !accessMatrix[page].includes(role)) {
  alert("You do not have permission to view this page.");
  window.location.href = "login.html";
}
