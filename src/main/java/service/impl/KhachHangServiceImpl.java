package service.impl;

import java.util.List;
import model.KhachHang;
import repository.KhachHangRepository;
import viewmodel.KhachHangResponse;
import service.KhachHangService;

public class KhachHangServiceImpl implements KhachHangService {

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
    public String update(KhachHangResponse kh) {
        if (repo.update(kh) == true) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

}
