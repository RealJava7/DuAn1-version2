package viewmodel;

import java.text.NumberFormat;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.LoaiManHinh;

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
    private long giaNhap;
    private long giaBan;
    private int soLuong;
    private String hinhAnh;

    private String heDieuHanh;
    private String hang;
    private String dongSanPham;
    private String mauSac; // maMauSac

    private int cameraChinh;
    private int cameraPhu;
    private int cameraGocRong;
    private int cameraTele;

    private float kichThuoc;
    private String doPhanGiai;
    private LoaiManHinh loaiManHinh;

    @Override
    public String toString() {
        return "DienThoaiResponse{" + "id=" + id + ", maDT=" + maDT + ", tenDT=" + tenDT + ", moTa=" + moTa + ", dungLuongPin=" + dungLuongPin + ", rom=" + rom + ", ram=" + ram + ", cpu=" + cpu + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", hinhAnh=" + hinhAnh + ", heDieuHanh=" + heDieuHanh + ", hang=" + hang + ", dongSanPham=" + dongSanPham + ", mauSac=" + mauSac + ", cameraChinh=" + cameraChinh + ", cameraPhu=" + cameraPhu + ", cameraGocRong=" + cameraGocRong + ", cameraTele=" + cameraTele + ", kichThuoc=" + kichThuoc + ", doPhanGiai=" + doPhanGiai + ", loaiManHinh=" + loaiManHinh + '}';
    }

    public Object[] toDataRow() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return new Object[]{tenDT, hang, ram + " GB", rom + " GB", dungLuongPin + " mAh", soLuong, nf.format(giaBan) + " VND"};
    }

}
