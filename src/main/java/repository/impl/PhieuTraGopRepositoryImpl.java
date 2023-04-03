/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.time.LocalDate;
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

    public List<PhieuTraGop> getByTimeAndTrangThai(int time, int trangThai) {
        LocalDate date = LocalDate.now();
        String hql = "";
        switch (time) {
            case 0:
                //tất cả
                hql = "From PhieuTraGop";
                break;
            case 1:
                //Hôm nay
                hql = "FROM PhieuTraGop Where NgayTao = '" + date + "'";
                break;
            case 2:
                //Hôm qua

                hql = "FROM PhieuTraGop Where NgayTao = '" + date.minusDays(1) + "'";
                break;
            case 3:
//                //Tuần này
//                System.out.println(date);
//                System.out.println(date.minusDays(7));
                hql = "FROM PhieuTraGop\n"
                        + "WHERE NgayTao BETWEEN '" + date.minusDays(7) + "' and '" + date + "'";
                break;
            case 4:
                //tháng này
                System.out.println(date.getMonthValue());
                hql = "FROM PhieuTraGop Where MONTH(NgayTao) = '" + date.getMonthValue() + "'";
                break;
            case 5:
                //năm này
                hql = "FROM PhieuTraGop Where Year(NgayTao) = " + date.getYear();
                break;
            default:
                hql = "From PhieuTraGop";
        }
        if (time != 0 && trangThai == 0) {
            hql = hql;
        }
        if (time != 0 && trangThai == 1) {
            hql = hql + "AND TrangThai = 0";
        }
        if (time != 0 && trangThai == 2) {
            hql = hql + "AND TrangThai = 1";
        }

        try (Session session = HibernateUtil.getFACTORY().openSession()) {
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

//    public static void main(String[] args) {
//        PhieuTraGopRepositoryImpl repositoryImpl = new PhieuTraGopRepositoryImpl();
//
//        List<PhieuTraGop> listTime = repositoryImpl.getByTimeAndTrangThai(3, 2);
//        for (PhieuTraGop phieuTraGop : listTime) {
//            System.out.println(phieuTraGop.toString());
//        }
//    }
}
