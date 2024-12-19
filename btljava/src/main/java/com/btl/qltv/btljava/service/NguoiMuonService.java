package com.btl.qltv.btljava.service;
import com.btl.qltv.btljava.entity.NguoiMuon;
import com.btl.qltv.btljava.respository.NguoiMuonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiMuonService {
    @Autowired
    private NguoiMuonRepository repository;
    // hien thi danh sach nguoi muon
    public List<NguoiMuon> findAll() {
        return repository.findAll();
    }
    // them moi 
    public Boolean AddNguoiMuon(NguoiMuon nguoiMuon){
        try{
            NguoiMuon nguoi = new NguoiMuon();
            nguoi.setMaSinhVien(nguoiMuon.getMaSinhVien());
            nguoi.setTenSinhVien(nguoiMuon.getTenSinhVien());
            nguoi.setLop(nguoiMuon.getLop());
            nguoi.setSoDienThoai(nguoiMuon.getSoDienThoai());
            repository.save(nguoi);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
        
    }
    // tim kiem nguoi muon theo ID
    public Optional<NguoiMuon> findById(String id) {
        return repository.findById(id);
    }
    // sua thông tin nguoi muon
    public NguoiMuon save(NguoiMuon nguoimuon){
        return repository.save(nguoimuon);
    }
  
    // xoa nguoi muon
    public void deleteById(String id) {
        repository.deleteById(id);
    }
     // tim kiem thong tin nguoi muon theo tên 
    public List<NguoiMuon> seach(String tenSinhVien){
        return repository.findByTenSinhVienContainingIgnoreCase(tenSinhVien);
        
    }
    public List<NguoiMuon> seachID(String maSinhVien){
    	return repository.findByMaSinhVienContainingIgnoreCase(maSinhVien);
    }
}

