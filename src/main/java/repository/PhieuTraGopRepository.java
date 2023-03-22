package repository;

import java.util.List;
import model.PhieuTraGop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class PhieuTraGopRepository {
    
    public static boolean add(PhieuTraGop phieuTraGop) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(phieuTraGop);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    public static void main(String[] args) {
        PhieuTraGop phieuTraGop = new PhieuTraGop();
        
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
        phieuTraGop.setMaPhieu("PTG0001");
    }

//    public boolean delete(String id);
//
//    boolean update(String id, PhieuTraGop phieuTraGop);
//
//    boolean insert(PhieuTraGop phieuTraGop);
//
//    List<PhieuTraGop> getAll();
//
//    List<PhieuTraGop> getByString(String s);
//
//    List<PhieuTraGop> getByTime(int index);
//
//    List<PhieuTraGop> getByTrangThai(int index);
}
