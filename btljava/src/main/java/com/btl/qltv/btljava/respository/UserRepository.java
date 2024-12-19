package com.btl.qltv.btljava.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qltv.btljava.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
//	Optional<User> findByEmail(String email);
//	boolean existsByEmail(String email);
	User findByEmail(String email);
}
