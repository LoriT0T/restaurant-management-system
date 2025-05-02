const todayOrders = [
  { id: "#1051", table: 2, items: "Burger & Fries", time: "12:30" },
  { id: "#1052", table: 3, items: "Pizza Margherita", time: "13:00" }
];

const yesterdayOrders = [
  { id: "#1045", table: 1, items: "Grilled Salmon", time: "19:00" },
  { id: "#1046", table: 5, items: "Pasta Alfredo", time: "20:15" }
];

function displayOrders(list, elementId) {
  const container = document.getElementById(elementId);
  container.innerHTML = list.map(order => `
    <div class="order-entry">
      <strong>${order.id}</strong> — ${order.items} (Table ${order.table}) at ${order.time}
    </div>
  `).join('');
}

function updateSummaries() {
  document.getElementById("todays-summary").textContent = `${todayOrders.length} Orders`;
  document.getElementById("avg-time").textContent = "15 mins";
}

window.onload = () => {
  displayOrders(todayOrders, "today-orders");
  displayOrders(yesterdayOrders, "yesterday-orders");
  updateSummaries();
};
