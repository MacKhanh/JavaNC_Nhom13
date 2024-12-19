package com.btl.qltv.btljava.entity;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TAI_KHOAN")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Email")
	private String email;
    @Column(name = "Password")
	private String password;
    @Column(name = "roles")
    private String roles;
	public User() {
	}
	
	public User(long id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//	    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles));
//	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
