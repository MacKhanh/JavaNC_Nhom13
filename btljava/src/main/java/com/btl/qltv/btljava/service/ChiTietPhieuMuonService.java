/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.qltv.btljava.service;


import com.btl.qltv.btljava.entity.PhieuChiTietEntity;
import com.btl.qltv.btljava.respository.PhieuMuonChiTietRespone;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietPhieuMuonService {
    @Autowired
    private PhieuMuonChiTietRespone chiTietPhieuMuonRepository;
    
    public void Save(PhieuChiTietEntity chiTietPhieuMuon) {
        chiTietPhieuMuonRepository.save(chiTietPhieuMuon);
    }

    public List<PhieuChiTietEntity> findAll() {
        return chiTietPhieuMuonRepository.findAll();
    }

}

   
