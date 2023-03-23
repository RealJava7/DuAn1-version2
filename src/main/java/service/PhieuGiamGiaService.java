package service;

import java.util.List;
import model.PhieuGiamGia;
import viewmodel.PhieuGiamGiaResponse;

public interface PhieuGiamGiaService {

    List<PhieuGiamGiaResponse> getall();

    String add(PhieuGiamGia phieu);

    String update(PhieuGiamGiaResponse phieu);

    List<PhieuGiamGiaResponse> getByStatus(int tt);

    List<PhieuGiamGiaResponse> getByName(String name);

    List<PhieuGiamGiaResponse> getByMa(String ma);

}
