package com.btl.qltv.btljava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SACH")
public class BooksVer2 {
	@Id
	@Column(name="MaSach")
	private String MaSach;
	@Column(name="TenSach")
	private String tenSach;
	@Column(name="LinkAnh")
	private String LinkAnh;
	@Column(name="GiaGoc")
	private String GiaGoc;
	@Column(name="GiaKM")
	private String GiaKM;
	@Column(name="TenTacGia")
	private String TenTG;
	@Column(name="TenDoiTuong")
	private String TenDoiTuong;
	@Column(name="SoTrang")
	private String SoTrang;
	@Column(name="SoLuongCon")
	private int soLuongCon;
	
	@ManyToOne
    @JoinColumn(name = "MaDM")
	private Category category;

	public BooksVer2() {
	}

	public BooksVer2(String maSach, String tenSach, String linkAnh, String giaGoc, String giaKM, String tenTG,
			String tenDoiTuong, String soTrang, int soLuongCon, Category category) {
		super();
		MaSach = maSach;
		this.tenSach = tenSach;
		LinkAnh = linkAnh;
		GiaGoc = giaGoc;
		GiaKM = giaKM;
		TenTG = tenTG;
		TenDoiTuong = tenDoiTuong;
		SoTrang = soTrang;
		this.soLuongCon = soLuongCon;
		this.category = category;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getLinkAnh() {
		return LinkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		LinkAnh = linkAnh;
	}

	public String getGiaGoc() {
		return GiaGoc;
	}

	public void setGiaGoc(String giaGoc) {
		GiaGoc = giaGoc;
	}

	public String getGiaKM() {
		return GiaKM;
	}

	public void setGiaKM(String giaKM) {
		GiaKM = giaKM;
	}

	public String getTenTG() {
		return TenTG;
	}

	public void setTenTG(String tenTG) {
		TenTG = tenTG;
	}

	public String getTenDoiTuong() {
		return TenDoiTuong;
	}

	public void setTenDoiTuong(String tenDoiTuong) {
		TenDoiTuong = tenDoiTuong;
	}

	public String getSoTrang() {
		return SoTrang;
	}

	public void setSoTrang(String soTrang) {
		SoTrang = soTrang;
	}

	public int getSoLuongCon() {
		return soLuongCon;
	}

	public void setSoLuongCon(int soLuongCon) {
		this.soLuongCon = soLuongCon;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
