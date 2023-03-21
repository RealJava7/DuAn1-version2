package utility;

import java.util.Properties;
import model.CameraChiTiet;
import model.DienThoai;
import model.DongSanPham;
import model.Hang;
import model.Imei;
import model.KhachHang;
import model.ManHinhChiTiet;
import model.MauSac;
import model.NhanVien;
import model.PhieuGiamGia;
import model.PhieuGiamGiaChiTiet;
import model.TaiKhoan;
import model.TheTichDiem;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=duan1_spring2023");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "1");

        conf.setProperties(properties);
        conf.addAnnotatedClass(PhieuGiamGia.class);
        conf.addAnnotatedClass(PhieuGiamGiaChiTiet.class);
        conf.addAnnotatedClass(DienThoai.class);
        conf.addAnnotatedClass(CameraChiTiet.class);
        conf.addAnnotatedClass(ManHinhChiTiet.class);
        conf.addAnnotatedClass(DongSanPham.class);
        conf.addAnnotatedClass(Imei.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(TheTichDiem.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(TaiKhoan.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
