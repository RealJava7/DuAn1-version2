package service.impl;

import java.util.List;
import model.KhachHang;
import repository.KhachHangRepository;
import service.QuanLyKhachHangService;
import viewmodel.KhachHangResponse;

public class QuanLyKhachHangServiceImpl implements QuanLyKhachHangService {

    KhachHangRepository repo = new KhachHangRepository();

    @Override
    public List<KhachHangResponse> getAll() {
        return repo.getAll();
    }

    @Override
    public String add(KhachHang kh) {
        if (repo.add(kh) == true) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String edit(KhachHang kh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
