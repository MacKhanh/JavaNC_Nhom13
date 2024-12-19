package com.btl.qltv.btljava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DANH_MUC")
public class Category {	
	@Id
	@Column(name="MaDM")
    private String MaDM;
	@Column(name="TenDM")
	private String TenDM;
	public Category() {
	}
	public Category(String maDM, String tenDM) {
		MaDM = maDM;
		TenDM = tenDM;
	}
	public String getMaDM() {
		return MaDM;
	}
	public void setMaDM(String maDM) {
		MaDM = maDM;
	}
	public String getTenDM() {
		return TenDM;
	}
	public void setTenDM(String tenDM) {
		TenDM = tenDM;
	}
	
}
