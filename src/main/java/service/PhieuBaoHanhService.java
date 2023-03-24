package service;

import java.util.List;
import viewmodel.PhieuBaoHanhResponse;

public interface PhieuBaoHanhService {

    List<PhieuBaoHanhResponse> getAll();

    String add(PhieuBaoHanhResponse pbh);

    List<PhieuBaoHanhResponse> getAllStatus(boolean status);

    List<PhieuBaoHanhResponse> getAllListSearch(String tenKH);

    List<String> getAllLoaiBaoHanh();
}
