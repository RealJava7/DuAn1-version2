package service;

import java.util.List;
import model.PhieuGiamGia;
import viewmodel.PhieuGiamGiaResponse;

public interface QuanLyPhieuGiamGiaService {

    List<PhieuGiamGiaResponse> getall();

    String add(PhieuGiamGia phieu);

    String update(PhieuGiamGiaResponse phieu);

}
