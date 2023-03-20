/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author virus
 */
public class PhieuBaoHanhReponse {

    private int id;
    private int idHoaDonCT;
    private int idChiTietPhieuBH;

    public PhieuBaoHanhReponse() {
    }

    public PhieuBaoHanhReponse(int idHoaDonCT, int idChiTietPhieuBH) {
        this.idHoaDonCT = idHoaDonCT;
        this.idChiTietPhieuBH = idChiTietPhieuBH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDonCT() {
        return idHoaDonCT;
    }

    public void setIdHoaDonCT(int idHoaDonCT) {
        this.idHoaDonCT = idHoaDonCT;
    }

    public int getIdChiTietPhieuBH() {
        return idChiTietPhieuBH;
    }

    public void setIdChiTietPhieuBH(int idChiTietPhieuBH) {
        this.idChiTietPhieuBH = idChiTietPhieuBH;
    }
    //test
}
