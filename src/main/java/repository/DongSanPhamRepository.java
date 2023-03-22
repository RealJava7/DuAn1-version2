package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.DongSanPham;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

public class DongSanPhamRepository {

    // 1. get all
    public List<DongSanPham> getAllEntityByHang(int hangId) {
        List<DongSanPham> dongSanPhams = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.DongSanPham
                                              (dsp.id, dsp.ten, dsp.hangDienThoai)
                                              FROM DongSanPham dsp
                                              WHERE dsp.hangDienThoai.id = :hangId
                                               """);
            query.setParameter("hangId", hangId);
            dongSanPhams = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dongSanPhams;
    }

    // 2. get by id
    public static DongSanPham getById(int id) {
        DongSanPham dongSanPham = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            dongSanPham = session.get(DongSanPham.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return dongSanPham;
    }

    public static void main(String[] args) {
//        List<DongSanPham> dongSanPhams = getAllByHang(1);
//        System.out.println(dongSanPhams.size());
//        dongSanPhams.forEach(d -> System.out.println(d.toString()));

//        DongSanPham dsp3 = getById(3);
//        System.out.println(dsp3.getTen());
    }
}
