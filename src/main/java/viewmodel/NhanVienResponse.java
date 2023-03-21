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
public class NhanVienResponse {

    private int id;
    private String hoTen;
    private boolean gioiTinh;
    private String sdt;
    private LocalDate ngaySinh;
    private String diaChi;
    private String email;
    private boolean chucVu;
    private boolean trangThai;
    private String hinhAnh;
    private String taiKhoan;
    private String matKhau;
    
}
