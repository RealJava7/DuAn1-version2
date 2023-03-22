package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.DongSanPham;
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

    // 1. get by id
    public static MauSac getById(int id) {
        MauSac mauSac = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            mauSac = session.get(MauSac.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return mauSac;
    }

    public static void main(String[] args) {
//        List<MauSac> mauSacs = getAllEntity();
//        mauSacs.forEach(ms -> System.out.println(ms.toString()));

        MauSac ms1 = getById(1);
        System.out.println(ms1.getTenMauSac());
    }
}
