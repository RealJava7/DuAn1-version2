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
public class ViewModelTTCTPhieuBaoHanh {

    private int id;
    private int idLoaiBaoHanh;
    private int idHD;
    private String tenKH;
    private String tenDT;
    private String emei;
    private long giaSanPham;
    private int thoiHanBaoHanh;

    public ViewModelTTCTPhieuBaoHanh() {
    }

    public ViewModelTTCTPhieuBaoHanh(int id, int idLoaiBaoHanh, int idHD, String tenKH, String tenDT, String emei, long giaSanPham, int thoiHanBaoHanh) {
        this.id = id;
        this.idLoaiBaoHanh = idLoaiBaoHanh;
        this.idHD = idHD;
        this.tenKH = tenKH;
        this.tenDT = tenDT;
        this.emei = emei;
        this.giaSanPham = giaSanPham;
        this.thoiHanBaoHanh = thoiHanBaoHanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoaiBaoHanh() {
        return idLoaiBaoHanh;
    }

    public void setIdLoaiBaoHanh(int idLoaiBaoHanh) {
        this.idLoaiBaoHanh = idLoaiBaoHanh;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
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

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
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

}
