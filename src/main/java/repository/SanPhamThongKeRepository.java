/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

/**
 *
 * @author Ma
 */
public class SanPhamThongKeRepository {
          
    public List<Object[]> getSPTKThang(int month, int year) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select 
                                              dt.tenDT, count(ct.imei)
                                              from Imei im join HoaDonChiTiet ct on im = ct.imei
                                              join DienThoai dt on dt = im.dienThoai
                                              join HoaDon hd on hd = ct.hoaDon
                                              where month(hd.ngayThanhToan) = :month and year(hd.ngayThanhToan) = :year
                                              group by dt.tenDT
                                               """);
            query.setParameter("month", month);
            query.setParameter("year", year);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public List<Object[]> getSPTKNam(int year) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select 
                                              dt.tenDT, count(ct.imei)
                                              from Imei im join HoaDonChiTiet ct on im = ct.imei
                                              join DienThoai dt on dt = im.dienThoai
                                              join HoaDon hd on hd = ct.hoaDon
                                              where year(hd.ngayThanhToan) = :year
                                              group by dt.tenDT
                                               """);
            query.setParameter("year", year);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
//    
//    public static void main(String[] args) {
//        List<Object[]> lists = getSPTKThang3(0, 2023);
//        List<SanPhamThongKeResponse> listRe = new ArrayList<>();
//        for (Object[] list : lists) {
//            String tenDT = (String) list[0];
//            Long count =  (Long) list[1];
//            System.out.println(tenDT + ": " + count);
//            listRe.add(new SanPhamThongKeResponse(tenDT, count));
//        }
//        
//        for (SanPhamThongKeResponse sp : listRe) {
//            System.out.println(sp.toString());
//        }
//    }
}
