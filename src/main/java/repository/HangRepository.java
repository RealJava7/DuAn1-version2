package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.Hang;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

public class HangRepository {

    // 1. get all
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

    public static void main(String[] args) {
//        List<Hang> hangList = getAll();
//        hangList.forEach(h -> System.out.println(h.toString()));
    }
}
