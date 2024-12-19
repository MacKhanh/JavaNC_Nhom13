package com.btl.qltv.btljava.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookDTO {
	@NotBlank(message = "Mã sách không được để trống")
	private String MaSach;
	
	@NotBlank(message = "Tên sách không được để trống")
	private String tenSach;
	
	@NotNull(message = "Giá gốc sách không được để trống")
    @Min(value = 1000, message = "Giá sách phải lớn hơn 1000 VNĐ")
	private String GiaGoc;
	
	@NotNull(message = "Giá khuyến mại sách không được để trống")
    @Min(value = 1000, message = "Giá sách phải lớn hơn 1000 VNĐ")
	private String GiaKM;
	
	@NotBlank(message="Tên Tác giả không để trống")
	private String TenTG;
	
	@Size(min =10, message ="Tên đối tượng tối thiểu 5 từ")
	@Size(max =50, message ="Tên đối tượng tối đa 50 từ")
	private String TenDoiTuong;
	
	@Pattern(regexp = "\\d+", message = "Nhập số nguyên")
	private String SoTrang;
	
	@NotNull(message = "Số lượng hàng không được để trống.")
	@Min(value = 0, message = "Số lượng hàng phải lớn hơn hoặc bằng 0.")
 	private int SoLuongCon;
	
	@NotEmpty(message="Nhập link ảnh")
	private String LinkAnh;
	
	@NotEmpty(message="Nhập theo dạng DMABCB với ABCD là số")
	private String MaDM;

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
		return SoLuongCon;
	}

	public void setSoLuongCon(int soLuongCon) {
		SoLuongCon = soLuongCon;
	}

	public String getLinkAnh() {
		return LinkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		LinkAnh = linkAnh;
	}

	public String getMaDM() {
		return MaDM;
	}

	public void setMaDM(String maDM) {
		MaDM = maDM;
	}
	
}
