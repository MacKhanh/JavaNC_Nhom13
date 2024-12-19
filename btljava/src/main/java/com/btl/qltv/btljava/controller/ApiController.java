package com.btl.qltv.btljava.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qltv.btljava.entity.Books;
import com.btl.qltv.btljava.entity.BooksVer2;
import com.btl.qltv.btljava.entity.Category;
import com.btl.qltv.btljava.phantrang.PagedRespone;
import com.btl.qltv.btljava.service.BookService;



@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ApiController {
	
	private BookService bookService;
	
	public ApiController(BookService bookService) {
		this.bookService = bookService;
	}
	@GetMapping("/allBooks")
    public ResponseEntity<PagedRespone<Books>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Books> sach = bookService.getAllBooks(page, size);
        PagedRespone<Books> response = new PagedRespone<>(sach);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/booksVer2")
    public Map<String, Long> getBooksVer2() {
        // Lấy tất cả sách và danh mục
        List<BooksVer2> books = bookService.layAllSach(); 
        List<Category> categories = bookService.layAllDanhMuc(); 

        // Mapping danh mục (id -> name)
        Map<String, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::getMaDM, Category::getTenDM));

        // Đếm số lượng sách theo thể loại
        return books.stream()
                .collect(Collectors.groupingBy(
                        book -> categoryMap.get(book.getCategory().getMaDM()),
                        Collectors.counting()
                ));
    }
}
