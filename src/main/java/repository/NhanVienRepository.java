package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.TaiKhoan;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import utility.HibernateUtil;
import viewmodel.NhanVienResponse;

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

    // 2. update
    public static boolean update(NhanVienResponse nhanVienResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            NhanVien nhanVien = session.get(NhanVien.class, nhanVienResponse.getId());

            nhanVien.setHoTen(nhanVienResponse.getHoTen());
            nhanVien.setGioiTinh(nhanVienResponse.isGioiTinh());
            nhanVien.setSdt(nhanVienResponse.getSdt());
            nhanVien.setNgaySinh(nhanVienResponse.getNgaySinh());
            nhanVien.setDiaChi(nhanVienResponse.getDiaChi());
            nhanVien.setEmail(nhanVienResponse.getEmail());
            nhanVien.setChucVu(nhanVienResponse.isChucVu());
            nhanVien.setTrangThai(nhanVienResponse.isTrangThai());
            nhanVien.setHinhAnh(nhanVienResponse.getHinhAnh());

            TaiKhoan taiKhoan = nhanVien.getTaiKhoan();
            taiKhoan.setTaiKhoan(nhanVienResponse.getTaiKhoan());
            taiKhoan.setMatKhau(nhanVienResponse.getMatKhau());
            nhanVien.setTaiKhoan(taiKhoan);

            session.update(nhanVien);
            transaction.commit();
            check = true;
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 3. get all
    public static List<NhanVienResponse> getAll() {
        List<NhanVienResponse> nhanVienResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.NhanVienResponse
                                              (nv.id, nv.hoTen, nv.gioiTinh, nv.sdt, nv.ngaySinh, nv.diaChi, nv.email, nv.chucVu, nv.trangThai, nv.hinhAnh, tk.taiKhoan, tk.matKhau)
                                              FROM NhanVien nv
                                              INNER JOIN nv.taiKhoan tk
                                               """);

            nhanVienResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return nhanVienResponses;
    }

    // Test
    public static void main(String[] args) {
        // getAll
//        List<NhanVienResponse> nhanVienResponses = getAll();
//        nhanVienResponses.forEach(nv -> System.out.println(nv.toString()));

        // update
        NhanVienResponse nhanVienResponse = new NhanVienResponse();

        nhanVienResponse.setId(1);
        nhanVienResponse.setHoTen("Nguyễn Khắc Thịnh");
        nhanVienResponse.setGioiTinh(true);
        nhanVienResponse.setSdt("0961271232");
        nhanVienResponse.setNgaySinh(LocalDate.of(2004, 7, 22));
        nhanVienResponse.setDiaChi("365 Nguyễn Chí Thanh");
        nhanVienResponse.setEmail("thinh123@gmail.com");
        nhanVienResponse.setChucVu(false);
        nhanVienResponse.setTrangThai(true);
        nhanVienResponse.setHinhAnh("xyz1.png");

        nhanVienResponse.setTaiKhoan("thingnguyen1234");
        nhanVienResponse.setMatKhau("231");

        System.out.println(update(nhanVienResponse));

        // add
//        NhanVien nhanVien = new NhanVien();
//        nhanVien.setHoTen("Nguyễn Thu Thảo");
//        nhanVien.setGioiTinh(true);
//        nhanVien.setSdt("09120182312");
//        nhanVien.setNgaySinh(LocalDate.of(2003, 2, 22));
//        nhanVien.setDiaChi("466 abc");
//        nhanVien.setEmail("thao123@gmail.com");
//        nhanVien.setChucVu(true);
//        nhanVien.setTrangThai(false);
//        nhanVien.setHinhAnh("");
//
//        TaiKhoan taiKhoan = new TaiKhoan();
//        taiKhoan.setTaiKhoan("thao543");
//        taiKhoan.setMatKhau("123");
//
//        nhanVien.setTaiKhoan(taiKhoan);
//
//        System.out.println(add(nhanVien));
    }
}
