package repository;

import model.KhachHang;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class KhachHangRepository {

    // 1. add
    public boolean add(KhachHang khachHang) {
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
    public boolean update(KhachHang phieu, int id) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, id);

            Transaction transaction = session.beginTransaction();
            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
}
