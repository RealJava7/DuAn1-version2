/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.PhieuTraGop;
import repository.PhieuTraGopRepository;
import repository.impl.PhieuTraGopRepositoryImpl;
import service.PhieuTraGopService;
import viewmodel.PhieuTraGopViewModel;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopServiceImpl implements PhieuTraGopService {

    private PhieuTraGopRepository repository = new PhieuTraGopRepositoryImpl();

    @Override

    public List<PhieuTraGopViewModel> getAll() {
        List<PhieuTraGop> listRepo = repository.getAll();
        List<PhieuTraGopViewModel> listView = new ArrayList<>();

        for (PhieuTraGop phieuTraGop : listRepo) {
            PhieuTraGopViewModel traGopViewModel = new PhieuTraGopViewModel();
            traGopViewModel.setNgayDong(phieuTraGop.getNgayTao());
            traGopViewModel.setMaPhieu(phieuTraGop.getMaPhieu());
            traGopViewModel.setKhachHang(phieuTraGop.getHoaDon().getKhachHang().getHoTen());
//            traGopViewModel.setMaDon(phieuTraGop.getHoaDon().get);
            traGopViewModel.setTongTien(phieuTraGop.getHoaDon().getTongTien() - phieuTraGop.getHoaDon().getTienGiam()); // tổng tiền - tiền giảm
//            traGopViewModel.setDaTra(phieuTraGop.getNgayTao()); làm sau

            traGopViewModel.setConNo(0);
            traGopViewModel.setTrangThai(false);
        }
        return listView;
    }

    @Override
    public List<PhieuTraGopViewModel> getByString(String s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PhieuTraGopViewModel> getByTime(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PhieuTraGopViewModel> getByTrangThai(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
