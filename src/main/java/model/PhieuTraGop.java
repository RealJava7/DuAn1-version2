package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PhieuTraGop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuTraGop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieu")
    private int id;

    @Column(name = "ID")
    private String maPhieu;
    
    @Column(name = "TongPhaiTra")
    private long tongPhaiTra;
    
    @Column(name = "LaiSuat")
    private float laiSuat;
    
    @Column(name = "KyHan")
    private int kyHan;
    
    @Column(name = "NgayTao")
    private LocalDate ngayTao;
    
    @Column(name = "NgayDong")
    private int ngayDong;
    
    @Column(name = "PhaiTra")
    private long phaiTra;
    
    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "phieuTraGop")
    private Set<LichSuTraGop> lichSuSet = new HashSet<>();

}
