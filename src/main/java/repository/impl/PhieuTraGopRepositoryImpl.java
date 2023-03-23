/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.Query;
import model.PhieuTraGop;
import org.hibernate.Session;
import repository.LichSuTraGopRepository;
//import repository.PhieuTraGopRepository;
import utility.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopRepositoryImpl {

    private LichSuTraGopRepository lstgRepository = new LichSuTraGopRepositoryImpl();

    public boolean update(int id, PhieuTraGop phieuTraGop) {
        boolean check = false;
        PhieuTraGop ptg = getByID(id);
        ptg.setMaPhieu(phieuTraGop.getMaPhieu());
        ptg.setKyHan(phieuTraGop.getKyHan());
        ptg.setLaiSuat(phieuTraGop.getLaiSuat());
        ptg.setNgayDong(phieuTraGop.getNgayDong());
        ptg.setPhaiTra(phieuTraGop.getPhaiTra());
        ptg.setTrangThai(phieuTraGop.isTrangThai());
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.update(ptg);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public boolean insert(PhieuTraGop phieuTraGop) {
        phieuTraGop.setMaPhieu(genarateMaPhieu());

        boolean check = false;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.save(phieuTraGop);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public List<PhieuTraGop> getAll() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public PhieuTraGop getByID(int id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop Where id = :id";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            PhieuTraGop ptg = (PhieuTraGop) query.getSingleResult();
            session.getTransaction().commit();

            return ptg;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String genarateMaPhieu() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT COUNT(*) FROM PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            int count = Integer.parseInt(query.getSingleResult().toString());
            session.getTransaction().commit();
            return ("PTG" + ++count);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuTraGop> getByString(String s) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = """
                         FROM PhieuTraGop ptg
                         JOIN ptg.HoaDon hd
                         JOIN hd.KhachHang kh
                         WHERE kh.HoTen LIKE :s or ptg.MaPhieu LIKE :ss
                         """;
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("s", "%" + s + "%");
            query.setParameter("ss", "%" + s + "%");
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuTraGop> getByTime(int index) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuTraGop> getByTrangThai(int index) {
        if (index == 0) {
            return getAll();
        }

        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop WHERE TrangThai = :index";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("index", --index);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    public static void main(String[] args) {
//        PhieuTraGopRepositoryImpl repositoryImpl = new PhieuTraGopRepositoryImpl();
////
////        //tạo Phieu Tra Gop
////        PhieuTraGop ptg = new PhieuTraGop();
////        //tạo lich su tra gop
////        LichSuTraGop lstg = new LichSuTraGop();
////        //set lich sử trả góp
//////        lstg.setId(10);
////        lstg.setGhiChu("Ghi chú");
////        lstg.setMa("LSTG10");
////        lstg.setNgayThanhToan(LocalDate.now());
////        lstg.setPhieuTraGop(ptg);
////        lstg.setTongTien(703703);
////        //set Phiếu trả góp
//////        ptg.setHoaDon(null);
//////        ptg.setId(5);
////        ptg.setKyHan(3);
////        ptg.setLaiSuat(7);
////        ptg.addLichSuTraGop(lstg);
////        ptg.setMaPhieu("PTG10");
////        ptg.setNgayDong(LocalDate.now().getDayOfMonth());
////        ptg.setNgayTao(LocalDate.now());
////        ptg.setPhaiTra(547325);
////        ptg.setTongPhaiTra(2345678);
////
////        System.out.println(ptg.toString());
////        System.out.println(lstg.toString());
//
//        System.out.println(repositoryImpl.getByID(1).toString());
//    }
}
