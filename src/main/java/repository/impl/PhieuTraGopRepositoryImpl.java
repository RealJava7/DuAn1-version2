/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Query;
import model.LichSuTraGop;
import model.PhieuTraGop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import repository.PhieuTraGopRepository;
import utility.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopRepositoryImpl {

    public boolean update(String id, PhieuTraGop phieuTraGop) {
        return false;
    }

    public boolean insert(PhieuTraGop phieuTraGop) {

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

    public List<PhieuTraGop> getByString(String s) {
        return null;
    }

    public List<PhieuTraGop> getByTime(int index) {
        return null;
    }

    public List<PhieuTraGop> getByTrangThai(int index) {
        return null;
    }

    public static void main(String[] args) {
        PhieuTraGopRepositoryImpl repositoryImpl = new PhieuTraGopRepositoryImpl();

        //tạo Phieu Tra Gop
        PhieuTraGop ptg = new PhieuTraGop();
        //tạo lich su tra gop
        LichSuTraGop lstg = new LichSuTraGop();
        //set lich sử trả góp
//        lstg.setId(10);
        lstg.setGhiChu("Ghi chú");
        lstg.setMa("LSTG10");
        lstg.setNgayThanhToan(LocalDate.now());
        lstg.setPhieuTraGop(ptg);
        lstg.setTongTien(703703);
        //set Phiếu trả góp
//        ptg.setHoaDon(null);
//        ptg.setId(5);
        ptg.setKyHan(3);
        ptg.setLaiSuat(7);
        ptg.addLichSuTraGop(lstg);
        ptg.setMaPhieu("PTG10");
        ptg.setNgayDong(LocalDate.now().getDayOfMonth());
        ptg.setNgayTao(LocalDate.now());
        ptg.setPhaiTra(547325);
        ptg.setTongPhaiTra(2345678);

        System.out.println(ptg.toString());
        System.out.println(lstg.toString());

        System.out.println(repositoryImpl.insert(ptg));

    }
}
