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
    private int idHDCT;
    private int idCTPBH;

    public PhieuBaoHanhReponse() {
    }

    public PhieuBaoHanhReponse(int idHDCT, int idCTPBH) {
        this.idHDCT = idHDCT;
        this.idCTPBH = idCTPBH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(int idHDCT) {
        this.idHDCT = idHDCT;
    }

    public int getIdCTPBH() {
        return idCTPBH;
    }

    public void setIdCTPBH(int idCTPBH) {
        this.idCTPBH = idCTPBH;
    }

    public Object[] toRowData() {
        return new Object[]{this.id, this.idHDCT, this.idCTPBH};
    }
}
