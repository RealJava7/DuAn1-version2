/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.NhanVien;
import repository.NhanVienRepository;
import viewmodel.NhanVienResponse;

/**
 *
 * @author Ma
 */
public class QuanLyNhanVienService {
    NhanVienRepository repo = new NhanVienRepository();
    public String add(NhanVien nhanVien) {
        if(repo.add(nhanVien) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String update(NhanVienResponse nhanVienResponse) {
        if(repo.update(nhanVienResponse) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }
    
    public String delete(int id) {
        if(repo.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }
    
    public List<NhanVienResponse> getAllLam() {
        return repo.getAllLam();
    }
    
    public List<NhanVienResponse> getAllNghi() {
        return repo.getAllNghi();
    }
    
    public List<NhanVienResponse> findByName(String name) {
        return repo.findByName(name);
    }
}
