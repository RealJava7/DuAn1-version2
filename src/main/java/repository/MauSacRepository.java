package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.Hang;
import model.MauSac;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

public class MauSacRepository {
    
    // 1. get all
    public List<MauSac> getAllEntity() {
        List<MauSac> mauSacs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.MauSac
                                              (ms.id, ms.maMauSac, ms.tenMauSac)
                                              FROM MauSac ms
                                               """);

            mauSacs = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return mauSacs;
    }
}
