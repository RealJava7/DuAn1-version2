package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.HeDieuHanh;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.HeDieuHanhResponse;

public class HeDieuHanhRepository {

    // 1. get all by trangThai
    public List<HeDieuHanh> getAllEntityByStatus(boolean status) {
        List<HeDieuHanh> heDieuHanhs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.HeDieuHanh
                                              (h.id, h.ten, h.trangThai)
                                              FROM HeDieuHanh h
                                              WHERE h.trangThai = :status
                                               """);
            query.setParameter("status", status);
            heDieuHanhs = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return heDieuHanhs;
    }

    // 2. get by id
    public static HeDieuHanh getById(int id) {
        HeDieuHanh HeDieuHanh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            HeDieuHanh = session.get(HeDieuHanh.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return HeDieuHanh;
    }

    // 3. add
    public boolean add(HeDieuHanh heDieuHanh) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(heDieuHanh);
            transaction.commit();
            check = true;
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 5. get HDH by tenHDH
    public static HeDieuHanh getByTen(String tenHDH) {
        HeDieuHanh hdh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT h
                                              FROM HeDieuHanh h
                                              WHERE h.ten = :tenHDH
                                               """);
            query.setParameter("tenHDH", tenHDH);
            hdh = (HeDieuHanh) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            hdh = null;
        }
        return hdh;
    }

    // 6. get all response
    public List<HeDieuHanhResponse> getAllResponse(boolean status) {
        List<HeDieuHanhResponse> hdhResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.HeDieuHanhResponse
                                              (h.id, h.ten, h.trangThai)
                                              FROM HeDieuHanh h
                                              WHERE h.trangThai = :status
                                               """);
            query.setParameter("status", status);
            hdhResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hdhResponses;
    }

    // 7. update
    public boolean update(HeDieuHanhResponse hdhResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            HeDieuHanh hdh = session.get(HeDieuHanh.class, hdhResponse.getId());
            hdh.setTen(hdhResponse.getTenHDH());

            session.update(hdh);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 8. change status
    public void changeStatus(HeDieuHanhResponse hdhResponse, boolean newStatus) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            HeDieuHanh mauSac = session.get(HeDieuHanh.class, hdhResponse.getId());
            mauSac.setTrangThai(newStatus);

            session.update(mauSac);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        // change status
//        HeDieuHanhResponse hdhResponse = new HeDieuHanhResponse();
//        hdhResponse.setId(5);
//        changeStatus(hdhResponse, false);
        
//        List<HeDieuHanh> heDieuHanhs = getAllEntity();
//        heDieuHanhs.forEach(h -> System.out.println(h.toString()));

//        HeDieuHanh hdh1 = getById(1);
//        System.out.println(hdh1.getTen());
        // add
//        HeDieuHanh hdh = new HeDieuHanh();
//        hdh.setTen("He dieu hanh 1");
//        hdh.setTrangThai(true);
//        System.out.println(add(hdh));
        // update
//        HeDieuHanhResponse hdhResponse = new HeDieuHanhResponse();
//        hdhResponse.setId(5);
//        hdhResponse.setTenHDH("He dieu hanh b");
//        
//        System.out.println(update(hdhResponse));
    }
}
