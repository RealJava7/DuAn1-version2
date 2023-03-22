/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.Query;
import model.PhieuTraGop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.PhieuTraGopRepository;
import utility.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopRepositoryImpl implements PhieuTraGopRepository {

    public boolean delete(String id) {
        return false;
    }

    public boolean update(String id, PhieuTraGop phieuTraGop) {
        return false;
    }

    public boolean insert(PhieuTraGop phieuTraGop) {
        return false;
    }

    public List<PhieuTraGop> getAll() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<PhieuTraGop> getByString(String s) {
        return null;
    }

    public List<PhieuTraGop> getByTime(int index) {
        return null;
    }

    public List<PhieuTraGop> getByTrangThai(int index) {
        return null;
    }
}
