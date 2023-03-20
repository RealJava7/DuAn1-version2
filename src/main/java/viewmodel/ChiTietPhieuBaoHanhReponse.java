/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author virus
 */
public class ChiTietPhieuBaoHanhReponse {

    private int id;
    private String tenKH;
    private String tenDT;
    private String Imei;
    private long giaSanPham;
    private int thoiHanBaoHanh;
    private Date ngayMuaHang;
    private boolean trangThai;

    public ChiTietPhieuBaoHanhReponse() {
    }

    public ChiTietPhieuBaoHanhReponse(String tenKH, String tenDT, String Imei, long giaSanPham, int thoiHanBaoHanh, Date ngayMuaHang, boolean trangThai) {
        this.tenKH = tenKH;
        this.tenDT = tenDT;
        this.Imei = Imei;
        this.giaSanPham = giaSanPham;
        this.thoiHanBaoHanh = thoiHanBaoHanh;
        this.ngayMuaHang = ngayMuaHang;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getImei() {
        return Imei;
    }

    public void setImei(String Imei) {
        this.Imei = Imei;
    }

    public long getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(long giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getThoiHanBaoHanh() {
        return thoiHanBaoHanh;
    }

    public void setThoiHanBaoHanh(int thoiHanBaoHanh) {
        this.thoiHanBaoHanh = thoiHanBaoHanh;
    }

    public Date getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(Date ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
