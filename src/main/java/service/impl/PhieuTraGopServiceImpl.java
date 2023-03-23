package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.PhieuTraGop;
//import repository.PhieuTraGopRepository;
import repository.impl.PhieuTraGopRepositoryImpl;
import service.PhieuTraGopService;
import viewmodel.PhieuTraGopViewModel;

public class PhieuTraGopServiceImpl implements PhieuTraGopService {

    private PhieuTraGopRepositoryImpl repository = new PhieuTraGopRepositoryImpl();

    @Override

    public List<PhieuTraGopViewModel> getAll() {
        List<PhieuTraGop> listRepo = repository.getAll();
        List<PhieuTraGopViewModel> listView = new ArrayList<>();

        for (PhieuTraGop phieuTraGop : listRepo) {

            PhieuTraGopViewModel traGopViewModel = new PhieuTraGopViewModel();
            traGopViewModel.setId(phieuTraGop.getId());
            traGopViewModel.setNgayDong(phieuTraGop.getNgayTao());
            traGopViewModel.setMaPhieu(phieuTraGop.getMaPhieu());
//            traGopViewModel.setKhachHang(phieuTraGop.getHoaDon().getKhachHang().getHoTen());
//            traGopViewModel.setMaDon(phieuTraGop.getHoaDon().get);
//            traGopViewModel.setTongTien(phieuTraGop.getHoaDon().getTongTien() - phieuTraGop.getHoaDon().getTienGiam()); // tổng tiền - tiền giảm
            traGopViewModel.setDaTra(phieuTraGop.getTongTienDaTra());

            traGopViewModel.setConNo(0);
            traGopViewModel.setTrangThai(false);
            listView.add(traGopViewModel);
        }
        return listView;
//        return null;
    }

    @Override
    public List<PhieuTraGopViewModel> getByString(String s) {
        List<PhieuTraGop> listRepo = repository.getByString(s);
        List<PhieuTraGopViewModel> listView = new ArrayList<>();

        for (PhieuTraGop phieuTraGop : listRepo) {

            PhieuTraGopViewModel traGopViewModel = new PhieuTraGopViewModel();
            traGopViewModel.setId(phieuTraGop.getId());
            traGopViewModel.setNgayDong(phieuTraGop.getNgayTao());
            traGopViewModel.setMaPhieu(phieuTraGop.getMaPhieu());
//            traGopViewModel.setKhachHang(phieuTraGop.getHoaDon().getKhachHang().getHoTen());
//            traGopViewModel.setMaDon(phieuTraGop.getHoaDon().get);
//            traGopViewModel.setTongTien(phieuTraGop.getHoaDon().getTongTien() - phieuTraGop.getHoaDon().getTienGiam()); // tổng tiền - tiền giảm
            traGopViewModel.setDaTra(phieuTraGop.getTongTienDaTra());

            traGopViewModel.setConNo(0);
            traGopViewModel.setTrangThai(false);
            listView.add(traGopViewModel);
        }
        return listView;
    }

    @Override
    public List<PhieuTraGopViewModel> getByTime(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PhieuTraGopViewModel> getByTrangThai(int index) {
        List<PhieuTraGop> listRepo = repository.getByTrangThai(index);
        List<PhieuTraGopViewModel> listView = new ArrayList<>();

        for (PhieuTraGop phieuTraGop : listRepo) {

            PhieuTraGopViewModel traGopViewModel = new PhieuTraGopViewModel();
            traGopViewModel.setId(phieuTraGop.getId());
            traGopViewModel.setNgayDong(phieuTraGop.getNgayTao());
            traGopViewModel.setMaPhieu(phieuTraGop.getMaPhieu());
//            traGopViewModel.setKhachHang(phieuTraGop.getHoaDon().getKhachHang().getHoTen());
//            traGopViewModel.setMaDon(phieuTraGop.getHoaDon().get);
//            traGopViewModel.setTongTien(phieuTraGop.getHoaDon().getTongTien() - phieuTraGop.getHoaDon().getTienGiam()); // tổng tiền - tiền giảm
            traGopViewModel.setDaTra(phieuTraGop.getTongTienDaTra());

            traGopViewModel.setConNo(0);
            traGopViewModel.setTrangThai(false);
            listView.add(traGopViewModel);
        }
        return listView;
    }

    @Override
    public String insert(PhieuTraGop phieuTraGop) {

        boolean check = repository.insert(phieuTraGop);
        if (check) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(int id, PhieuTraGop phieuTraGop) {
        boolean check = repository.update(id, phieuTraGop);
        if (check) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }

    @Override
    public PhieuTraGop getByID(int i) {
        return repository.getByID(i);
    }

}
