package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.PhieuBaoHanh;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.PhieuBaoHanhResponse;

public class PhieuBaoHanhRepository {

    // 1. add
    public boolean add(PhieuBaoHanh phieuBaoHanh) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(phieuBaoHanh);
            transaction.commit();
            session.close();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return check;
    }

    // 2. get all
    public List<PhieuBaoHanhResponse> getAll() {
        List<PhieuBaoHanhResponse> phieuBaoHanhResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuBaoHanhResponse
                                              (pbh.id, lbh.ten, lbh.dieuKien,
                                              ct.tenKhachHang, ct.tenDienThoai, ct.imei, ct.giaSanPham, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
                                              INNER JOIN pbh.loaiBaoHanh lbh
                                              INNER JOIN pbh.chiTietPhieuBaoHanh ct
                                              """);
            phieuBaoHanhResponses = query.getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuBaoHanhResponses;
    }

    // 3. update
    //4. lọc
    public List<PhieuBaoHanhResponse> getList(boolean status) {
        List<PhieuBaoHanhResponse> phieuBaoHanhResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuBaoHanhResponse
                                              (pbh.id, lbh.ten, lbh.dieuKien,
                                              ct.tenKhachHang, ct.tenDienThoai, ct.imei, ct.giaSanPham, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
                                              INNER JOIN pbh.loaiBaoHanh lbh
                                              INNER JOIN pbh.chiTietPhieuBaoHanh ct
                                              WHERE ct.trangThai = :status
                                              """);
            query.setParameter("status", status);
            phieuBaoHanhResponses = query.getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuBaoHanhResponses;
    }

    //5. tìm kiếm
    public List<PhieuBaoHanhResponse> getListSearch(String tenKH) {
        List<PhieuBaoHanhResponse> phieuBaoHanhResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuBaoHanhResponse
                                              (pbh.id, lbh.ten, lbh.dieuKien,
                                              ct.tenKhachHang, ct.tenDienThoai, ct.imei, ct.giaSanPham, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
                                              INNER JOIN pbh.loaiBaoHanh lbh
                                              INNER JOIN pbh.chiTietPhieuBaoHanh ct
                                              WHERE ct.tenKhachHang LIKE :ten
                                              """);
            query.setParameter("ten", "%" + tenKH + "%");
            phieuBaoHanhResponses = query.getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuBaoHanhResponses;
    }

    public boolean updatePhieuBH(PhieuBaoHanhResponse pbh, int id) {
        boolean check;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check = false;
    }

}
