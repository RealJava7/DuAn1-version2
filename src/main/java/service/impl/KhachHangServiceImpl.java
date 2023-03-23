package service.impl;

import java.util.List;
import model.KhachHang;
import repository.KhachHangRepository;
import viewmodel.KhachHangResponse;
import service.KhachHangService;

public class KhachHangServiceImpl implements KhachHangService {

    KhachHangRepository repo = new KhachHangRepository();

    @Override
    public List<KhachHangResponse> getAll(int trangThai) {
        return repo.getAll(trangThai);
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

    @Override
    public void updateKhoiPhuc(KhachHangResponse kh, int trangThai) {
        repo.updateKhoiPhuc(kh, trangThai);
    }

    @Override
    public List<KhachHangResponse> findBySdt(String sdt, int trangThai) {
        return repo.findBySDT(sdt, trangThai);
    }

    @Override
    public List<KhachHangResponse> sortByName(Boolean c, int trangThai) {
        return repo.sortByName(c, trangThai);
    }

    @Override
    public List<KhachHangResponse> getAllTheTichDiem() {
        return repo.getAllTheTichDiem();
    }

    @Override
    public List<KhachHangResponse> findByMa(int id) {
        return repo.findByMa(id);

    }

}
