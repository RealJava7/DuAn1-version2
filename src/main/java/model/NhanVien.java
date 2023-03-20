package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NhanVien")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NhanVien {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "HoTen")
    private String hoTen;
    
    @Column(name = "GioiTinh")
    private boolean gioiTinh;
    
    @Column(name = "Sdt")
    private String sdt;
    
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;
    
    @Column(name = "DiaChi")
    private String diaChi;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "ChucVu")
    private boolean chucVu; // 0-Nhan vien, 1-Quan ly
    
    @Column(name = "TrangThai")
    private boolean trangThai;
    
    @Column(name = "HinhAnh")
    private String hinhAnh;
    
    @OneToOne
    @JoinColumn(name = "IdTaiKhoan")
    private TaiKhoan taiKhoan;
    
}
