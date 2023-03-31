package service;

import java.util.List;
import model.ChiTietPhieuBaoHanh;
import model.PhieuBaoHanh;
import viewmodel.PhieuBaoHanhResponse;

public interface PhieuBaoHanhService {

    List<PhieuBaoHanhResponse> getAll();

    String add(PhieuBaoHanh pbh, ChiTietPhieuBaoHanh ctPBH);

    List<PhieuBaoHanhResponse> getAllStatus(boolean status);

    List<PhieuBaoHanhResponse> getAllListSearch(String tenKH);

    List<String> getAllLoaiBaoHanh();

    String updateMoTa(PhieuBaoHanhResponse pbh, int id);

    PhieuBaoHanhResponse getPBHByID(int id);
    
}
