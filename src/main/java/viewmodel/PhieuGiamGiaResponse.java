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
public class PhieuGiamGiaResponse {
    
    private int id;  // id phieuGiamGia
    private String maPhieu;
    private String tenPhieu;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private int luotSuDung;
    private long dieuKien;
    private float giaTri;
    private int trangThai;

    @Override
    public String toString() {
        return "PhieuGiamGiaResponse{" + "id=" + id + ", maPhieu=" + maPhieu + ", tenPhieu=" + tenPhieu + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", luotSuDung=" + luotSuDung + ", dieuKien=" + dieuKien + ", giaTri=" + giaTri + ", trangThai=" + trangThai + '}';
    }
    public Object[] toDataRow(){
        return new Object[]{
            tenPhieu,maPhieu,giaTri,dieuKien,luotSuDung,trangThai
        };
    }
}
