package com.btl.qltv.btljava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.btl.qltv.btljava.dto.BookDTO;
import com.btl.qltv.btljava.entity.Books;
import com.btl.qltv.btljava.entity.User;
import com.btl.qltv.btljava.respository.UserRepository;
import com.btl.qltv.btljava.service.BookService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/sach")
public class BookController {
	
	@GetMapping("/home")
	public String goHome() {
		return "sach/home";
	}
	@GetMapping("/books")
	public String goLibrary() {
		return "sach/books";
	}
	@GetMapping("/login")
		public String showLogin() {
			return "sach/login";
	}
	@GetMapping("/register")
	public String showRegister() {
		return "sach/register";
	}

}
