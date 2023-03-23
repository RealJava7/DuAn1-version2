package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Hang;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.HangResponse;

public class HangRepository {

    // 1. get all entity
    public List<Hang> getAllEntity() {
        List<Hang> hangs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.Hang
                                              (h.id, h.tenHang)
                                              FROM Hang h
                                               """);

            hangs = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hangs;
    }

    // 2. get all response
    public List<HangResponse> getAllResponse() {
        List<HangResponse> hangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.HangResponse
                                              (h.id, h.tenHang, h.trangThai)
                                              FROM Hang h
                                               """);

            hangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hangResponses;
    }

    // 3. get by id
    public Hang getById(int id) {
        Hang hang = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            hang = session.get(Hang.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return hang;
    }

    // 4. add
    public boolean add(Hang hang) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(hang);
            transaction.commit();
            check = true;
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    // 5. update
    public static boolean update(HangResponse hangResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            Hang hang = session.get(Hang.class, hangResponse.getId());
            hang.setTenHang(hangResponse.getTenHang());

            session.update(hang);
            transaction.commit();
            
            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 6. get by tenHang
    public static Hang getByTenHang(String tenHang) {
        Hang hang = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT h
                                              FROM Hang h
                                              WHERE h.tenHang = :tenHang
                                               """);
            query.setParameter("tenHang", tenHang);
            hang = (Hang) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            hang = null;
        }
        return hang;
    }

    public static void main(String[] args) {
//        HangResponse hangResponse = new HangResponse();
//        hangResponse.setId(1);
//        hangResponse.setTenHang("Applee");
//        System.out.println(update(hangResponse));
        
//        List<HangResponse> hangResponses = getAllResponse();
//        hangResponses.forEach(h -> System.out.println(h.toString()));

//        List<Hang> hangs = getAllEntity();
//        hangs.forEach(h -> System.out.println(h.toString()));
//        Hang apple = getByTenHang("Apple1");
//        System.out.println(apple == null);
//        System.out.println(apple.getId());
//        System.out.println(apple.getTenHang());

//        Hang hang = new Hang();
//        hang.setTenHang("Nokia");
//        System.out.println(add(hang));
//        List<Hang> hangList = getAll();
//        hangList.forEach(h -> System.out.println(h.toString()));
//        Hang hang = getById(1);
//        System.out.println(hang.getTenHang());
    }
}
