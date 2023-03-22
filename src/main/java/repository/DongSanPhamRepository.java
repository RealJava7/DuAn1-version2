package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.DongSanPham;
import model.Hang;
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
    
    public static void main(String[] args) {
//        List<DongSanPham> dongSanPhams = getAllByHang(1);
//        System.out.println(dongSanPhams.size());
//        dongSanPhams.forEach(d -> System.out.println(d.toString()));
    }
}
