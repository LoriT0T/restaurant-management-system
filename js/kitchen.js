const orders = [
  {
    id: 1042,
    table: 'Table 2',
    items: 2,
    time: '18:15',
    status: 'pending'
  },
  {
    id: 1043,
    table: 'Table 3 + 4',
    items: 4,
    time: '18:30',
    status: 'pending'
  },
  {
    id: 1044,
    table: 'Takeout',
    items: 3,
    time: '18:45',
    status: 'pending'
  }
];

const items = [
  { id: "#1042-1", dish: "Grilled Salmon", table: "2", special: "No sauce", status: "Pending" },
  { id: "#1042-2", dish: "Caesar Salad", table: "2", special: "No modifications", status: "Pending" },
  { id: "#1043-1", dish: "Ribeye Steak", table: "3", special: "Medium rare", status: "Pending" },
  { id: "#1043-2", dish: "Mushroom Risotto", table: "3", special: "Extra parmesan", status: "Pending" },
  { id: "#1043-3", dish: "Garlic Bread", table: "3", special: "No modifications", status: "Pending" },
  { id: "#1043-4", dish: "Tiramisu", table: "3", special: "No modifications", status: "Pending" }
];

function loadOrders() {
  const container = document.getElementById("ordersContainer");
  container.innerHTML = "";
  orders.forEach(order => {
    const div = document.createElement("div");
    div.className = "order-card";
    div.innerHTML = `
      <h3>Order #${order.id}</h3>
      <p>${order.table} • ${order.items} items • ${order.time}</p>
      <button class="start-btn">Start Cooking</button>
      <button class="view-btn">View Details</button>
    `;
    container.appendChild(div);
  });
}

function loadOrderItems() {
  const body = document.getElementById("orderItemsBody");
  items.forEach(item => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${item.id}</td>
      <td>${item.dish}</td>
      <td>Table ${item.table}</td>
      <td>${item.special}</td>
      <td>${item.status}</td>
      <td>⏱️</td>
    `;
    body.appendChild(tr);
  });
}

function filterOrders(status) {
  document.querySelectorAll(".tab").forEach(t => t.classList.remove("active"));
  event.target.classList.add("active");
  console.log("Filtering orders:", status);
}

loadOrders();
loadOrderItems();
