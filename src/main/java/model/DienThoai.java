package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.HeDieuHanh;

@Entity
@Table(name = "DienThoai")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DienThoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaDT")
    private String maDT;

    @Column(name = "TenDT")
    private String tenDT;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "DungLuongPin")
    private int dungLuongPin;

    @Column(name = "Rom")
    private int rom;

    @Column(name = "Ram")
    private int ram;

    @Column(name = "Cpu")
    private String cpu;

    @Column(name = "HeDieuHanh")
    @Enumerated(EnumType.STRING)
    private HeDieuHanh heDieuHanh;

    @Column(name = "GiaNhap")
    private long giaNhap;

    @Column(name = "GiaBan")
    private long giaBan;

    @Column(name = "soLuongTonKho")
    private int soLuong;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @ManyToOne
    @JoinColumn(name = "IdHang")
    private Hang hang;

    @ManyToOne
    @JoinColumn(name = "IdDongSanPham")
    private DongSanPham dongSanPham;

    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdCameraChiTiet")
    private CameraChiTiet cameraChiTiet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdManHinhChiTiet")
    private ManHinhChiTiet manHinhChiTiet;

    @OneToMany(mappedBy = "dienThoai", cascade = CascadeType.ALL)
    private Set<Imei> imeis = new HashSet<>();
    
    private void addImei(Imei imei) {
        if(imei != null) {
            this.imeis.add(imei);
        }
        imei.setDienThoai(this);
    }
}
