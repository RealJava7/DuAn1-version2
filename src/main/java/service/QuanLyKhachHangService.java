package service;

import java.util.List;
import model.KhachHang;
import viewmodel.KhachHangResponse;

public interface QuanLyKhachHangService {

    List<KhachHangResponse> getAll();

    String add(KhachHang kh);

    String edit(KhachHang kh);
}
