/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.NhanVien;
import viewmodel.NhanVienResponse;

/**
 *
 * @author Ma
 */
public interface QuanLyNhanVienService {

    String add(NhanVien nhanVien);

    String update(NhanVienResponse nhanVienResponse);

    String delete(int id);

    List<NhanVienResponse> getAllLam();

    List<NhanVienResponse> getAllNghi();

    List<NhanVienResponse> findByName(String name);
    
    List<NhanVienResponse> findByGioiTinh(boolean gt);
    
    List<NhanVienResponse> findByChucVu(boolean cv);
}
