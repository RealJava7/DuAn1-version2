package view.Contains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CameraChiTiet;
import model.DienThoai;
import model.DongSanPham;
import model.Hang;
import model.HeDieuHanh;
import model.Imei;
import model.ManHinhChiTiet;
import model.MauSac;
import model.enums.LoaiManHinh;
import repository.DienThoaiRepository;
import service.DienThoaiService;
import service.DongSanPhamService;
import service.HangService;
import service.HeDieuHanhService;
import service.ImeiService;
import service.MauSacService;
import service.impl.DienThoaiServiceImpl;
import service.impl.DongSanPhamServiceImpl;
import service.impl.HangServiceImpl;
import service.impl.HeDieuHanhServiceImpl;
import service.impl.ImeiServiceImpl;
import service.impl.MauSacServiceImpl;
import view.Contains.EntitySanPham.ThemDongSP;
import view.Contains.EntitySanPham.ThemHang;
import view.Contains.EntitySanPham.ThemImei;
import view.Contains.EntitySanPham.ThemMauSac;
import view.Contains.EntitySanPham.ThemHeDieuHanh;
import viewmodel.DienThoaiResponse;
import viewmodel.ImeiResponse;

public class jplSanPham extends javax.swing.JPanel {

//    public static int numberOfImei = 0;
    private DefaultTableModel dtmActive;
    private DefaultTableModel dtmInactive;

    private DefaultComboBoxModel dcbmHang;
    private DefaultComboBoxModel dcbmDongSP;
    private DefaultComboBoxModel dcbmMauSac;

    private DefaultComboBoxModel dcbmHDH;
    private DefaultComboBoxModel dcbmRam;
    private DefaultComboBoxModel dcbmRom;
    private DefaultComboBoxModel dcbmLoaiMH;

    private HangService hangService;
    private DongSanPhamService dongSanPhamService;
    private MauSacService mauSacService;
    private HeDieuHanhService heDieuHanhService;
    private DienThoaiService dienThoaiService;

    private List<DienThoaiResponse> dienThoaiResponseActiveList;
    private List<DienThoaiResponse> dienThoaiResponseInactiveList;

    private static ImeiService imeiService;
    private static DefaultComboBoxModel dcbmImei;
    private static List<ImeiResponse> imeiResponseList;

    public jplSanPham() {
        initComponents();

        dtmActive = (DefaultTableModel) tbActive.getModel();
        dtmInactive = (DefaultTableModel) tbInactive.getModel();

        dcbmHang = (DefaultComboBoxModel) cbHang.getModel();
        dcbmDongSP = (DefaultComboBoxModel) cbDongSanPham.getModel();
        dcbmMauSac = (DefaultComboBoxModel) cbMauSac.getModel();
        dcbmHDH = (DefaultComboBoxModel) cbHeDieuHanh.getModel();

        dcbmRam = (DefaultComboBoxModel) cbRam.getModel();
        dcbmRom = (DefaultComboBoxModel) cbRom.getModel();
        dcbmLoaiMH = (DefaultComboBoxModel) cbLoaiManHinh.getModel();

        hangService = new HangServiceImpl();
        dongSanPhamService = new DongSanPhamServiceImpl();
        mauSacService = new MauSacServiceImpl();
        heDieuHanhService = new HeDieuHanhServiceImpl();
        dienThoaiService = new DienThoaiServiceImpl();

        dienThoaiResponseActiveList = new ArrayList<>();
        dienThoaiResponseActiveList = dienThoaiService.getAllResponse(true);

        dienThoaiResponseInactiveList = new ArrayList<>();
        dienThoaiResponseInactiveList = dienThoaiService.getAllResponse(false);

        dcbmImei = (DefaultComboBoxModel) cbImei.getModel();
        imeiService = new ImeiServiceImpl();
        imeiResponseList = new ArrayList<>();

        showActiveTable(dienThoaiResponseActiveList);
        showInactiveTable(dienThoaiResponseInactiveList);
        getDataForComboBox();
    }

    // 1
    private void getDataForComboBox() {
        // Hãng
        List<Hang> hangList = hangService.getAllEntity();
        hangList.forEach(h -> cbHang.addItem(h));

        // Màu sắc
        List<MauSac> mauSacList = mauSacService.getAll();
        mauSacList.forEach(ms -> cbMauSac.addItem(ms));

        // Hệ điều hành
        List<HeDieuHanh> heDieuHanhList = heDieuHanhService.getAll();
        heDieuHanhList.forEach(hdh -> cbHeDieuHanh.addItem(hdh));

        // Ram
        List<String> ramList = List.of("4", "6", "8", "12", "16");
        ramList.forEach(r -> dcbmRam.addElement(r));

        // Rom
        List<String> romList = List.of("64", "128", "256", "512", "1024");
        romList.forEach(r -> dcbmRom.addElement(r));

        // Loại màn hình
        LoaiManHinh[] loaiManHinhArr = LoaiManHinh.values();
        for (LoaiManHinh lmh : loaiManHinhArr) {
            cbLoaiManHinh.addItem(lmh);
        }
    }

    // 2
    private void showActiveTable(List<DienThoaiResponse> dienThoaiResponses) {
        dtmActive.setRowCount(0);
        dienThoaiResponses.forEach(dt -> dtmActive.addRow(dt.toDataRow()));
    }

    // 3
    private void showInactiveTable(List<DienThoaiResponse> dienThoaiResponses) {
        dtmInactive.setRowCount(0);
        dienThoaiResponses.forEach(dt -> dtmInactive.addRow(dt.toDataRow()));
    }

    // 4
    public static void showImeis(int idCurrentDienThoai) {
        if (idCurrentDienThoai == 0) {
            dcbmImei.removeAllElements();
            imeiResponseList = imeiService.getAllNoneDienThoaiImei();
            imeiResponseList.forEach(i -> dcbmImei.addElement(i.getImei()));
        } else {
            dcbmImei.removeAllElements();
            imeiResponseList = imeiService.getAllDienThoaiId(idCurrentDienThoai);
            List<ImeiResponse> noneDienThoaiImeis = imeiService.getAllNoneDienThoaiImei();
            imeiResponseList.addAll(noneDienThoaiImeis);
            imeiResponseList.forEach(i -> dcbmImei.addElement(i.getImei()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        txtTimKiemTen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbActive = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbInactive = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThemImei = new javax.swing.JLabel();
        cbImei = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbHang = new javax.swing.JComboBox<>();
        btnHang = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbDongSanPham = new javax.swing.JComboBox<>();
        btnDongSanPham = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cbMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cbHeDieuHanh = new javax.swing.JComboBox<>();
        btnHeDieuHanh = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbRam = new javax.swing.JComboBox<>();
        cbRom = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCpu = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCamChinh = new javax.swing.JTextField();
        txtCamPhu = new javax.swing.JTextField();
        txtCamGocRong = new javax.swing.JTextField();
        txtCamTele = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        txtDoPG = new javax.swing.JTextField();
        cbLoaiManHinh = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiemTen.setBackground(new java.awt.Color(255, 255, 255));

        tbActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN ĐT", "HÃNG", "RAM", "ROM", "PIN", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbActive.setFocusable(false);
        tbActive.setGridColor(new java.awt.Color(47, 85, 212));
        tbActive.setRowHeight(25);
        tbActive.setShowGrid(true);
        tbActive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActiveMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbActive);

        jLabel9.setText("TÌM KIẾM THEO TÊN:");

        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel10.setText("SẮP XẾP THEO GIÁ:");

        jButton4.setBackground(new java.awt.Color(47, 85, 212));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-amount-up-reversed-20.png"))); // NOI18N

        jButton5.setBackground(new java.awt.Color(47, 85, 212));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-descending-sorting-20.png"))); // NOI18N

        javax.swing.GroupLayout txtTimKiemTenLayout = new javax.swing.GroupLayout(txtTimKiemTen);
        txtTimKiemTen.setLayout(txtTimKiemTenLayout);
        txtTimKiemTenLayout.setHorizontalGroup(
            txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtTimKiemTenLayout.createSequentialGroup()
                        .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(102, 102, 102))))
        );
        txtTimKiemTenLayout.setVerticalGroup(
            txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtTimKiemTenLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("KHÁCH HÀNG", txtTimKiemTen);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbInactive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN", "HÃNG", "RAM", "ROM", "PIN", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbInactive.setFocusable(false);
        tbInactive.setGridColor(new java.awt.Color(47, 85, 212));
        tbInactive.setRowHeight(25);
        tbInactive.setShowGrid(true);
        jScrollPane2.setViewportView(tbInactive);
        if (tbInactive.getColumnModel().getColumnCount() > 0) {
            tbInactive.getColumnModel().getColumn(0).setMinWidth(100);
            tbInactive.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbInactive.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ĐÃ XÓA", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("MÃ ĐIỆN THOẠI:");

        txtMaSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel3.setText("TÊN ĐIỆN THOẠI:");

        txtTenSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel4.setText("GIÁ NHẬP:");

        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel5.setText("GIÁ BÁN:");

        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel6.setText("IMEI:");

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-trash-20.png"))); // NOI18N
        btnXoa.setText("XÓA");

        btnThemImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20.png"))); // NOI18N
        btnThemImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemImeiMouseClicked(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(47, 85, 212));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-new-20.png"))); // NOI18N
        btnLamMoi.setText("MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txtTenSanPham)
                            .addComponent(txtGiaNhap)
                            .addComponent(txtGiaBan)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemImei)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnThemImei))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("LINH KIỆN");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("HÃNG"));

        cbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new Hang[]{}));
        cbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHangActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(47, 85, 212));
        btnHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("DÒNG SP"));

        cbDongSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new DongSanPham[]{}));

        btnDongSanPham.setBackground(new java.awt.Color(47, 85, 212));
        btnDongSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnDongSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDongSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDongSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbDongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("MÀU SẮC"));

        cbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new MauSac[]{}));

        btnMauSac.setBackground(new java.awt.Color(47, 85, 212));
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("HỆ ĐIỀU HÀNH"));

        cbHeDieuHanh.setModel(new javax.swing.DefaultComboBoxModel<>(new HeDieuHanh[]{}));

        btnHeDieuHanh.setBackground(new java.awt.Color(47, 85, 212));
        btnHeDieuHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnHeDieuHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeDieuHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbHeDieuHanh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("RAM:");

        jLabel13.setText("ROM:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cbRom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("PIN:");

        jLabel15.setText("CPU:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPin))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(8, 8, 8)
                        .addComponent(txtCpu)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("CAMERA"));

        jLabel16.setText("CHÍNH:");

        jLabel17.setText("PHỤ:");

        jLabel18.setText("GÓC RỘNG:");

        jLabel19.setText("TELE:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19))
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCamChinh)
                    .addComponent(txtCamPhu)
                    .addComponent(txtCamGocRong)
                    .addComponent(txtCamTele))
                .addGap(38, 38, 38))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCamChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtCamPhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtCamGocRong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtCamTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("MÀN HÌNH"));

        jLabel20.setText("KÍCH THƯỚC:");

        jLabel21.setText("ĐỘ PHÂN GIẢI:");

        jLabel22.setText("LOẠI MÀN HÌNH:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKichThuoc)
                    .addComponent(txtDoPG)
                    .addComponent(cbLoaiManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDoPG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbLoaiManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        new ThemHang().setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        new ThemMauSac().setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnDongSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongSanPhamActionPerformed
        new ThemDongSP().setVisible(true);
    }//GEN-LAST:event_btnDongSanPhamActionPerformed

    private void btnThemImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemImeiMouseClicked

        int clickedActiveRow = tbActive.getSelectedRow();
        if (clickedActiveRow != -1) {
            DienThoaiResponse dienThoaiResponse = dienThoaiResponseActiveList.get(clickedActiveRow);
            int dienThoaiId = dienThoaiResponse.getId();
            new ThemImei(dienThoaiId).setVisible(true);
//            
//            ThemImei themImei = new ThemImei();
//            themImei.showDataTable2(dienThoaiId);
        }
//        new ThemImei().setVisible(true);
    }//GEN-LAST:event_btnThemImeiMouseClicked

    private void cbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHangActionPerformed
        Hang hang = (Hang) cbHang.getSelectedItem();
        int hangId = hang.getId();

        List<DongSanPham> dongSanPhamList = dongSanPhamService.getAll(hangId);
        cbDongSanPham.removeAllItems();
        dongSanPhamList.forEach(dsp -> cbDongSanPham.addItem(dsp));
    }//GEN-LAST:event_cbHangActionPerformed

    private void btnHeDieuHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeDieuHanhActionPerformed
        new ThemHeDieuHanh().setVisible(true);
    }//GEN-LAST:event_btnHeDieuHanhActionPerformed

    private void tbActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActiveMouseClicked
        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseActiveList.get(clickedRow);

        txtMaSanPham.setText(dienThoaiResponse.getMaDT());
        txtTenSanPham.setText(dienThoaiResponse.getTenDT());
        txtGiaNhap.setText(String.valueOf(dienThoaiResponse.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(dienThoaiResponse.getGiaBan()));

        dcbmRam.setSelectedItem(dienThoaiResponse.getRam());
        dcbmRom.setSelectedItem(dienThoaiResponse.getRom());

        txtPin.setText(String.valueOf(dienThoaiResponse.getDungLuongPin()));
        txtCpu.setText(dienThoaiResponse.getCpu());

        txtCamChinh.setText(String.valueOf(dienThoaiResponse.getCameraChinh()));
        txtCamPhu.setText(String.valueOf(dienThoaiResponse.getCameraPhu()));
        txtCamGocRong.setText(String.valueOf(dienThoaiResponse.getCameraGocRong()));
        txtCamTele.setText(String.valueOf(dienThoaiResponse.getCameraTele()));

        txtKichThuoc.setText(String.valueOf(dienThoaiResponse.getKichThuoc()));
        txtDoPG.setText(dienThoaiResponse.getDoPhanGiai());
        dcbmLoaiMH.setSelectedItem(dienThoaiResponse.getLoaiManHinh());

        Hang hang = new Hang(dienThoaiResponse.getHang());
        System.out.println(hang.getId());
        System.out.println(hang.getTenHang());
        dcbmHang.setSelectedItem(hang);
        DongSanPham dsp = new DongSanPham(dienThoaiResponse.getDongSanPham());
        dcbmDongSP.setSelectedItem(dsp);
        MauSac ms = new MauSac(dienThoaiResponse.getMauSac());
        System.out.println(ms.getId());
        System.out.println(ms.getMaMauSac());
        System.out.println(ms.getTenMauSac());
        dcbmMauSac.setSelectedItem(ms);
        HeDieuHanh hdh = new HeDieuHanh(dienThoaiResponse.getHeDieuHanh());
        dcbmHDH.setSelectedItem(hdh);

        DienThoai dienThoai = DienThoaiRepository.getById(dienThoaiResponse.getId());
        Set<Imei> imeiSet = dienThoai.getImeis();

        dcbmImei.removeAllElements();
        imeiSet.forEach(i -> cbImei.addItem(i.getImei()));
    }//GEN-LAST:event_tbActiveMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void lamMoiForm() {
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        dcbmImei.removeAllElements();

        cbRam.setSelectedIndex(0);
        cbRom.setSelectedIndex(0);

        txtPin.setText("");
        txtCpu.setText("");

        txtCamChinh.setText("");
        txtCamPhu.setText("");
        txtCamGocRong.setText("");
        txtCamTele.setText("");

        txtKichThuoc.setText("");
        txtDoPG.setText("");
        cbLoaiManHinh.setSelectedIndex(0);

        dienThoaiResponseActiveList = dienThoaiService.getAllResponse(true);
        showActiveTable(dienThoaiResponseActiveList);

        dienThoaiResponseInactiveList = dienThoaiService.getAllResponse(false);
        showInactiveTable(dienThoaiResponseInactiveList);
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // Camera
        CameraChiTiet cam = new CameraChiTiet();
        String camChinh = txtCamChinh.getText().trim();
        String camPhu = txtCamChinh.getText().trim();
        String camTele = txtCamChinh.getText().trim();
        String camGocRong = txtCamChinh.getText().trim();

        if (!camChinh.isBlank()) {
            cam.setCameraChinh(Integer.valueOf(camChinh));
        }
        if (!camPhu.isBlank()) {
            cam.setCameraPhu(Integer.valueOf(camPhu));
        }
        if (!camTele.isBlank()) {
            cam.setCameraTele(Integer.valueOf(camTele));
        }
        if (!camGocRong.isBlank()) {
            cam.setCameraGocRong(Integer.valueOf(camGocRong));
        }

        // Màn hình
        ManHinhChiTiet man = new ManHinhChiTiet();
        man.setKichThuoc(Float.valueOf(txtKichThuoc.getText().trim()));
        man.setDoPhanGiai(txtDoPG.getText().trim());
        man.setLoaiManHinh((LoaiManHinh) cbLoaiManHinh.getSelectedItem());

        // Điện thoại
        DienThoai dienThoai = new DienThoai();

        dienThoai.setMaDT(txtMaSanPham.getText().trim());
        dienThoai.setTenDT(txtTenSanPham.getText().trim());
        dienThoai.setMoTa("Dep");
        dienThoai.setDungLuongPin(Integer.valueOf(txtPin.getText().trim()));
        dienThoai.setRam(Integer.valueOf(String.valueOf(dcbmRam.getSelectedItem())));
        dienThoai.setRom(Integer.valueOf(String.valueOf(dcbmRom.getSelectedItem())));
        dienThoai.setCpu(txtCpu.getText().trim());
        dienThoai.setGiaNhap(Long.valueOf(txtGiaNhap.getText().trim()));
        dienThoai.setGiaBan(Long.valueOf(txtGiaBan.getText().trim()));
        dienThoai.setHinhAnh("abc.png");

        Hang hang = (Hang) dcbmHang.getSelectedItem();
        DongSanPham dsp = (DongSanPham) dcbmDongSP.getSelectedItem();
        MauSac mauSac = (MauSac) dcbmMauSac.getSelectedItem();
        HeDieuHanh hdh = (HeDieuHanh) dcbmHDH.getSelectedItem();

        dienThoai.setHang(hang);
        dienThoai.setDongSanPham(dsp);
        dienThoai.setMauSac(mauSac);
        dienThoai.setHeDieuHanh(hdh);

        dienThoai.setCameraChiTiet(cam);
        dienThoai.setManHinhChiTiet(man);

        // xử lý imei
        for (ImeiResponse imeiResponse : jplSanPham.imeiResponseList) {
            Imei imei = new Imei(imeiResponse.getImei());
            dienThoai.addImei(imei);
        }
        dienThoai.setSoLuong(jplSanPham.imeiResponseList.size());

        String addResult = dienThoaiService.add(dienThoai);
        JOptionPane.showMessageDialog(this, addResult);

        // update idDienThoai cua Imei (phải add dienThoai để có ID trước)
        DienThoai dtByMa = dienThoaiService.getByMaDT(dienThoai.getMaDT());
        for (ImeiResponse imeiResponse : jplSanPham.imeiResponseList) {
            imeiResponse.setIdDienThoai(dtByMa.getId());
            imeiService.update(imeiResponse);
        }

        // after add
        lamMoiForm();
        dienThoaiResponseActiveList = dienThoaiService.getAllResponse(true);
        showActiveTable(dienThoaiResponseActiveList);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa điện thoại?", "Xác nhận sửa điện thoại", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn điện thoại trước khi sửa!");
            return;
        }

        DienThoaiResponse selectedDienThoai = dienThoaiResponseActiveList.get(clickedRow);

        // xử lý imei mới
        List<ImeiResponse> noneDienThoaiImeis = imeiService.getAllNoneDienThoaiImei();
        for (ImeiResponse imeiResponse : noneDienThoaiImeis) {
            imeiResponse.setIdDienThoai(selectedDienThoai.getId());
            imeiService.update(imeiResponse);
        }

        selectedDienThoai.setMaDT(txtMaSanPham.getText().trim());
        selectedDienThoai.setTenDT(txtTenSanPham.getText().trim());
        selectedDienThoai.setMoTa("Dep");
        selectedDienThoai.setDungLuongPin(Integer.valueOf(txtPin.getText().trim()));
        selectedDienThoai.setRam(Integer.valueOf(String.valueOf(dcbmRam.getSelectedItem())));
        selectedDienThoai.setRom(Integer.valueOf(String.valueOf(dcbmRom.getSelectedItem())));
        selectedDienThoai.setCpu(txtCpu.getText().trim());
        selectedDienThoai.setGiaNhap(Long.valueOf(txtGiaNhap.getText().trim()));
        selectedDienThoai.setGiaBan(Long.valueOf(txtGiaBan.getText().trim()));
        selectedDienThoai.setHinhAnh("abc.png");

        Hang hang = (Hang) dcbmHang.getSelectedItem();
        System.out.println(hang.getTenHang());
        DongSanPham dsp = (DongSanPham) dcbmDongSP.getSelectedItem();
        MauSac mauSac = (MauSac) dcbmMauSac.getSelectedItem();
        System.out.println(mauSac.getMaMauSac());
        HeDieuHanh hdh = (HeDieuHanh) dcbmHDH.getSelectedItem();
        System.out.println(hdh.getTen());

        selectedDienThoai.setHang(hang.getTenHang());
        selectedDienThoai.setDongSanPham(dsp.getTen());
        selectedDienThoai.setMauSac(mauSac.getMaMauSac());
        selectedDienThoai.setHeDieuHanh(hdh.getTen());

        // Camera
        String camChinh = txtCamChinh.getText().trim();
        String camPhu = txtCamPhu.getText().trim();
        String camTele = txtCamTele.getText().trim();
        String camGocRong = txtCamGocRong.getText().trim();

        if (!camChinh.isBlank()) {
            selectedDienThoai.setCameraChinh(Integer.valueOf(camChinh));
        }
        if (!camPhu.isBlank()) {
            selectedDienThoai.setCameraPhu(Integer.valueOf(camPhu));
        }
        if (!camTele.isBlank()) {
            selectedDienThoai.setCameraTele(Integer.valueOf(camTele));
        }
        if (!camGocRong.isBlank()) {
            selectedDienThoai.setCameraGocRong(Integer.valueOf(camGocRong));
        }

        // Màn hình
        selectedDienThoai.setKichThuoc(Float.valueOf(txtKichThuoc.getText().trim()));
        selectedDienThoai.setDoPhanGiai(txtDoPG.getText().trim());
        selectedDienThoai.setLoaiManHinh((LoaiManHinh) cbLoaiManHinh.getSelectedItem());
//
        String updateResult = dienThoaiService.update(selectedDienThoai);
        JOptionPane.showMessageDialog(this, updateResult);

        // after update
        lamMoiForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongSanPham;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnHeDieuHanh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel btnThemImei;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<DongSanPham> cbDongSanPham;
    private javax.swing.JComboBox<Hang> cbHang;
    private javax.swing.JComboBox<HeDieuHanh> cbHeDieuHanh;
    private javax.swing.JComboBox<String> cbImei;
    private javax.swing.JComboBox<LoaiManHinh> cbLoaiManHinh;
    private javax.swing.JComboBox<MauSac> cbMauSac;
    private javax.swing.JComboBox<String> cbRam;
    private javax.swing.JComboBox<String> cbRom;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable tbActive;
    private javax.swing.JTable tbInactive;
    private javax.swing.JTextField txtCamChinh;
    private javax.swing.JTextField txtCamGocRong;
    private javax.swing.JTextField txtCamPhu;
    private javax.swing.JTextField txtCamTele;
    private javax.swing.JTextField txtCpu;
    private javax.swing.JTextField txtDoPG;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JPanel txtTimKiemTen;
    // End of variables declaration//GEN-END:variables
}
