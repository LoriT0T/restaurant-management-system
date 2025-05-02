document.getElementById("reservationForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const formData = new FormData(this);
  const data = {};
  formData.forEach((val, key) => (data[key] = val));

  console.log("Reservation Submitted:", data);

  alert("Reservation successfully created!");
  this.reset();
});
