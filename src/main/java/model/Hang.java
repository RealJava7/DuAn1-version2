package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HangDienThoai")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Ten")
    private String tenHang;
    
    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "hangDienThoai")
    private List<DongSanPham> dongSanPhams = new ArrayList<>();

    public Hang(String tenHang) {
        this.tenHang = tenHang;
    }

    public Hang(int id, String tenHang) {
        this.id = id;
        this.tenHang = tenHang;
    }

    @Override
    public String toString() {
        return this.tenHang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.tenHang);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hang other = (Hang) obj;
        return Objects.equals(this.tenHang, other.tenHang);
    }

}
