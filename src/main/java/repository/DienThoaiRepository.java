package repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import model.CameraChiTiet;
import model.DienThoai;
import model.DongSanPham;
import model.Hang;
import model.HeDieuHanh;
import model.Imei;
import model.ManHinhChiTiet;
import model.MauSac;
import model.enums.LoaiManHinh;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.DienThoaiResponse;

public class DienThoaiRepository {

    // 1. add
    public static boolean add(DienThoai dienThoai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(dienThoai);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. get all
    public List<DienThoaiResponse> getAll() {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              h.tenHang, dsp.ten, ms.tenMauSac, hdh.ten,
                                              c.cameraChinh, c.cameraPhu, c.cameraGocRong, c.cameraTele,
                                              mh.kichThuoc, mh.doPhanGiai, mh.loaiManHinh)
                                              FROM DienThoai dt
                                              INNER JOIN dt.hang h
                                              INNER JOIN dt.dongSanPham dsp
                                              INNER JOIN dt.mauSac ms
                                              INNER JOIN dt.heDieuHanh hdh
                                              INNER JOIN dt.cameraChiTiet c
                                              INNER JOIN dt.manHinhChiTiet mh
                                               """);
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }

    // Test
    public static void main(String[] args) {
        // get all
//        List<DienThoaiResponse> dienThoaiResponses = getAll();
//        dienThoaiResponses.forEach(dt -> System.out.println(dt.toString()));
        
        // add
//        CameraChiTiet cam = new CameraChiTiet();
//        cam.setCameraChinh(48);
//        cam.setCameraPhu(36);
//        cam.setCameraTele(48);
//
//        ManHinhChiTiet man = new ManHinhChiTiet();
//        man.setKichThuoc(6.7f);
//        man.setDoPhanGiai("3600x1200");
//        man.setLoaiManHinh(LoaiManHinh.SUPER_AMOLED);
//
//        DienThoai dienThoai = new DienThoai();
//
//        dienThoai.setMaDT("IP109");
//        dienThoai.setTenDT("iPhone 14 Pro Max 128GB chính hãng");
//        dienThoai.setMoTa("Đẹp long lanh");
//        dienThoai.setDungLuongPin(3400);
//        dienThoai.setRam(128);
//        dienThoai.setRom(6);
//        dienThoai.setCpu("Apple A16 Bionic");
//        dienThoai.setGiaNhap(23_000_000L);
//        dienThoai.setGiaBan(27_000_000L);
//        dienThoai.setSoLuong(5);
//        dienThoai.setHinhAnh("abc.png");
//
//        Hang hang1 = HangRepository.getById(1);
//        DongSanPham dsp3 = DongSanPhamRepository.getById(3);
//        MauSac ms1 = MauSacRepository.getById(1);
//        HeDieuHanh hdh1 = HeDieuHanhRepository.getById(1);
//
//        dienThoai.setHang(hang1);
//        dienThoai.setDongSanPham(dsp3);
//        dienThoai.setMauSac(ms1);
//        dienThoai.setHeDieuHanh(hdh1);
//
//        dienThoai.setCameraChiTiet(cam);
//        dienThoai.setManHinhChiTiet(man);
//
//        Set<Imei> imeis = new HashSet<>();
//        dienThoai.setImeis(imeis);
//
//        System.out.println(add(dienThoai));
    }
}
