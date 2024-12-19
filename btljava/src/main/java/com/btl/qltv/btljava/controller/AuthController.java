package com.btl.qltv.btljava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qltv.btljava.dto.LoginRequest;
import com.btl.qltv.btljava.dto.RegisterRequest;
import com.btl.qltv.btljava.entity.User;
import com.btl.qltv.btljava.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 @Autowired
	    private UserService userService;

	 @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
	 
	 @PostMapping("/login")
	 public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		 	String email = loginRequest.getEmail();
	        String password = loginRequest.getPassword();

	        // Kiểm tra người dùng
	        User user = userService.findEmail(email);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
	        }

	        // Kiểm tra mật khẩu
	        if (!user.getPassword().equals(password)) { // Nếu có mã hóa, sử dụng bcrypt.matches(password, user.getPassword())
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mật khẩu không đúng");
	        }

	        // Xác thực thành công
	        return ResponseEntity.ok("Đăng nhập thành công, chuyển đến trang admin");
	 }
	 
	 @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
	        String email = registerRequest.getEmail();

	        // Kiểm tra email đã tồn tại
	        if (userService.findEmail(email) != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email đã tồn tại");
	        }

	        // Tạo người dùng mới
	        User newUser = new User();
	        newUser.setUserName(registerRequest.getUsername());
	        newUser.setEmail(registerRequest.getEmail());
	        newUser.setPassword(registerRequest.getPassword()); // Nếu có mã hóa, hãy mã hóa ở đây
	        userService.saveUser(newUser);

	        return ResponseEntity.status(HttpStatus.CREATED).body("Đăng ký thành công");
	    }
}
