package repository;

import model.DienThoai;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class DienThoaiRepository {

    // 1. add
    public static boolean add(DienThoai dienThoai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(dienThoai);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    // update
//    public static boolean update() {
//        
//    }
}
