const todayData = [
  { time: "6:30 PM", name: "Martinez", guests: 2, table: "Table 5", status: "Confirmed" },
  { time: "7:00 PM", name: "Johnson", guests: 4, table: "Table 8", status: "Confirmed" },
  { time: "7:15 PM", name: "Garcia", guests: 6, table: "Table 12", status: "Pending" },
  { time: "7:30 PM", name: "Williams", guests: 3, table: "Table 7", status: "Confirmed" },
  { time: "8:00 PM", name: "Brown", guests: 2, table: "Table 3", status: "Confirmed" },
];

const upcomingData = [
  { date: "May 13", time: "6:00 PM", name: "Davis", guests: 5, table: "Table 10", status: "Confirmed" },
  { date: "May 13", time: "7:30 PM", name: "Miller", guests: 2, table: "Table 4", status: "Confirmed" },
  { date: "May 14", time: "6:45 PM", name: "Wilson", guests: 4, table: "Table 9", status: "Pending" },
];

function renderToday() {
  const tbody = document.getElementById("todayReservations");
  todayData.forEach(item => {
    tbody.innerHTML += `
      <tr>
        <td>📅 ${item.time}</td>
        <td>${item.name}</td>
        <td>${item.guests} guests</td>
        <td>${item.table}</td>
        <td>${item.status}</td>
        <td>👁️ ✏️</td>
      </tr>
    `;
  });
}

function renderUpcoming() {
  const tbody = document.getElementById("upcomingReservations");
  upcomingData.forEach(item => {
    tbody.innerHTML += `
      <tr>
        <td>📅 ${item.date} ${item.time}</td>
        <td>${item.name}</td>
        <td>${item.guests} guests</td>
        <td>${item.table}</td>
        <td>${item.status}</td>
        <td>👁️ ✏️</td>
      </tr>
    `;
  });
}

// Account dropdown logic
const accountBtn = document.getElementById("accountBtn");
const accountMenu = document.getElementById("accountMenu");
const logoutBtn = document.getElementById("logoutBtn");
const deleteAccountBtn = document.getElementById("deleteAccountBtn");

accountBtn.addEventListener("click", () => {
  accountMenu.classList.toggle("show");
});

logoutBtn.addEventListener("click", () => {
  alert("Logging out...");
  window.location.href = "login.html";
});

deleteAccountBtn.addEventListener("click", () => {
  if (confirm("Are you sure you want to delete your account?")) {
    alert("Account deleted.");
    window.location.href = "login.html";
  }
});

document.addEventListener("click", (e) => {
  if (!accountBtn.contains(e.target) && !accountMenu.contains(e.target)) {
    accountMenu.classList.remove("show");
  }
});

renderToday();
renderUpcoming();
