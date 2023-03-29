package viewmodel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonResponse {

    private int id;
    private LocalDate ngayTao;
    private LocalDate ngayThanhToan;
    private Long tongTien;
    private String maPhieuGiamGia;
    private Long tienGiam;
    private Boolean hinhThucThanhToan;
    private String ghiChu;

    private String tenNhanVien;
    private String tenKhachHang;
    private Boolean trangThai;

    @Override
    public String toString() {
        return "HoaDonResponse{" + "id=" + id + ", ngayTao=" + ngayTao + ", ngayThanhToan=" + ngayThanhToan + ", tongTien=" + tongTien + ", maPhieuGiamGia=" + maPhieuGiamGia + ", tienGiam=" + tienGiam + ", hinhThucThanhToan=" + hinhThucThanhToan + ", ghiChu=" + ghiChu + ", tenNhanVien=" + tenNhanVien + ", tenKhachHang=" + tenKhachHang + '}';
    }

}
