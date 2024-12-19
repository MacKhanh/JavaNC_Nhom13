package com.btl.qltv.btljava.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.btl.qltv.btljava.dto.BookDTO;
import com.btl.qltv.btljava.entity.Books;
import com.btl.qltv.btljava.entity.BooksVer2;
import com.btl.qltv.btljava.entity.Category;
import com.btl.qltv.btljava.respository.BookRepository;
import com.btl.qltv.btljava.respository.BooksVer2Repository;
import com.btl.qltv.btljava.respository.CategoryRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BooksVer2Repository booksVer2Repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	// phan trang api
	public Page<Books> getAllBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }
	
	private final RestTemplate restTemplate;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Page<Books> getBooks(int page, int size) {
        String url = "http://localhost:8080/api/allBooks?page=" + page + "&size=" + size;
        return restTemplate.getForObject(url, Page.class);
    }
    
    // tim kiem ten sach
    public List<Books> searchByTenSach(String tenSach) {
        return bookRepository.findByTenSachContainingIgnoreCase(tenSach);
    }
    
    public List<BooksVer2> layAllSach() {
        return booksVer2Repository.findAll();
    }
    
    public List<Category> layAllDanhMuc(){
    	return categoryRepository.findAll();
    }
    // them moi sach
    public void addBook(BookDTO bookDTO) {
    	Books newBook = new Books();
    	newBook.setMaSach(bookDTO.getMaSach());
    	newBook.setTenSach(bookDTO.getTenSach());
    	newBook.setGiaGoc(bookDTO.getGiaGoc());
    	newBook.setGiaKM(bookDTO.getGiaKM());
    	newBook.setTenTG(bookDTO.getTenTG());
    	newBook.setLinkAnh(bookDTO.getLinkAnh());
    	newBook.setSoTrang(bookDTO.getSoTrang());
    	newBook.setTenDoiTuong(bookDTO.getTenDoiTuong());
    	newBook.setSoLuongCon(bookDTO.getSoLuongCon());
    	newBook.setMaDM(bookDTO.getMaDM());
    	bookRepository.save(newBook);
    }
    
    //xoa sach
    public void deleteBook(String MaSach) {
    	bookRepository.deleteById(MaSach);
    }
    
    // cap nhat sach
    public Optional<Books> findById(String MaSach) {
        return bookRepository.findById(MaSach);
    }
    public void updateBook(BookDTO bookDTO) {
        Optional<Books> bookOptional = bookRepository.findById(bookDTO.getMaSach());
        if (bookOptional.isPresent()) {
            Books sach = bookOptional.get();
            sach.setTenSach(bookDTO.getTenSach());
            sach.setGiaGoc(bookDTO.getGiaGoc());
            sach.setGiaKM(bookDTO.getGiaKM());
            sach.setTenTG(bookDTO.getTenTG());
            sach.setTenDoiTuong(bookDTO.getTenDoiTuong());
            sach.setSoTrang(bookDTO.getSoTrang());
            sach.setSoLuongCon(bookDTO.getSoLuongCon());
            sach.setLinkAnh(bookDTO.getLinkAnh());
            sach.setMaDM(bookDTO.getMaDM());
            bookRepository.save(sach);
        }
    }
}
