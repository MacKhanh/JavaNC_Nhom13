/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.qltv.btljava.service;


import com.btl.qltv.btljava.entity.NguoiMuon;
import com.btl.qltv.btljava.entity.PhieuChiTietEntity;
import com.btl.qltv.btljava.entity.PhieuMuonEntity;
import com.btl.qltv.btljava.respository.NguoiMuonRepository;
import com.btl.qltv.btljava.respository.PhieuMuonChiTietRespone;
import com.btl.qltv.btljava.respository.PhieuMuonRepository;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguyentoan
 */
@Service
public class PhieuMuonService {
    private static final Logger logger = LoggerFactory.getLogger(PhieuMuonService.class);
    @Autowired
    private PhieuMuonRepository phieumuonRS;
    @Autowired
    private PhieuMuonChiTietRespone chitietRS;
    @Autowired
    private NguoiMuonRepository nguoimuonRS;
    
    // hien thi danh sach phieu muon 
    public List<PhieuMuonEntity> getPhieuMuon() {
        List<PhieuMuonEntity> phieumuon = phieumuonRS.findAll();
        for(PhieuMuonEntity pm : phieumuon){
            List<PhieuChiTietEntity> chitiet = this.chitietRS.findByMaPhieuMuon(pm.getMaPhieuMuon());
            pm.setChiTietPhieuMuons(chitiet);
        }
        return phieumuon;
    }
    
    
    // Sua thong tin
     public boolean create(PhieuMuonEntity PM){
        try{
            this.phieumuonRS.save(PM);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
        
    }
    
     // cap nhat thong tin 
     public PhieuMuonEntity save(PhieuMuonEntity phieumuon){
         return phieumuonRS.save(phieumuon);
     }
     public List<PhieuMuonEntity> findAll(){
         return phieumuonRS.findAll();
     }
     // xem thong tin phieu muon thong qua ma sinh vien 
     public List<PhieuMuonEntity> getPhieuMuonByMaSinhVien(String maSinhVien){
         
         List<PhieuMuonEntity> phieuMuons = phieumuonRS.findByMaSinhVien(maSinhVien);
        for(PhieuMuonEntity pm : phieuMuons){
            List<PhieuChiTietEntity> chitiet = chitietRS.findByMaPhieuMuon(pm.getMaPhieuMuon());
            pm.setChiTietPhieuMuons(chitiet);
        }
        return phieuMuons;
     }
     // thuc hien tra sach

    @Transactional
    public boolean checkAndUpdateReturnDate(String phieuMuonId, String ngayTraThucTe) {
        logger.info("Attempting to update return date for PhieuMuon ID: {}", phieuMuonId);
        PhieuMuonEntity phieuMuon = phieumuonRS.findById(phieuMuonId).orElseThrow(() -> new RuntimeException("PhieuMuon not found"));

        // Check if the book has already been returned
        if (phieuMuon.getNgayTraThucTe() != null) {
            logger.info("PhieuMuon ID: {} has already been returned.", phieuMuonId);
            return true; // Indicates that the book was already returned
        }

        // Update the actual return date
        phieuMuon.setNgayTraThucTe(LocalDate.parse(ngayTraThucTe));
        phieumuonRS.save(phieuMuon);
        return false; // Indicates that the book return was successfully processed
    }
    
    public List<PhieuMuonEntity> getDaTra(){
        return phieumuonRS.findReturnedLoans();
    }
    public List<PhieuMuonEntity> getChuaTra(){
        return phieumuonRS.findNotReturnedLoans();
    }
    
    
    // thuc hien danh sach qua han tra sach
    public List<PhieuMuonEntity> getQuaHan(){
        return phieumuonRS.quahan();
    }

    public PhieuMuonService(PhieuMuonRepository phieumuonRS, PhieuMuonChiTietRespone chitietRS) {
        this.phieumuonRS = phieumuonRS;
        this.chitietRS = chitietRS;
    }
    
    // thực hiện tạo phiéu mượn mơi s
    public PhieuMuonEntity createPhieuMuon(PhieuMuonEntity phieuMuon, List<String> bookIds) {
        PhieuMuonEntity phieuMuonSave = phieumuonRS.save(phieuMuon);
        
        List<PhieuChiTietEntity> chiTietPhieuMuons = bookIds.stream()
            .map(bookId -> {
                PhieuChiTietEntity chiTiet = new PhieuChiTietEntity();
                chiTiet.setMaCTPhieuMuon(UUID.randomUUID().toString());
                chiTiet.setMaPhieuMuon(phieuMuonSave.getMaPhieuMuon());
                chiTiet.setMaSach(bookId);
                return chiTiet;
            })
            .collect(Collectors.toList());
        
        chitietRS.saveAll(chiTietPhieuMuons);
        
        return phieuMuon;
    }

    public NguoiMuon createNguoiMuon(NguoiMuon nguoiMuon) {
        return nguoimuonRS.save(nguoiMuon);
    }
    
    @Autowired
    public PhieuMuonService(PhieuMuonRepository phieumuonRS, PhieuMuonChiTietRespone chitietRS, NguoiMuonRepository nguoimuonRS) {
        this.phieumuonRS = phieumuonRS;
        this.chitietRS = chitietRS;
        this.nguoimuonRS = nguoimuonRS;
    }
   // xem thong tin nguoi muon cua tung phieu muon 
    public List<NguoiMuon> getNguoiMuonbyMaSinhVien(String maSinhVien){
        List<PhieuMuonEntity> phieuMuons = phieumuonRS.findByMaSinhVien(maSinhVien);
        List<NguoiMuon> nguoiMuons = new ArrayList<>();
        for(PhieuMuonEntity pm:phieuMuons){
            NguoiMuon nguoiMuon = pm.getNguoiMuon();
            if(!nguoiMuons.contains(nguoiMuon)){
                nguoiMuons.add(nguoiMuon);
            }
        }
        return nguoiMuons;
    }
    
   
}








