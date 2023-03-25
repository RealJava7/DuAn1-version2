package repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
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
    public List<DienThoaiResponse> getAllResponse(boolean status) {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              hdh.ten, h.tenHang, dsp.ten, ms.maMauSac,
                                              c.cameraChinh, c.cameraPhu, c.cameraGocRong, c.cameraTele,
                                              mh.kichThuoc, mh.doPhanGiai, mh.loaiManHinh)
                                              FROM DienThoai dt
                                              INNER JOIN dt.hang h
                                              INNER JOIN dt.dongSanPham dsp
                                              INNER JOIN dt.mauSac ms
                                              INNER JOIN dt.heDieuHanh hdh
                                              INNER JOIN dt.cameraChiTiet c
                                              INNER JOIN dt.manHinhChiTiet mh
                                              WHERE dt.trangThai = :status
                                               """);
            query.setParameter("status", status);
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }

    // 3. get by id
    public static DienThoai getById(int id) {
        DienThoai dienThoai = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            dienThoai = session.get(DienThoai.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return dienThoai;
    }
    
    // 4. get by maDienThoai
    public DienThoai getByMaDT(String maDT) {
        DienThoai dienThoai = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT dt
                                              FROM DienThoai dt
                                              WHERE dt.maDT = :maDT
                                               """);
            query.setParameter("maDT", maDT);
            dienThoai = (DienThoai) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            dienThoai = null;
        }
        return dienThoai;
    }
    
    // 5. update
    public boolean update(DienThoaiResponse dienThoaiResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            DienThoai dienThoai = session.get(DienThoai.class, dienThoaiResponse.getId());
            
            dienThoai.setMaDT(dienThoaiResponse.getMaDT());
            dienThoai.setTenDT(dienThoaiResponse.getTenDT());
            dienThoai.setMoTa(dienThoaiResponse.getMoTa());
            dienThoai.setDungLuongPin(dienThoaiResponse.getDungLuongPin());
            dienThoai.setRom(dienThoaiResponse.getRom());
            dienThoai.setRam(dienThoaiResponse.getRam());
            dienThoai.setCpu(dienThoaiResponse.getCpu());
            dienThoai.setGiaNhap(dienThoaiResponse.getGiaNhap());
            dienThoai.setGiaBan(dienThoaiResponse.getGiaBan());
            dienThoai.setSoLuong(dienThoaiResponse.getSoLuong());
            dienThoai.setHinhAnh(dienThoaiResponse.getHinhAnh());
            
            Hang hang = HangRepository.getByTenHang(dienThoaiResponse.getHang());
            DongSanPham dsp = DongSanPhamRepository.getByTenDongSP(dienThoaiResponse.getDongSanPham());
            MauSac mauSac = MauSacRepository.getByMa(dienThoaiResponse.getMauSac());
            System.out.println("in repo");
            System.out.println(mauSac.getId());
            System.out.println(mauSac.getMaMauSac());
            System.out.println(mauSac.getTenMauSac());
            HeDieuHanh hdh = HeDieuHanhRepository.getByTen(dienThoaiResponse.getHeDieuHanh());
            
            dienThoai.setHang(hang);
            dienThoai.setDongSanPham(dsp);
            dienThoai.setMauSac(mauSac);
            dienThoai.setHeDieuHanh(hdh);
            
            // Camera
            CameraChiTiet cameraChiTiet = dienThoai.getCameraChiTiet();
            cameraChiTiet.setCameraChinh(dienThoaiResponse.getCameraChinh());
            cameraChiTiet.setCameraPhu(dienThoaiResponse.getCameraPhu());
            cameraChiTiet.setCameraGocRong(dienThoaiResponse.getCameraGocRong());
            cameraChiTiet.setCameraTele(dienThoaiResponse.getCameraTele());
            dienThoai.setCameraChiTiet(cameraChiTiet);
            
            // Màn hình
            ManHinhChiTiet manHinhChiTiet = dienThoai.getManHinhChiTiet();
            manHinhChiTiet.setKichThuoc(dienThoaiResponse.getKichThuoc());
            manHinhChiTiet.setDoPhanGiai(dienThoaiResponse.getDoPhanGiai());
            manHinhChiTiet.setLoaiManHinh(dienThoaiResponse.getLoaiManHinh());
            dienThoai.setManHinhChiTiet(manHinhChiTiet);

            System.out.println(dienThoai.toString());
            session.update(dienThoai);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public static void main(String[] args) {
//        DienThoai dt = getById(1);
//        System.out.println(dt.toString());
        
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
