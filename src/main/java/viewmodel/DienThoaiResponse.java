package viewmodel;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DienThoaiResponse {

    private int id;
    private String maDT;
    private String tenDT;
    private String moTa;
    private int dungLuongPin;
    private int rom;
    private int ram;
    private String cpu;
    private String heDieuHanh;
    private long giaNhap;
    private long giaBan;
    private int soLuong;
    private String hinhAnh;
    private String hang;
    private String dongSanPham;
    private String mauSac;

    private int cameraChinh;
    private int cameraPhu;
    private int cameraGocRong;
    private int cameraTele;

    private float kichThuoc;
    private String doPhanGiai;
    private String loaiManHinh;

}
