document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault(); // Ngăn gửi form mặc định

    const email = document.getElementById('email_field').value;
    const password = document.getElementById('password_field').value;
	if (!email || !password) {
	               event.preventDefault(); // Ngừng gửi form nếu thiếu thông tin
	               alert('Vui lòng nhập đủ thông tin.');
	               return;
	}

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            // Đăng nhập thành công
            alert('Đăng nhập thành công!');
            window.location.href = '/admin/dashboard'; // Chuyển hướng đến trang dashboard
        } else {
            // Xử lý lỗi
            const errorText = await response.text();
            alert(errorText); // Hiển thị lỗi
        }
    } catch (error) {
        messageElement.textContent = 'Có lỗi xảy ra. Vui lòng thử lại!';
        console.error('Error:', error);
    }
});

     
