package com.btl.qltv.btljava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.btl.qltv.btljava.phantrang.PagedRespone;
import com.btl.qltv.btljava.service.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	private BookService bookService;
	@GetMapping("/thongke")
	public String bieuDoThongKe() {
		return "admin/thongke";
	}
	
	@GetMapping("/dashboard")
	public String showDashboard() {
		return "admin/dashboard";
	}
	 @GetMapping("/search")
	 public String search(@RequestParam String tenSach, Model model) {
	    List<Books> books = bookService.searchByTenSach(tenSach);
	    model.addAttribute("books", books);
	    return "admin/search";
	 }
	 
	 @GetMapping("/create")
	 public String showCreatePage(Model model) {
	     model.addAttribute("bookDTO", new BookDTO());  
	     return "admin/create";  
	 }
	 
	 @PostMapping("/create")
	 public String createBook(@Valid @ModelAttribute("bookDTO") BookDTO bookDTO,
			 BindingResult result,
			 RedirectAttributes redirectAttributes) {
		 
		 if (result.hasErrors()) {
	            return "admin/create";  
	     }
	     // Gọi service để thêm sách vào cơ sở dữ liệu
	     bookService.addBook(bookDTO);
	     // Thêm thông báo thêm sách thành công
	     redirectAttributes.addFlashAttribute("successMessage", "Sách đã được thêm thành công!");
	     // Sau khi thêm thành công, chuyển hướng về trang đầu tiên của danh sách sách
	     return "redirect:/admin/quanly?page=0"; 
	 }
	 
	 @GetMapping("/delete")
	 public String deleteBook(@RequestParam String MaSach,RedirectAttributes redirectAttributes) {
		 bookService.deleteBook(MaSach);
		// Thêm thông báo xóa sách thành công
		redirectAttributes.addFlashAttribute("successMessage", "Sách có mã "+MaSach+" đã được xóa thành công!");
		// Sau khi xóa thành công, chuyển hướng về trang đầu tiên của danh sách sách
	     return "redirect:/admin/quanly?page=0";
	 }
	 
	 @GetMapping("/edit")
	 public String showEditPage(Model model, @RequestParam String MaSach) {
	     return bookService.findById(MaSach)
	            .map(sach -> {
	                BookDTO bookDTO = convertToDTO(sach);
	                model.addAttribute("bookDTO", bookDTO);
	                return "admin/edit";
	            })
	              .orElse("redirect:/admin/quanly?page=0");
	 }
	 private BookDTO convertToDTO(Books book) {
	        BookDTO bookDTO = new BookDTO();
	        bookDTO.setMaSach(book.getMaSach());
	        bookDTO.setTenSach(book.getTenSach());
	        bookDTO.setGiaGoc(book.getGiaGoc());
	        bookDTO.setGiaKM(book.getGiaKM());
	        bookDTO.setTenTG(book.getTenTG());
	        bookDTO.setTenDoiTuong(book.getTenDoiTuong());
	        bookDTO.setSoTrang(book.getSoTrang());
	        bookDTO.setSoLuongCon(book.getSoLuongCon());
	        bookDTO.setLinkAnh(book.getLinkAnh());
	        bookDTO.setMaDM(book.getMaDM());
	        return bookDTO;
	    }
	 @PostMapping("/edit")
	 public String editBook(@Valid @ModelAttribute("bookDTO") BookDTO bookDTO,
	                           BindingResult result, Model model, RedirectAttributes redirectAttributes) {
	     if (result.hasErrors()) {
	            return "admin/edit";
	     }
	     bookService.updateBook(bookDTO);
	  // Thêm thông báo xóa sách thành công
	     redirectAttributes.addFlashAttribute("successMessage", "Sách có mã "+ bookDTO.getMaSach()+" đã được cập nhật thành công!");
	     return "redirect:/admin/quanly";
	 }
	 @GetMapping("/quanly")
	 public String getBooks(@RequestParam(defaultValue = "0") int page, Model model) {
	     // Lấy dữ liệu phân trang từ API (có thể gọi API hoặc sử dụng dịch vụ trực tiếp)
	     Page<Books> booksPage = bookService.getAllBooks(page, 20);  // Size là 20 ví dụ
	     PagedRespone<Books> pagedResponse = new PagedRespone<>(booksPage);

	     // Thêm các đối tượng vào model
	     model.addAttribute("books", pagedResponse.getItems());
         model.addAttribute("currentPage", pagedResponse.getCurrentPage());
         model.addAttribute("totalPages", pagedResponse.getTotalPages());
         model.addAttribute("size",20);
	     return "admin/quanly";
	 }
	 
}
