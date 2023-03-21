package repository;

import java.time.LocalDate;
import model.NhanVien;
import model.TaiKhoan;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class NhanVienRepository {
    
    // 1. add
    public static boolean add(NhanVien nhanVien) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(nhanVien);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    public static void main(String[] args) {
        NhanVien nhanVien = new NhanVien();
        
        nhanVien.setHoTen("Nguyễn Khắc Thịnh");
        nhanVien.setGioiTinh(true);
        nhanVien.setSdt("091232829112");
        nhanVien.setNgaySinh(LocalDate.now());
        nhanVien.setDiaChi("262 Phạm Văn Đồng");
        nhanVien.setEmail("thinh12@gmail.com");
        nhanVien.setChucVu(true);
        nhanVien.setTrangThai(false);
        nhanVien.setHinhAnh("");
        
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTaiKhoan("thingnguyen123");
        taiKhoan.setMatKhau("12312092");
        
        nhanVien.setTaiKhoan(taiKhoan);
        
        System.out.println(add(nhanVien));
    }
}
