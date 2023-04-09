package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import model.ChiTietPhieuBaoHanh;
import model.LoaiBaoHanh;
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
    public static List<PhieuBaoHanhResponse> getAll() {
        List<PhieuBaoHanhResponse> phieuBaoHanhResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuBaoHanhResponse
                                              (pbh.id,
                                              ct.imei, kh.hoTen,kh.sdt,dt.tenDT ,ct.thoiHanBaoHanh
                                              , ct.ngayMuaHang
                                              , ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
                                              INNER JOIN pbh.chiTietPhieuBaoHanh ct
                                              INNER JOIN pbh.hoaDonChiTiet.hoaDon.khachHang kh
                                              INNER JOIN pbh.hoaDonChiTiet.imei.dienThoai dt
                                              """);
            phieuBaoHanhResponses = query.getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuBaoHanhResponses;
    }
    public static void main(String[] args) {
        List<PhieuBaoHanhResponse> list = getAll();
        System.out.println(list.toString());
    }
    // 3. update
    //4. lọc
    public List<PhieuBaoHanhResponse> getList(boolean status) {
        List<PhieuBaoHanhResponse> phieuBaoHanhResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuBaoHanhResponse
                                              (pbh.id,
                                              ct.imei, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
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
                                              (pbh.id,
                                              ct.imei, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                              FROM PhieuBaoHanh pbh
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
    
    public static PhieuBaoHanhResponse getPBHByID(int id) {
        PhieuBaoHanhResponse pbh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                       SELECT new viewmodel.PhieuBaoHanhResponse
                                       (pbh.id,
                                       ct.imei, ct.thoiHanBaoHanh, ct.ngayMuaHang, ct.ngayHetHan, ct.moTa, ct.trangThai)
                                       FROM PhieuBaoHanh pbh
                                       INNER JOIN pbh.chiTietPhieuBaoHanh ct
                                       WHERE pbh.id = :id
                                              """);
            query.setParameter("id", id);
            pbh = (PhieuBaoHanhResponse) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return pbh;
        }
    }
    
    public static boolean updateMotaPBH(PhieuBaoHanhResponse pbh, int id) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            ChiTietPhieuBaoHanh newCTPBH = session.get(ChiTietPhieuBaoHanh.class, id);
            newCTPBH.setMoTa(pbh.getMoTa());
            session.update(newCTPBH);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public static PhieuBaoHanh getById(int id) {
        PhieuBaoHanh phieuBaoHanh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            phieuBaoHanh = session.get(PhieuBaoHanh.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return phieuBaoHanh;
    }
    
}
