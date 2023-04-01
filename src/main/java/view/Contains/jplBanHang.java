package view.Contains;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.HoaDonChiTiet;
import model.Imei;
import model.KhachHang;
import model.PhieuGiamGia;
import model.TheTichDiem;
import repository.PhieuGiamGiaRepository;
import service.DienThoaiService;
import service.ImeiService;
import service.KhachHangService;
import service.PhieuGiamGiaService;
import service.impl.DienThoaiServiceImpl;
import service.impl.ImeiServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.PhieuBaoHanhServiceImpl;
import service.impl.PhieuGiamGiaServiceImpl;
import static view.Contains.jplKhachHang.generateRandomNumericString;
import view.Contains.tragop.ViewTraGopForm;
import view.Contains.tragop.jplFormThanhToan;
import viewmodel.DienThoaiResponse;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.ImeiResponse;
import viewmodel.KhachHangResponse;
import viewmodel.PhieuGiamGiaResponse;

public class jplBanHang extends javax.swing.JPanel {

    private DefaultTableModel dtmDienThoai;

    private List<DienThoaiResponse> dienThoaiResponseList;
    private List<KhachHangResponse> khachHangResponseList;
    private List<HoaDonChiTietResponse> hoaDonChiTietResponseList;

    private DienThoaiService dienThoaiService;
    private KhachHangService khachHangService;
    private ImeiService imeiService;
    private PhieuGiamGiaService phieuGiamGiaService;

    private NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vn", "VN"));

    public jplBanHang() {
        initComponents();

        dtmDienThoai = (DefaultTableModel) tbDienThoai.getModel();

        dienThoaiResponseList = new ArrayList<>();
        khachHangResponseList = new ArrayList<>();
        hoaDonChiTietResponseList = new ArrayList<>();

        dienThoaiService = new DienThoaiServiceImpl();
        khachHangService = new KhachHangServiceImpl();
        imeiService = new ImeiServiceImpl();
        phieuGiamGiaService = new PhieuGiamGiaServiceImpl();

        dienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        khachHangResponseList = khachHangService.getAllResponseByStatus(1);

        showKhachHangCombobox();
        showDienThoaiTable(dienThoaiResponseList);

        //khởi tạo giở hàng
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(++soDon));
        //Khởi tạo cách thanh toán
//        addFormThanhToan(new jplFormThanhToan());
        viewTable();
    }

    // 1. hiển thị điện thoại trạng thái = true
    private void showDienThoaiTable(List<DienThoaiResponse> dienThoaiResponses) {
        dtmDienThoai.setRowCount(0);
        dienThoaiResponses.forEach(dt -> dtmDienThoai.addRow(dt.toDataRow2()));
    }

    // 2. hiển thị combobox khách hàng
    private void showKhachHangCombobox() {
        cbKhachHang.removeAllItems();
        for (KhachHangResponse s : khachHangResponseList) {
            cbKhachHang.addItem(s.getHoTen() + " - " + s.getSdt());
        }
    }

    private void viewTable() {
        JTableHeader Theader = tbDienThoai.getTableHeader();

        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);
    }

//    private void addFormThanhToan(JPanel jpl) {
//        jpl.setVisible(true);
//        jplThanhToanContain.removeAll();
//        jplThanhToanContain.add(jpl);
//        jplThanhToanContain.updateUI();
//    }
    private void setDefault() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        jdateNgaySinh.setDate(null);
        txtDiaChi.setText("");
        chkTrangThai.setSelected(false);
    }

    private Boolean kiemTra(int id, String email) {

        StringBuilder sb = new StringBuilder();
        KhachHangResponse kh = khachHangService.getKhachHangByEmail(email);
        if (txtHoTen.getText().isBlank()) {
            sb.append("Không để trống họ và tên\n");

        }
        if (txtEmail.getText().trim().isBlank()) {
            sb.append("Không để trống email\n");
        } else if (!txtEmail.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            sb.append("vui lòng nhập đúng định dạng email\n");
        } else if (kh != null) {
            if (id == 0) {
                String str = "Email đã tồn tại\n";

                if (kh.getTrangThai() == 0) {
                    str = str + " trong phần đã xóa\n";
                }
                sb.append(str);
            } else if (id > 0) {

                String str = "";
                for (KhachHangResponse s : khachHangResponseList) {
                    if (s.getId() != id) {

                        if (txtEmail.getText().trim().toLowerCase().equals(s.getEmail().toLowerCase()) == true) {

                            str = "Email đã tồn tại\n";
                            if (s.getTrangThai() == 0) {
                                str = str + " trong phần đã xóa\n";
                            }
                            sb.append(str);
                            break;
                        }
                    }
                }
            }
        }

        if (txtSdt.getText().trim().isBlank()) {
            sb.append("Không để trống SĐT\n");
        } else if (!txtSdt.getText().trim().matches("^(0|\\+84)[1-9][0-9]{8}$")) {
            sb.append("vui lòng nhập đúng định dạng SĐT\n");
        }
        if (txtDiaChi.getText().isBlank()) {
            sb.append("Không để trống Địa Chỉ\n");
        }
        if (jdateNgaySinh.getDate() == null) {

            sb.append("Không để trống Ngày Sinh\n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return false;
        }
        return true;
    }
    private static final String NUMERIC_CHARS = "0123456789";
    private static final int STRING_LENGTH = 15;

    public static String generateRandomNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(NUMERIC_CHARS.length());
            char randomChar = NUMERIC_CHARS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public KhachHang getData() {
        TheTichDiem theTichDiem = new TheTichDiem();
        theTichDiem.setMaThe(generateRandomNumericString(STRING_LENGTH));
        theTichDiem.setNgayKichHoat(LocalDate.now());
        theTichDiem.setSoDiem(0);
        theTichDiem.setTrangThai(true);

        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(txtHoTen.getText().trim());
        khachHang.setEmail(txtEmail.getText().trim());
        khachHang.setSdt(txtSdt.getText().trim());
        if (cboNam.isSelected()) {
            khachHang.setGioiTinh(true);
        } else {
            khachHang.setGioiTinh(false);
        }
        try {
            LocalDate localDate = jdateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            khachHang.setNgaySinh(localDate);
        } catch (Exception e) {
            System.out.println("alo");
        }

        khachHang.setDiaChi(txtDiaChi.getText().trim());
        if (chkTrangThai.isSelected()) {
            khachHang.setTrangThai(1);
        } else {
            khachHang.setTrangThai(0);
        }

        khachHang.setTheTichDiem(theTichDiem);
        return khachHang;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        ThemKhachHang = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cboNam = new javax.swing.JRadioButton();
        cboNu = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        chkTrangThai = new javax.swing.JCheckBox();
        btnThem = new javax.swing.JButton();
        jdateNgaySinh = new com.toedter.calendar.JDateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuItem1 = new javax.swing.JMenuItem();
        menuItem2 = new javax.swing.JMenuItem();
        imeiDialog = new javax.swing.JDialog();
        jLabel48 = new javax.swing.JLabel();
        cbImeiInDialog = new javax.swing.JComboBox<>();
        btnOkImei = new javax.swing.JButton();
        jpnFormBanHang = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        btnSapXepGiaTangDan = new javax.swing.JButton();
        btnSapXepGiaGiamDan = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jplGioHang = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel3 = new javax.swing.JLabel();
        btnXoaDonHang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDienThoai = new javax.swing.JTable();
        Jpanel20 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbKhachHang = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnThanhToan1 = new javax.swing.JButton();
        lbTongTien = new javax.swing.JLabel();
        lbTienGiam = new javax.swing.JLabel();
        lbKhachPhaiTra = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lbTienThua = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        rdbtnTienMat = new javax.swing.JRadioButton();
        rdBtnChuyenKhoan = new javax.swing.JRadioButton();
        cbMaGiamGia = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lbTongTien2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbTienGiam2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lbKhachPhaiTra2 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtGhiChu1 = new javax.swing.JTextArea();
        btnThanhToan2 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbTraTruocToiThieu = new javax.swing.JLabel();
        lbConNo = new javax.swing.JLabel();
        lbTraHangThang = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtTienTraTruoc = new javax.swing.JTextField();
        cbKyHan = new javax.swing.JComboBox<>();
        cbMaGiamGia2 = new javax.swing.JComboBox<>();
        txtLaiSuat = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        lbTongNo = new javax.swing.JLabel();
        btnTaoDonHang = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel13.setText("HỌ VÀ TÊN:");

        jLabel14.setText("EMAIL:");

        jLabel15.setText("SDT:");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(51, 51, 51));
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel16.setText("GIỚI TÍNH:");

        cboNam.setBackground(new java.awt.Color(255, 255, 255));
        cboNam.setSelected(true);
        cboNam.setText("NAM");

        cboNu.setBackground(new java.awt.Color(255, 255, 255));
        cboNu.setText("NỮ");

        jLabel17.setText("NGÀY SINH:");

        jLabel18.setText("ĐỊA CHỈ:");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel19.setText("TRẠNG THÁI:");

        chkTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        chkTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkTrangThai.setForeground(new java.awt.Color(102, 102, 102));
        chkTrangThai.setText("Hoạt Động?");

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.setBorderPainted(false);
        btnThem.setFocusable(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jdateNgaySinh.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(30, 30, 30)
                        .addComponent(cboNam)
                        .addGap(26, 26, 26)
                        .addComponent(cboNu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(28, 28, 28)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(68, 68, 68)
                                .addComponent(txtSdt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(54, 54, 54)
                                .addComponent(txtEmail)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(jdateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(chkTrangThai)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNam)
                        .addComponent(cboNu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addContainerGap())
        );

        javax.swing.GroupLayout ThemKhachHangLayout = new javax.swing.GroupLayout(ThemKhachHang.getContentPane());
        ThemKhachHang.getContentPane().setLayout(ThemKhachHangLayout);
        ThemKhachHangLayout.setHorizontalGroup(
            ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThemKhachHangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ThemKhachHangLayout.setVerticalGroup(
            ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThemKhachHangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuItem1.setText("Xem chi tiết");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuItem1);

        menuItem2.setText("Thêm vào giỏ");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuItem2);

        jLabel48.setText("Chọn IMEI");

        btnOkImei.setText("OK");
        btnOkImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imeiDialogLayout = new javax.swing.GroupLayout(imeiDialog.getContentPane());
        imeiDialog.getContentPane().setLayout(imeiDialogLayout);
        imeiDialogLayout.setHorizontalGroup(
            imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imeiDialogLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(imeiDialogLayout.createSequentialGroup()
                        .addComponent(cbImeiInDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOkImei)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        imeiDialogLayout.setVerticalGroup(
            imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imeiDialogLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbImeiInDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkImei))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jpnFormBanHang.setBackground(new java.awt.Color(255, 255, 255));
        jpnFormBanHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnSapXepGiaTangDan.setBackground(new java.awt.Color(47, 85, 212));
        btnSapXepGiaTangDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-amount-up-reversed-20.png"))); // NOI18N
        btnSapXepGiaTangDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepGiaTangDanActionPerformed(evt);
            }
        });

        btnSapXepGiaGiamDan.setBackground(new java.awt.Color(47, 85, 212));
        btnSapXepGiaGiamDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-descending-sorting-20.png"))); // NOI18N
        btnSapXepGiaGiamDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepGiaGiamDanActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Tìm Kiếm:");

        jLabel9.setText("Phân Loại:");

        jLabel10.setText("Lọc Theo Giá:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        jplGioHang.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 85, 212));
        jLabel3.setText("DANH SÁCH HÓA ĐƠN CHỜ");

        btnXoaDonHang.setBackground(new java.awt.Color(47, 85, 212));
        btnXoaDonHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaDonHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaDonHang.setText("Xóa Đơn");
        btnXoaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDonHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplGioHangLayout = new javax.swing.GroupLayout(jplGioHang);
        jplGioHang.setLayout(jplGioHangLayout);
        jplGioHangLayout.setHorizontalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jplGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaDonHang)
                .addContainerGap())
        );
        jplGioHangLayout.setVerticalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplGioHangLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ĐT", "Tên ĐT", "Giá Bán", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDienThoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDienThoai);
        if (tbDienThoai.getColumnModel().getColumnCount() > 0) {
            tbDienThoai.getColumnModel().getColumn(1).setResizable(false);
            tbDienThoai.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbDienThoai.getColumnModel().getColumn(3).setResizable(false);
            tbDienThoai.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jpnFormBanHangLayout = new javax.swing.GroupLayout(jpnFormBanHang);
        jpnFormBanHang.setLayout(jpnFormBanHangLayout);
        jpnFormBanHangLayout.setHorizontalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFormBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnFormBanHangLayout.setVerticalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFormBanHangLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel20.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("TẠO HÓA ĐƠN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Khách Hàng:");

        cbKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Nhân Viên:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("jLabel7");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("THANH TOÁN");

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Tổng Tiền :");

        jLabel20.setText("Mã Giảm Giá :");

        jLabel21.setText("Tiền Giảm :");

        jLabel22.setText("Khách Phải Trả :");

        jLabel23.setText("Tiền Khách Đưa :");

        jLabel24.setText("Tiền thừa :");

        jLabel25.setText("Ghi Chú :");

        btnThanhToan1.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan1.setText("Thanh Toán");

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lbTongTien.setText("0");

        lbTienGiam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienGiam.setForeground(new java.awt.Color(255, 51, 51));
        lbTienGiam.setText("0");

        lbKhachPhaiTra.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbKhachPhaiTra.setForeground(new java.awt.Color(51, 51, 255));
        lbKhachPhaiTra.setText("0");

        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        lbTienThua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienThua.setForeground(new java.awt.Color(51, 51, 255));
        lbTienThua.setText("0");

        txtGhiChu.setColumns(1);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        jLabel1.setText("Hình Thức TT:");

        rdbtnTienMat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdbtnTienMat);
        rdbtnTienMat.setSelected(true);
        rdbtnTienMat.setText("Tiền Mặt");

        rdBtnChuyenKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdBtnChuyenKhoan);
        rdBtnChuyenKhoan.setText("Chuyển Khoản");

        cbMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTienGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbKhachPhaiTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienKhachDua)
                    .addComponent(lbTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdbtnTienMat)
                                .addGap(18, 18, 18)
                                .addComponent(rdBtnChuyenKhoan))
                            .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lbTienGiam))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbKhachPhaiTra))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rdbtnTienMat)
                    .addComponent(rdBtnChuyenKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lbTienThua))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(btnThanhToan1)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Trả Toàn Bộ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setText("Tổng Tiền :");

        lbTongTien2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongTien2.setForeground(new java.awt.Color(255, 51, 51));
        lbTongTien2.setText("0");

        jLabel32.setText("Mã Giảm Giá :");

        jLabel33.setText("Tiền Giảm :");

        lbTienGiam2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienGiam2.setForeground(new java.awt.Color(255, 51, 51));
        lbTienGiam2.setText("0");

        jLabel35.setText("Khách Phải Trả :");

        lbKhachPhaiTra2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbKhachPhaiTra2.setForeground(new java.awt.Color(0, 51, 255));
        lbKhachPhaiTra2.setText("0");

        jLabel37.setText("Ghi Chú :");

        txtGhiChu1.setColumns(1);
        txtGhiChu1.setRows(5);
        jScrollPane5.setViewportView(txtGhiChu1);

        btnThanhToan2.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan2.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan2.setText("Thanh Toán");

        jLabel38.setText("Trả Trước Tối Thiểu :");

        jLabel39.setText("Số Tiền Trả Trước :");

        jLabel40.setText("Còn Nợ :");

        jLabel41.setText("Lãi Suất :");

        jLabel42.setText("Kì Hạn :");

        jLabel43.setText("Phải trả hàng tháng :");

        lbTraTruocToiThieu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTraTruocToiThieu.setForeground(new java.awt.Color(0, 51, 255));
        lbTraTruocToiThieu.setText("0");

        lbConNo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbConNo.setForeground(new java.awt.Color(0, 51, 255));
        lbConNo.setText("0");

        lbTraHangThang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTraHangThang.setForeground(new java.awt.Color(0, 51, 255));
        lbTraHangThang.setText("0");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel47.setText("%");

        txtTienTraTruoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTienTraTruoc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienTraTruocCaretUpdate(evt);
            }
        });

        cbKyHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4 tháng", "6 tháng", "8 tháng", "10 tháng", "12 tháng" }));
        cbKyHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKyHanActionPerformed(evt);
            }
        });

        cbMaGiamGia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaGiamGia2ActionPerformed(evt);
            }
        });

        txtLaiSuat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtLaiSuat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLaiSuatCaretUpdate(evt);
            }
        });

        jLabel44.setText("Tổng nợ:");

        lbTongNo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongNo.setForeground(new java.awt.Color(0, 51, 255));
        lbTongNo.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lbTongNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtLaiSuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbMaGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbKyHan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbConNo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbTraHangThang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbTienGiam2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbKhachPhaiTra2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbTraTruocToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 17, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTongTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lbTongTien2))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbMaGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lbTienGiam2))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbKhachPhaiTra2))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lbTraTruocToiThieu))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lbConNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lbTongNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbKyHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(lbTraHangThang))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnThanhToan2)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Trả Góp", jPanel3);

        btnTaoDonHang.setBackground(new java.awt.Color(47, 85, 212));
        btnTaoDonHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTaoDonHang.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnTaoDonHang.setText("Tạo Đơn");
        btnTaoDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Jpanel20Layout = new javax.swing.GroupLayout(Jpanel20);
        Jpanel20.setLayout(Jpanel20Layout);
        Jpanel20Layout.setHorizontalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(111, 111, 111))
                    .addGroup(Jpanel20Layout.createSequentialGroup()
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel20Layout.createSequentialGroup()
                                .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(Jpanel20Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29))
                            .addGroup(Jpanel20Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Jpanel20Layout.createSequentialGroup()
                                        .addComponent(btnTaoDonHang)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(Jpanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(103, 103, 103)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2)
                        .addContainerGap())))
        );
        Jpanel20Layout.setVerticalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbKhachHang)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane2)
                .addGap(15, 15, 15))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 85, 212));
        jLabel2.setText("BÁN HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jpanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jpanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonHangActionPerformed
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(soDon + 1));
    }//GEN-LAST:event_btnTaoDonHangActionPerformed

    private void btnXoaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDonHangActionPerformed
        int indexDon = jTabbedPane1.getSelectedIndex();
        //sẽ không thể xóa đơn duy nhất (Phải để lại ít nhất 1 đơn)
        if (jTabbedPane1.getTabCount() > 1) {
            //System.out.println(jTabbedPane1.getTabCount());
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa đơn hàng chờ này không ?",
                    "Xóa Đơn Hàng Chờ?", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                jTabbedPane1.remove(indexDon);
            }
        }
    }//GEN-LAST:event_btnXoaDonHangActionPerformed

    private void btnSapXepGiaTangDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaTangDanActionPerformed

    }//GEN-LAST:event_btnSapXepGiaTangDanActionPerformed

    private void btnSapXepGiaGiamDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaGiamDanActionPerformed

    }//GEN-LAST:event_btnSapXepGiaGiamDanActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ThemKhachHang.setSize(610, 350);
        ThemKhachHang.setResizable(false);
        ThemKhachHang.setLocationRelativeTo(null);
        ThemKhachHang.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setDefault();
        KhachHang kh = getData();
        if (kiemTra(0, txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(this, khachHangService.add(kh));

            khachHangResponseList = khachHangService.getAll();
            KhachHangResponse s = khachHangService.getKhachHangById(kh.getId());
            khachHangService.updateKhoiPhuc(s, chkTrangThai.isSelected() ? 1 : 0);
            showKhachHangCombobox();
            ThemKhachHang.dispose();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    // my job
    private void tbDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDienThoaiMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tbDienThoai.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < tbDienThoai.getRowCount()) {
                tbDienThoai.setRowSelectionInterval(row, row);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbDienThoaiMouseClicked

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        System.out.println("Xem chi tiết");
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        int clickedRowInDienThoaiTable = tbDienThoai.getSelectedRow();
        if (clickedRowInDienThoaiTable < 0) {
            return;
        }
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(clickedRowInDienThoaiTable);
        List<ImeiResponse> imeiResponses = imeiService.getAllDienThoaiId(dienThoaiResponse.getId());

        cbImeiInDialog.removeAllItems();
        imeiResponses.forEach(i -> cbImeiInDialog.addItem(i.getImei()));

        imeiDialog.setSize(450, 150);
        imeiDialog.setResizable(false);
        imeiDialog.setLocationRelativeTo(null);
        imeiDialog.setVisible(true);
    }//GEN-LAST:event_menuItem2ActionPerformed

    private void btnOkImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkImeiActionPerformed
        String selectedImei = (String) cbImeiInDialog.getSelectedItem();
        imeiDialog.setVisible(false);

        int clickedRowInDienThoaiTable = tbDienThoai.getSelectedRow();
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(clickedRowInDienThoaiTable);
        dienThoaiResponse.setSoLuong(dienThoaiResponse.getSoLuong() - 1);

        HoaDonChiTietResponse hoaDonChiTietResponse = new HoaDonChiTietResponse();
        hoaDonChiTietResponse.setImei(selectedImei);
        hoaDonChiTietResponse.setTenDienThoai(dienThoaiResponse.getTenDT());
        hoaDonChiTietResponse.setDonGia(dienThoaiResponse.getGiaBan());
        hoaDonChiTietResponseList.add(hoaDonChiTietResponse);

        jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        List<HoaDonChiTietResponse> list = jDonHang.getHoaDonChiTiets();
        list.add(hoaDonChiTietResponse);
        jDonHang.load();

        showHoaDonInfo1();
        showHoaDonInfo2();
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_btnOkImeiActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        jTabbedPane1.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = jTabbedPane1.getSelectedIndex();
                jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
                List<HoaDonChiTietResponse> currentList = jDonHang.getHoaDonChiTiets();
                jDonHang.load();

                hoaDonChiTietResponseList = new ArrayList<>();
                hoaDonChiTietResponseList.addAll(currentList);

                showHoaDonInfo1();
                showHoaDonInfo2();
            }
        });
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        // tab 1
        // Nhập tiền khách đưa -> 1. Tiền thừa

        String tienKhachDuaStr = txtTienKhachDua.getText();
        if (!tienKhachDuaStr.equals("")) {
            long tienKhachDua = Long.valueOf(txtTienKhachDua.getText());
            long tienThua = tienKhachDua - Long.valueOf(lbKhachPhaiTra.getText().replaceAll(",", ""));
            lbTienThua.setText(numberFormat.format(tienThua));
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void cbMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaGiamGiaActionPerformed
        // tab 1
        // Combo box Phiếu GG -> 1. Tiền giảm -> 2. Khách phải trả -> 3. Tiền thừa

        String clickPhieu = (String) cbMaGiamGia.getSelectedItem();
        if (clickPhieu != null) {
            String maPhieu = clickPhieu.split(" - ")[0];
            PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
            float giaTri = phieuGiamGia.getPhieuGiamGiaChiTiet().getGiaTri();

            // 1. Tiền giảm
            long tongTien = Long.valueOf(lbTongTien.getText().replaceAll(",", ""));
            float tienGiam = Math.round(Float.valueOf(tongTien) * giaTri / 100);
            lbTienGiam.setText(numberFormat.format(tienGiam));

            // làm tròn để tiền không bị lẻ
            String tienGiamStr = lbTienGiam.getText().replaceAll(",", "");
            tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
            tienGiam = Float.valueOf(tienGiamStr);
            lbTienGiam.setText(numberFormat.format(tienGiam));

            // 2. Tiền khách phải trả
            long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam.getText().replaceAll(",", ""));
            lbKhachPhaiTra.setText(numberFormat.format(khachPhaiTra));

            // 3. Tiền thừa
            String tienKhachDuaStr = txtTienKhachDua.getText();
            if (!tienKhachDuaStr.isBlank()) {
                long tienKhachDua = Long.valueOf(tienKhachDuaStr);
                long tienThua = tienKhachDua - khachPhaiTra;
                lbTienThua.setText(numberFormat.format(tienThua));
            }
        }
    }//GEN-LAST:event_cbMaGiamGiaActionPerformed

    private void cbMaGiamGia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaGiamGia2ActionPerformed
        // tab 2
        // Combo box Phiếu GG -> 1. Tiền giảm -> 2. Khách phải trả -> 3. Tiền trả trước tối thiểu

        String clickPhieu = (String) cbMaGiamGia2.getSelectedItem();
        if (clickPhieu != null) {
            String maPhieu = clickPhieu.split(" - ")[0];
            PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
            float giaTri = phieuGiamGia.getPhieuGiamGiaChiTiet().getGiaTri();

            // 1. Tiền giảm
            long tongTien = Long.valueOf(lbTongTien2.getText().replaceAll(",", ""));
            float tienGiam = Math.round(Float.valueOf(tongTien) * giaTri / 100);
            lbTienGiam2.setText(numberFormat.format(tienGiam));

            // làm tròn để tiền không bị lẻ
            String tienGiamStr = lbTienGiam2.getText().replaceAll(",", "");
            tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
            tienGiam = Float.valueOf(tienGiamStr);
            lbTienGiam2.setText(numberFormat.format(tienGiam));

            // 2. Tiền khách phải trả
            long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam2.getText().replaceAll(",", ""));
            lbKhachPhaiTra2.setText(numberFormat.format(khachPhaiTra));

            // 3. Tiền trả trước tối thiểu
            long traTruocToiThieu = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) / 2;
            lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));

            // làm tròn tiền trả trước tối thiểu để tiền không bị lẻ
            String traTruocToiThieuStr = lbTraTruocToiThieu.getText().replaceAll(",", "");
            traTruocToiThieuStr = traTruocToiThieuStr.substring(0, traTruocToiThieuStr.length() - 3).concat("000");
            traTruocToiThieu = Long.valueOf(traTruocToiThieu);
            lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));
        }
    }//GEN-LAST:event_cbMaGiamGia2ActionPerformed

    private void txtTienTraTruocCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienTraTruocCaretUpdate
        // tab 2
        // txt tiền trả trước -> 1. còn nợ

        String tienTraTruocStr = txtTienTraTruoc.getText();
        if (!tienTraTruocStr.equals("")) {
            long tienTraTruoc = Long.valueOf(tienTraTruocStr);
            long conNo = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) - tienTraTruoc;
            lbConNo.setText(numberFormat.format(conNo));
        }
    }//GEN-LAST:event_txtTienTraTruocCaretUpdate

    private void txtLaiSuatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLaiSuatCaretUpdate
        //  tab 2
        // txt lãi suất -> 1. tổng nợ

        // 1. Tổng nợ
        String laiSuatStr = txtLaiSuat.getText().trim();
        if (!laiSuatStr.equals("")) {
            float laiSuat = Float.valueOf(laiSuatStr);
            long conNo = Long.valueOf(lbConNo.getText().replaceAll(",", ""));
            long tongNo = Math.round(Float.valueOf(conNo) * laiSuat / 100 + Float.valueOf(conNo));
            lbTongNo.setText(numberFormat.format(tongNo));

            // làm tròn tổng nợ để tiền không bị lẻ
            String tongNoStr = lbTongNo.getText().trim().replaceAll(",", "");
            tongNoStr = tongNoStr.substring(0, tongNoStr.length() - 3).concat("000");
            tongNo = Long.valueOf(tongNoStr);
            lbTongNo.setText(numberFormat.format(tongNo));
        }
    }//GEN-LAST:event_txtLaiSuatCaretUpdate

    private void cbKyHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKyHanActionPerformed
        // tab 2
        // combo box kỳ hạn -> 1. phải trả hàng tháng

        try {
            int kyHan = Integer.valueOf(String.valueOf(cbKyHan.getSelectedItem()).substring(0, 2).trim());
            long tongNo = Long.valueOf(lbTongNo.getText().replaceAll(",", ""));
            long phaiTraHangThang = tongNo / kyHan;
            lbTraHangThang.setText(numberFormat.format(phaiTraHangThang));

            // làm tròn tiền trả hàng tháng để không bị lẻ
            String phaiTraHangThangStr = lbTraHangThang.getText().trim().replaceAll(",", "");
            phaiTraHangThangStr = phaiTraHangThangStr.substring(0, phaiTraHangThangStr.length() - 3).concat("000");

            phaiTraHangThang = Long.valueOf(phaiTraHangThangStr);
            lbTraHangThang.setText(numberFormat.format(phaiTraHangThang));
        } catch (Exception e) {
            System.out.println("Exception khi ấn cb kỳ hạn!");
        }
    }//GEN-LAST:event_cbKyHanActionPerformed

    private void showHoaDonInfo1() {
        // Tab 1
        // Thêm ĐT/ Xóa ĐT -> 1. Tổng tiền -> 2. Combo box phiếu GG -> 3. Tiền giảm -> 4. Tiền khách phải trả

        // 1. Tổng tiền
        long tongTien = 0L;
        for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
            tongTien += hoaDonChiTietResponseList.get(i).getDonGia();
        }
        lbTongTien.setText(numberFormat.format(tongTien));

        // 2. Combo box Phiếu giảm giá
        List<PhieuGiamGiaResponse> phieuGiamGiaResponseList = phieuGiamGiaService.getAllForView(tongTien);
        cbMaGiamGia.removeAllItems();
        phieuGiamGiaResponseList.forEach(p -> cbMaGiamGia.addItem(p.getMaPhieu() + " - " + p.getGiaTri()));

        // 3. Tiền giảm
        String clickPhieu = (String) cbMaGiamGia.getSelectedItem();
        if (clickPhieu != null) {
            String maPhieu = clickPhieu.split(" - ")[0];
            PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
            float giaTri = phieuGiamGia.getPhieuGiamGiaChiTiet().getGiaTri();

            float tienGiam = Math.round(Float.valueOf(tongTien) * giaTri / 100);
            lbTienGiam.setText(numberFormat.format(tienGiam));

            // làm tròn để tiền không bị lẻ
            String tienGiamStr = lbTienGiam.getText().replaceAll(",", "");
            tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
            tienGiam = Float.valueOf(tienGiamStr);
            lbTienGiam.setText(numberFormat.format(tienGiam));

            // 4. Tiền khách phải trả
            long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam.getText().replaceAll(",", ""));
            lbKhachPhaiTra.setText(numberFormat.format(khachPhaiTra));
        }
    }

    private void showHoaDonInfo2() {
        // Tab 2
        // Thêm ĐT/ Xóa ĐT -> 1. Tổng tiền -> 2. Combo box phiếu GG -> 3. Tiền giảm -> 4. Tiền khách phải trả -> 5.Trả trước tối thiều

        // 1. Tổng tiền
        long tongTien2 = 0L;
        for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
            tongTien2 += hoaDonChiTietResponseList.get(i).getDonGia();
        }
        lbTongTien2.setText(numberFormat.format(tongTien2));

        // 2. Combo box phiếu GG
        List<PhieuGiamGiaResponse> phieuGiamGiaResponseList = phieuGiamGiaService.getAllForView(tongTien2);
        cbMaGiamGia2.removeAllItems();
        phieuGiamGiaResponseList.forEach(p -> cbMaGiamGia2.addItem(p.getMaPhieu() + " - " + p.getGiaTri()));

        // 3. Tiền giảm
        String clickPhieu = (String) cbMaGiamGia2.getSelectedItem();
        if (clickPhieu != null) {
            String maPhieu = clickPhieu.split(" - ")[0];
            PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
            float giaTri = phieuGiamGia.getPhieuGiamGiaChiTiet().getGiaTri();

            float tienGiam = Math.round(Float.valueOf(tongTien2) * giaTri / 100);
            lbTienGiam2.setText(numberFormat.format(tienGiam));

            // làm tròn tiền giảm để tiền không bị lẻ
            String tienGiamStr = lbTienGiam2.getText().replaceAll(",", "");
            tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
            tienGiam = Float.valueOf(tienGiamStr);
            lbTienGiam2.setText(numberFormat.format(tienGiam));

            // 4. Tiền khách phải trả
            long khachPhaiTra2 = tongTien2 - Long.valueOf(lbTienGiam2.getText().replaceAll(",", ""));
            lbKhachPhaiTra2.setText(numberFormat.format(khachPhaiTra2));
        }

        // 5. Trả trước tối thiểu
        long traTruocToiThieu = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) / 2;
        lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));

        // làm tròn tiền trả trước tối thiểu để không bị lẻ
        String traTruocToiThieuStr = lbTraTruocToiThieu.getText().replaceAll(",", "");
        traTruocToiThieuStr = traTruocToiThieuStr.substring(0, traTruocToiThieuStr.length() - 3).concat("000");
        traTruocToiThieu = Long.valueOf(traTruocToiThieu);
        lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel20;
    private javax.swing.JDialog ThemKhachHang;
    private javax.swing.JButton btnOkImei;
    private javax.swing.JButton btnSapXepGiaGiamDan;
    private javax.swing.JButton btnSapXepGiaTangDan;
    private javax.swing.JButton btnTaoDonHang;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JButton btnThanhToan2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaDonHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbImeiInDialog;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JComboBox<String> cbKyHan;
    private javax.swing.JComboBox<String> cbMaGiamGia;
    private javax.swing.JComboBox<String> cbMaGiamGia2;
    private javax.swing.JRadioButton cboNam;
    private javax.swing.JRadioButton cboNu;
    private javax.swing.JCheckBox chkTrangThai;
    private javax.swing.JDialog imeiDialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser jdateNgaySinh;
    private javax.swing.JPanel jplGioHang;
    private javax.swing.JPanel jpnFormBanHang;
    private javax.swing.JLabel lbConNo;
    private javax.swing.JLabel lbKhachPhaiTra;
    private javax.swing.JLabel lbKhachPhaiTra2;
    private javax.swing.JLabel lbTienGiam;
    private javax.swing.JLabel lbTienGiam2;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTongNo;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongTien2;
    private javax.swing.JLabel lbTraHangThang;
    private javax.swing.JLabel lbTraTruocToiThieu;
    private javax.swing.JMenuItem menuItem1;
    private javax.swing.JMenuItem menuItem2;
    private javax.swing.JRadioButton rdBtnChuyenKhoan;
    private javax.swing.JRadioButton rdbtnTienMat;
    private javax.swing.JTable tbDienThoai;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtGhiChu1;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLaiSuat;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraTruoc;
    // End of variables declaration//GEN-END:variables
}
