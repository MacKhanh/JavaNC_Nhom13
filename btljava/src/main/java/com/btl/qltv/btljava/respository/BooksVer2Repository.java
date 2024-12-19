package com.btl.qltv.btljava.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qltv.btljava.entity.BooksVer2;

public interface BooksVer2Repository extends JpaRepository<BooksVer2, String>{
	List<BooksVer2> findAll();
}
