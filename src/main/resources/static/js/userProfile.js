$(document).ready(function() {
    $('#updateUser').click(function (event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của nút submit

        // Lấy dữ liệu từ form
        var formData = {
            userName: $('#userName').val(),
            name: $('#displayName').val(),
            email: $('#email').val(),
            address: $('#address').val()
        };

        // Gửi AJAX request
        $.ajax({
            method: "POST",
            url: '/profile',
            contentType: "application/json", // Thiết lập contentType là JSON
            data: JSON.stringify(formData),
            success: function (user) {
                // Cập nhật lại các giá trị trên form với dữ liệu mới nhận được
                $('#displayName').val(user.name);
                $('#email').val(user.email);
                $('#address').val(user.address);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log("Error: " + errorThrown);
            }
        });
    });

    $('.nav-link').click(function (e) {
        e.preventDefault(); // Prevent default anchor click behavior
        $('.nav-link').removeClass('active'); // Remove 'active' class from all nav-links
        $(this).addClass('active'); // Add 'active' class to the clicked nav-link

        $('.tab-pane').removeClass('active'); // Remove 'active' class from all tab-panes
        var target = $(this).attr('href'); // Get the target tab-pane's id
        $(target).addClass('active'); // Add 'active' class to the target tab-pane
    });



});
function goBack() {
    window.history.back();
}