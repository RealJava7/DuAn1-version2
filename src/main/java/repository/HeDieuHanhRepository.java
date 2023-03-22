package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.DongSanPham;
import model.HeDieuHanh;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

public class HeDieuHanhRepository {
    
    // 1. get all
    public List<HeDieuHanh> getAllEntity() {
        List<HeDieuHanh> heDieuHanhs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.HeDieuHanh
                                              (h.id, h.ten)
                                              FROM HeDieuHanh h
                                               """);

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
    
    public static void main(String[] args) {
//        List<HeDieuHanh> heDieuHanhs = getAllEntity();
//        heDieuHanhs.forEach(h -> System.out.println(h.toString()));

        HeDieuHanh hdh1 = getById(1);
        System.out.println(hdh1.getTen());
    }
}
