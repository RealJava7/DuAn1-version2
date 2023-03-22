/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.NhanVien;
import repository.NhanVienRepository;
import service.QuanLyNhanVienService;
import viewmodel.NhanVienResponse;

/**
 *
 * @author Ma
 */
public class QuanLyNhanVienServiceImpl implements QuanLyNhanVienService{
    NhanVienRepository repo = new NhanVienRepository();
    @Override
    public String add(NhanVien nhanVien) {
        if(repo.add(nhanVien) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    @Override
    public String update(NhanVienResponse nhanVienResponse) {
        if(repo.update(nhanVienResponse) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }
    
    @Override
    public String delete(int id) {
        if(repo.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }
    
    @Override
    public List<NhanVienResponse> getAllLam() {
        return repo.getAllLam();
    }
    
    @Override
    public List<NhanVienResponse> getAllNghi() {
        return repo.getAllNghi();
    }
    
    @Override
    public List<NhanVienResponse> findByName(String name) {
        return repo.findByName(name);
    }
}
