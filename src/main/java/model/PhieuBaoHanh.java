package model;

import javax.persistence.CascadeType;
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
@Table(name = "PhieuBaoHanh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuBaoHanh {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @OneToOne
    @JoinColumn(name = "IdHoaDonCT")
    private HoaDonChiTiet hoaDonChiTiet;
    
    @OneToOne
    @JoinColumn(name = "IdLoaiBH")
    private LoaiBaoHanh loaiBaoHanh;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdChiTietPhieuBH")
    private ChiTietPhieuBaoHanh chiTietPhieuBaoHanh;
}
