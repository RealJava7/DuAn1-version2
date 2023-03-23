package service;

import java.util.List;
import model.KhachHang;
import viewmodel.KhachHangResponse;

public interface KhachHangService {

    List<KhachHangResponse> getAll(int trangThai);

    List<KhachHangResponse> findBySdt(String sdt, int trangThai);

    List<KhachHangResponse> sortByName(Boolean c, int trangThai);

    String add(KhachHang kh);

    String update(KhachHangResponse kh);

    void updateKhoiPhuc(KhachHangResponse kh, int trangThai);

}
