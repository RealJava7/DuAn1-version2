package repository;

import java.lang.reflect.Array;
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
import org.hibernate.SessionFactory;
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
            session.close();
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
            khachHangInDB.setTrangThai(khachHangResponse.getTrangThai());

            Transaction transaction = session.beginTransaction();
            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    //update trạng thái thẻ tích điểm
    public static boolean updateTichDiem(KhachHangResponse kh, int trangThai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            TheTichDiem the = session.get(TheTichDiem.class, kh.getIdThe());
            the.setTrangThai(trangThai == 1 ? true : false);
            Transaction transaction = session.beginTransaction();

            session.update(the);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    public static boolean updateKhoiPhuc(KhachHangResponse kh, int trangThai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, kh.getId());
            khachHangInDB.setTrangThai(trangThai);
            updateTichDiem(kh, trangThai);
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
    public static List<KhachHangResponse> getAll(int trangThai) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE kh.trangThai = :trangThai
                                               """);
            query.setParameter("trangThai", trangThai);
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    // 4. get by SDT và trạng thái
    public static List<KhachHangResponse> findBySDT(String sdt, int trangThai) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE kh.trangThai = :trangThai AND kh.sdt LIKE :sdt
                                               """);
            query.setParameter("trangThai", trangThai);
            query.setParameter("sdt", "%" + sdt + "%");
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }
//5. lọc theo tên

    public static List<KhachHangResponse> sortByName(Boolean c, int trangThai) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();
        String sql = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            if (c == true) {
                sql = """
                       SELECT new viewmodel.KhachHangResponse
                                                                    (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                                                    FROM KhachHang kh
                                                                    INNER JOIN kh.theTichDiem ttd  WHERE kh.trangThai = :trangThai Order by kh.hoTen ASC
                      """;

            } else {
                sql = """
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd  WHERE kh.trangThai = :trangThai Order by kh.hoTen DESC
                                               """;
            }
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", trangThai);

            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    //6. getAll thẻ tích điểm
    public List<KhachHangResponse> getAllTheTichDiem() {
        List<KhachHangResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = """
                         SELECT new viewmodel.KhachHangResponse(kh.id,kh.maThe,kh.ngayKichHoat,kh.soDiem,kh.trangThai) FROM TheTichDiem kh
                         """;
            Query query = session.createQuery(hql);
            lists = query.getResultList();
        }
        return lists;
    }
// find by Mã Tích Điểm

    public static List<KhachHangResponse> findByMa(int id) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                         SELECT new viewmodel.KhachHangResponse(kh.id,kh.maThe,kh.ngayKichHoat,kh.soDiem,kh.trangThai) FROM TheTichDiem kh WHERE kh.id = :id
                         """);

            query.setParameter("id", id);
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    public static void main(String[] args) {
        // add

//        TheTichDiem theTichDiem = new TheTichDiem();
//        theTichDiem.setMaThe("1209 0232 2290");
//        theTichDiem.setNgayKichHoat(LocalDate.now());
//        theTichDiem.setSoDiem(0);
//        theTichDiem.setTrangThai(true);
//
//        KhachHang khachHang = new KhachHang();
//        khachHang.setHoTen("Nguyễn Khắc Thịnh");
//        khachHang.setEmail("thinh112@gmail.com");
//        khachHang.setSdt("09782561833");
//        khachHang.setGioiTinh(true);
//        khachHang.setNgaySinh(LocalDate.of(1997, 2, 7));
//        khachHang.setDiaChi("123 Trần Nhân Tông");
//        khachHang.setTheTichDiem(theTichDiem);
//
//        System.out.println(add(khachHang));
//        TheTichDiem theTichDiem = new TheTichDiem();
//        theTichDiem.setMaThe("9082 1109 2376");
//        theTichDiem.setNgayKichHoat(LocalDate.now());
//        theTichDiem.setSoDiem(0);
//        theTichDiem.setTrangThai(true);
//
//        KhachHang khachHang = new KhachHang();
//        khachHang.setHoTen("Nguyễn Đình Hiếu");
//        khachHang.setEmail("hieupham09@gmail.com");
//        khachHang.setSdt("0934129828");
//        khachHang.setGioiTinh(true);
//        khachHang.setNgaySinh(LocalDate.of(1998, 2, 7));
//        khachHang.setDiaChi("2 Trần Nhân Tông");
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
//        List<KhachHangResponse> khachHangResponses = getAll();
//        khachHangResponses.forEach(kh -> System.out.println(kh.toString()));
    }
}
