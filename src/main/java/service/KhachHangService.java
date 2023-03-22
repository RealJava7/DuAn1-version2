package service;

import java.util.List;
import model.KhachHang;
import viewmodel.KhachHangResponse;

public interface KhachHangService {

    List<KhachHangResponse> getAll();

    String add(KhachHang kh);

    String update(KhachHangResponse kh);

}
