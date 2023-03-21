package repository;

import java.time.LocalDate;
import java.time.Month;
import model.KhachHang;
import model.TheTichDiem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class KhachHangRepository {

    // 1. add
    public boolean add(KhachHang khachHang) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(khachHang);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. update
    public boolean update(KhachHang phieu, int id) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, id);

            Transaction transaction = session.beginTransaction();
            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    public static void main(String[] args) {
        // add
        TheTichDiem theTichDiem = new TheTichDiem();
        theTichDiem.setMaThe("TTD001");
        theTichDiem.setNgayKichHoat(LocalDate.now());
        theTichDiem.setSoDiem(0);
        theTichDiem.setTrangThai(true);
        
        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen("Phạm Văn Bình");
        khachHang.setEmail("binh78@gmail.com");
        khachHang.setSdt("09345677212");
        khachHang.setGioiTinh(true);
        khachHang.setNgaySinh(LocalDate.of(2003, 2, 7));
        khachHang.setDiaChi("Thai Binh");
        khachHang.setTheTichDiem(theTichDiem);
    }
}
