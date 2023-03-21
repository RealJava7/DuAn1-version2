package repository;

import model.PhieuGiamGia;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

public class PhieuGiamGiaRepository {

    // 1. add
    public boolean add(PhieuGiamGia phieu) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(phieu);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. update
//    public boolean update(PhieuGiamGia phieu, int id) {
//        boolean check = false;
//        try {
//            Session session = HibernateUtil.getFACTORY().openSession();
//
//            PhieuGiamGia phieuInDB = session.get(PhieuGiamGia.class, id);
//            // 'phieuInDB' là thằng trong db mà mình get ra bằng id
//            // mình có truyền vào hàm 'phieu', mình sẽ set lại các thuộc tính vào 'phieuInDB'
//
//            phieuInDB.setMaPhieu(phieu.getMaPhieu());
//            phieuInDB.setNgayBatDau(phieu.getNgayBatDau());
//            phieuInDB.setNgayKetThuc(phieu.getNgayKetThuc());
//            phieuInDB.setLuotSuDung(phieu.getLuotSuDung());
//            phieuInDB.setDieuKien(phieu.getDieuKien());
//            phieuInDB.setGiaTri(phieu.getGiaTri());
//
//            Transaction transaction = session.beginTransaction();
//            session.update(phieuInDB);
//            transaction.commit();
//            check = true;
//        } catch (HibernateException ex) {
//            ex.printStackTrace(System.out);
//        }
//        return check;
//    }
}
