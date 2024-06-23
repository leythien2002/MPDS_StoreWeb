function goBack() {
    window.history.back();
}
function showForm(productId) {
    var formPopup = document.getElementById('form-popup');
    document.getElementById("productId").value = productId;
    formPopup.style.display = 'block';
}

function closeForm() {
    var formPopup = document.getElementById('form-popup');
    formPopup.style.display = 'none';
}