const orderTableBody = document.querySelector("#orderTable tbody");
const subtotalEl = document.getElementById('subtotal');
const taxEl = document.getElementById('tax');
const totalEl = document.getElementById('total');

let orderItems = [];

function addItem(name, price) {
  const existingItem = orderItems.find(item => item.name === name);
  if (existingItem) {
    existingItem.quantity += 1;
  } else {
    orderItems.push({ name, price, quantity: 1 });
  }
  renderOrder();
}

function removeItem(name) {
  orderItems = orderItems.filter(item => item.name !== name);
  renderOrder();
}

function renderOrder() {
  orderTableBody.innerHTML = '';
  let subtotal = 0;
  orderItems.forEach(item => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${item.name}</td>
      <td>${item.quantity}</td>
      <td>$${(item.price * item.quantity).toFixed(2)}</td>
      <td><button onclick="removeItem('${item.name}')">❌</button></td>
    `;
    orderTableBody.appendChild(row);
    subtotal += item.price * item.quantity;
  });

  const tax = subtotal * 0.08;
  const total = subtotal + tax;

  subtotalEl.innerText = subtotal.toFixed(2);
  taxEl.innerText = tax.toFixed(2);
  totalEl.innerText = total.toFixed(2);
}
