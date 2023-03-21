package repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import model.KhachHang;
import model.TheTichDiem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.KhachHangResponse;

public class KhachHangRepository {

    // 1. add
    public static boolean add(KhachHang khachHang) {
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
    public static boolean update(KhachHangResponse khachHangResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, khachHangResponse.getId());

            khachHangInDB.setHoTen(khachHangResponse.getHoTen());
            khachHangInDB.setEmail(khachHangResponse.getEmail());
            khachHangInDB.setSdt(khachHangResponse.getSdt());
            khachHangInDB.setGioiTinh(khachHangResponse.isGioiTinh());
            khachHangInDB.setNgaySinh(khachHangResponse.getNgaySinh());
            khachHangInDB.setDiaChi(khachHangResponse.getDiaChi());
            khachHangInDB.setTrangThai(khachHangResponse.isTrangThai());

            Transaction transaction = session.beginTransaction();
            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 3. get all
//    public static List<KhachHangResponse> getAll() {
//        List<KhachHangResponse> khachHangResponses = new ArrayList<>();
//
//        try {
//            Session session = HibernateUtil.getFACTORY().openSession();
//            Query query = session.createQuery("""
//                                              SELECT new viewmodel.KhachHangResponse
//                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
//                                              FROM KhachHang kh
//                                              INNER JOIN kh.theTichDiem ttd
//                                               """);
//
//            khachHangResponses = query.getResultList();
//        } catch (HibernateException ex) {
//            ex.printStackTrace(System.out);
//        }
//        return khachHangResponses;
//    }
    public static List<KhachHang> getAll() {
        List<KhachHang> lists = new ArrayList<>();
    }

    // 4. get by id
    public static void main(String[] args) {
        // add
//        TheTichDiem theTichDiem = new TheTichDiem();
//        theTichDiem.setMaThe("9892 0232 9719");
//        theTichDiem.setNgayKichHoat(LocalDate.now());
//        theTichDiem.setSoDiem(0);
//        theTichDiem.setTrangThai(true);
//
//        KhachHang khachHang = new KhachHang();
//        khachHang.setHoTen("Nguyễn Văn An");
//        khachHang.setEmail("an8123@gmail.com");
//        khachHang.setSdt("093467288");
//        khachHang.setGioiTinh(true);
//        khachHang.setNgaySinh(LocalDate.of(2000, 2, 7));
//        khachHang.setDiaChi("77 Trần Nhân Tông");
//        khachHang.setTheTichDiem(theTichDiem);
//
//        System.out.println(add(khachHang));

        // update
//        KhachHangResponse khachHangResponse = new KhachHangResponse();
//
//        khachHangResponse.setId(2);
//        khachHangResponse.setHoTen("Nguyễn Văn An - 2");
//        khachHangResponse.setEmail("an123@gmail.com");
//        khachHangResponse.setSdt("0934875159");
//        khachHangResponse.setGioiTinh(true);
//        khachHangResponse.setNgaySinh(LocalDate.of(2003, 12, 12));
//        khachHangResponse.setDiaChi("77 Trần Nhân Tông");
//        khachHangResponse.setTrangThai(false);
//
//        System.out.println(update(khachHangResponse));
        // get all
        List<KhachHangResponse> khachHangResponses = getAll();
        khachHangResponses.forEach(kh -> System.out.println(kh.toString()));
    }
}
