/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author virus
 */
public class ViewModelPhieuBaoHanh {

    private int id;
    private int idChiTietPhieuBaoHanh;
    private int idLoaiBaoHanh;
    private int idHoaDon;
    private String emei;
    private int thoiHanBaoHanh;
    private boolean trangThai;

    public ViewModelPhieuBaoHanh() {
    }

    public ViewModelPhieuBaoHanh(int id, int idChiTietPhieuBaoHanh, int idLoaiBaoHanh, int idHoaDon, String emei, int thoiHanBaoHanh, boolean trangThai) {
        this.id = id;
        this.idChiTietPhieuBaoHanh = idChiTietPhieuBaoHanh;
        this.idLoaiBaoHanh = idLoaiBaoHanh;
        this.idHoaDon = idHoaDon;
        this.emei = emei;
        this.thoiHanBaoHanh = thoiHanBaoHanh;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChiTietPhieuBaoHanh() {
        return idChiTietPhieuBaoHanh;
    }

    public void setIdChiTietPhieuBaoHanh(int idChiTietPhieuBaoHanh) {
        this.idChiTietPhieuBaoHanh = idChiTietPhieuBaoHanh;
    }

    public int getIdLoaiBaoHanh() {
        return idLoaiBaoHanh;
    }

    public void setIdLoaiBaoHanh(int idLoaiBaoHanh) {
        this.idLoaiBaoHanh = idLoaiBaoHanh;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
    }

    public int getThoiHanBaoHanh() {
        return thoiHanBaoHanh;
    }

    public void setThoiHanBaoHanh(int thoiHanBaoHanh) {
        this.thoiHanBaoHanh = thoiHanBaoHanh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
