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
public class PhieuBaoHanhResponse {

    private int id;
    private String imei;
    private String tenKH;
    private String sdtKH;
    private String tenSP;
    private int thoiHanBaoHanh;
    private LocalDate ngayMuaHang;
    private LocalDate ngayHetHan;
    private String moTa;
    private boolean trangThai;

    public Object[] toRowData() {
        return new Object[]{this.id, this.imei, this.thoiHanBaoHanh,
            this.ngayMuaHang, this.ngayHetHan, this.moTa, getStatus(this.ngayHetHan)};
    }

    public String getStatus(LocalDate ngayHetHan) {
        LocalDate now = LocalDate.now();
        return ngayHetHan.compareTo(now) <= 0 ? "Hết Hạn" : "Còn Hạn";
    }

    public LocalDate getDate(LocalDate ngayMuaHang, int thoiHan) {
        return ngayMuaHang.plusMonths(thoiHan);
    }
}
