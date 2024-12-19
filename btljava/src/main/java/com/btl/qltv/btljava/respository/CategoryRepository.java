package com.btl.qltv.btljava.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qltv.btljava.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	List<Category> findAll();
}
