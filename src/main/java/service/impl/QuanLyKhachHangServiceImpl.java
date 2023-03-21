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
        List<KhachHang> lists = repo.
    }

}
