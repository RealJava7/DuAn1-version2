package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.LoaiBaoHanh;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.LoaiBaoHanhResponse;

public class LoaiBaoHanhRepository {

    // 1. add
    public boolean add(LoaiBaoHanh loaiBaoHanh) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(loaiBaoHanh);
            transaction.commit();
            session.close();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. get all
    public List<LoaiBaoHanhResponse> getAll() {
        List<LoaiBaoHanhResponse> loaiBaoHanhResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.LoaiBaoHanhResponse
                                              (lbh.id, lbh.ten, lbh.dieuKien)
                                              FROM LoaiBaoHanh lbh
                                               """);
            loaiBaoHanhResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return loaiBaoHanhResponses;
    }

    // 3. update
    public boolean update(LoaiBaoHanhResponse loaiBaoHanhResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            LoaiBaoHanh loaiBaoHanh = session.get(LoaiBaoHanh.class, loaiBaoHanhResponse.getId());
            loaiBaoHanh.setTen(loaiBaoHanhResponse.getTen());
            loaiBaoHanh.setDieuKien(loaiBaoHanhResponse.getDieuKien());

            session.update(loaiBaoHanh);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 4. get by id
    public LoaiBaoHanh getById(int id) {
        LoaiBaoHanh loaiBaoHanh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            loaiBaoHanh = session.get(LoaiBaoHanh.class, id);
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return loaiBaoHanh;
    }

    public static void main(String[] args) {
//        LoaiBaoHanh loaiBaoHanh = getById(1);
//        System.out.println(loaiBaoHanh.toString());

//        List<LoaiBaoHanhResponse> loaiBaoHanhResponses = getAll();
//        loaiBaoHanhResponses.forEach(l -> System.out.println(l.toString()));
//        LoaiBaoHanh lbh = new LoaiBaoHanh();
//
//        lbh.setTen("Màn hình");
//        lbh.setDieuKien("Lỗi do nhà sản xuất");
//        System.out.println(add(lbh));
    }
}
