package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.Hang;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;
import viewmodel.KhachHangResponse;

public class HangRepository {
    
    // 1. get all
    private static List<Hang> getAll() {
        List<Hang> hangList = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.Hang
                                              (h.id, h.maHang, h.tenHang)
                                              FROM Hang h
                                               """);

            hangList = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hangList;
    }
}
