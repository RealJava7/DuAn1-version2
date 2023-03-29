package service;

import java.util.List;
import model.KhachHang;
import viewmodel.KhachHangResponse;

public interface KhachHangService {

    List<KhachHangResponse> getAll();

    KhachHangResponse getKhachHangByEmail(String email);

    KhachHangResponse getKhachHangById(int id);

    KhachHangResponse getKhachHangByMaThe(String maThe);

    List<KhachHangResponse> findBySdt(String sdt, int trangThai);

    List<KhachHangResponse> sortByName(Boolean c, int trangThai);

    String add(KhachHang kh);

    String update(KhachHangResponse kh);

    void updateKhoiPhuc(KhachHangResponse kh, int trangThai);

    List<KhachHangResponse> getAllTheTichDiem();

    List<KhachHangResponse> getTop3KhachHang();

    List<KhachHangResponse> findByMa(String maThe);

    String updateDiem(KhachHangResponse kh, int soDiem);

}
