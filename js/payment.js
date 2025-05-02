function applyCoupon() {
  const couponInput = document.getElementById('coupon-code').value.trim();
  const discountEl = document.getElementById('discount');
  const totalEl = document.getElementById('total');

  let subtotal = 105.93; // hardcoded for now
  let tax = 8.74;
  let serviceCharge = 19.07;
  let discount = 5.00; // default discount shown
  let total = subtotal + tax + serviceCharge - discount;

  if (couponInput.toLowerCase() === 'save10') {
    discount = 10.00;
    total = subtotal + tax + serviceCharge - discount;
    discountEl.innerText = `- $${discount.toFixed(2)}`;
    totalEl.innerText = `$${total.toFixed(2)}`;
    alert('Coupon applied successfully!');
  } else {
    alert('Invalid coupon code.');
  }
}
