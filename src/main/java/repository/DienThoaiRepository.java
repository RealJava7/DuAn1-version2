package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    // 2. get all by trangThai
    public List<DienThoaiResponse> getAllResponseByStatus(boolean status) {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              hdh.ten, h.tenHang, dsp.ten, ms.tenMauSac,
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
                                              ORDER BY dt.tenDT
                                               """);
            query.setParameter("status", status);
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }
    
    // 2.1 get sản phẩm hết hàng
    public List<DienThoaiResponse> get5SanPhamHetHang() {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              ( dt.maDT, dt.tenDT, dt.soLuong)
                                              FROM DienThoai dt Where dt.soLuong <= 5
                                               """);

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

    // 5. get all by giaBan
    public List<DienThoaiResponse> getAllResponseByGiaBan(String order) {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              hdh.ten, h.tenHang, dsp.ten, ms.tenMauSac,
                                              c.cameraChinh, c.cameraPhu, c.cameraGocRong, c.cameraTele,
                                              mh.kichThuoc, mh.doPhanGiai, mh.loaiManHinh)
                                              FROM DienThoai dt
                                              INNER JOIN dt.hang h
                                              INNER JOIN dt.dongSanPham dsp
                                              INNER JOIN dt.mauSac ms
                                              INNER JOIN dt.heDieuHanh hdh
                                              INNER JOIN dt.cameraChiTiet c
                                              INNER JOIN dt.manHinhChiTiet mh
                                              WHERE dt.trangThai = TRUE
                                              ORDER BY dt.giaBan
                                               """ + order);
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }

    // 6. search by name
    public List<DienThoaiResponse> searchAllResponseByName(String keyword) {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              hdh.ten, h.tenHang, dsp.ten, ms.tenMauSac,
                                              c.cameraChinh, c.cameraPhu, c.cameraGocRong, c.cameraTele,
                                              mh.kichThuoc, mh.doPhanGiai, mh.loaiManHinh)
                                              FROM DienThoai dt
                                              INNER JOIN dt.hang h
                                              INNER JOIN dt.dongSanPham dsp
                                              INNER JOIN dt.mauSac ms
                                              INNER JOIN dt.heDieuHanh hdh
                                              INNER JOIN dt.cameraChiTiet c
                                              INNER JOIN dt.manHinhChiTiet mh
                                              WHERE dt.trangThai = TRUE
                                              AND dt.tenDT like :keyword
                                               """);
            query.setParameter("keyword", "%" + keyword + "%");
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }
    
    // get List<DienThoaiResponse> by tenHang
    public List<DienThoaiResponse> getResponsesByHang(String tenHang) {
        List<DienThoaiResponse> dienThoaiResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DienThoaiResponse
                                              (dt.id, dt.maDT, dt.tenDT, dt.moTa, dt.dungLuongPin, dt.rom, dt.ram, dt.cpu, dt.giaNhap, dt.giaBan, dt.soLuong, dt.hinhAnh,
                                              hdh.ten, h.tenHang, dsp.ten, ms.tenMauSac,
                                              c.cameraChinh, c.cameraPhu, c.cameraGocRong, c.cameraTele,
                                              mh.kichThuoc, mh.doPhanGiai, mh.loaiManHinh)
                                              FROM DienThoai dt
                                              INNER JOIN dt.hang h
                                              INNER JOIN dt.dongSanPham dsp
                                              INNER JOIN dt.mauSac ms
                                              INNER JOIN dt.heDieuHanh hdh
                                              INNER JOIN dt.cameraChiTiet c
                                              INNER JOIN dt.manHinhChiTiet mh
                                              WHERE dt.hang.tenHang = :tenHang
                                              AND dt.trangThai = true
                                               """);
            query.setParameter("tenHang", tenHang);
            dienThoaiResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiResponses;
    }

    // 6. update
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
            MauSac mauSac = MauSacRepository.getByTen(dienThoaiResponse.getMauSac());
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

            session.update(dienThoai);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 7. thay đổi trạng thái điện thoại
    public boolean changeStatus(DienThoaiResponse dienThoaiResponse, boolean newStatus) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            DienThoai dienThoai = session.get(DienThoai.class, dienThoaiResponse.getId());
            dienThoai.setTrangThai(newStatus);

            session.update(dienThoai);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    // 8. tăng/giảm số lượng điện thoại đi 1 (dùng nhiều trong view bán hàng)
    public static void updateSoLuongDienThoai(String imeiStr, int tangGiam) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            Imei imei = ImeiRepository.getByImei(imeiStr);
            int idDienThoai = imei.getDienThoai().getId();

            DienThoai dienThoai = session.get(DienThoai.class, idDienThoai);
            if (tangGiam == -1) {
                dienThoai.setSoLuong(dienThoai.getSoLuong() - 1);
            } else if (tangGiam == 1) {
                dienThoai.setSoLuong(dienThoai.getSoLuong() + 1);
            }

            session.update(dienThoai);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void update1(int id) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            DienThoai dienThoai = session.get(DienThoai.class, id);
            Set<Imei> imeis = dienThoai.getImeis();
            imeis = imeis.stream().filter(i -> i.getTrangThai() == 0).collect(Collectors.toSet());
            dienThoai.setSoLuong(imeis.size());

            session.update(dienThoai);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        update1(1);
        update1(2);
        update1(6);
        update1(7);
        update1(8);
        update1(9);
        update1(11);
        System.out.println("true");
    }
}
