package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.KhachHang;
import model.TheTichDiem;
import service.impl.KhachHangServiceImpl;
import viewmodel.KhachHangResponse;
import service.KhachHangService;

public class jplKhachHang extends javax.swing.JPanel {

    private List<KhachHangResponse> listKhachHang = new ArrayList<>();
    private List<KhachHangResponse> listTheTichDiem = new ArrayList<>();
    private KhachHangService service = new KhachHangServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmDaXoa = new DefaultTableModel();
    private DefaultTableModel dtmTichDiem = new DefaultTableModel();

    public jplKhachHang() {
        initComponents();

        JTableHeader Theader = tblKhachHang.getTableHeader();
        JTableHeader TheaderXoa = tblKhachHangDaXoa.getTableHeader();
        JTableHeader TheaderTichDiem = tblTheTichDiem.getTableHeader();
        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);

        TheaderXoa.setFont(new Font("tahoma", Font.BOLD, 15));
        TheaderXoa.setBackground(new Color(47, 85, 212));
        TheaderXoa.setForeground(Color.white);

        TheaderTichDiem.setFont(new Font("tahoma", Font.BOLD, 15));
        TheaderTichDiem.setBackground(new Color(47, 85, 212));
        TheaderTichDiem.setForeground(Color.white);

        dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtmDaXoa = (DefaultTableModel) tblKhachHangDaXoa.getModel();
        dtmTichDiem = (DefaultTableModel) tblTheTichDiem.getModel();
        listKhachHang = service.getAll();
        showData(listKhachHang);

        showDataRemove(listKhachHang);
        listTheTichDiem = service.getAllTheTichDiem();
        showDataTichDiem(listTheTichDiem);

    }

    private void showData(List<KhachHangResponse> list) {
        dtm.setRowCount(0);
        for (KhachHangResponse s : list) {
            if (s.getTrangThai() == 1) {
                dtm.addRow(s.toDataRow());
            }
        }
    }

    private void showDataRemove(List<KhachHangResponse> list) {
        dtmDaXoa.setRowCount(0);
        for (KhachHangResponse s : list) {
            if (s.getTrangThai() == 0) {
                dtmDaXoa.addRow(s.toDataRow());
            }
        }
    }

    private void showDataTichDiem(List<KhachHangResponse> list) {
        dtmTichDiem.setRowCount(0);
        for (KhachHangResponse s : list) {
            dtmTichDiem.addRow(s.toDataRowTheTichDiem());
        }
    }

    public void showDataToText(int rowIndex) {
        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        KhachHangResponse kh = service.getKhachHangById(id);

        txtHoTen.setText(kh.getHoTen());
        txtEmail.setText(kh.getEmail());
        txtSdt.setText(kh.getSdt());

        cldNgaySinh.setDate(Date.from(kh.getNgaySinh().atStartOfDay().toInstant(ZoneOffset.UTC)));
        txtDiaChi.setText(kh.getDiaChi());
        txtMathe.setText(kh.getMaThe());
        if (kh.getTrangThai() == 1) {
            chkTrangThai.setSelected(true);
        } else {
            chkTrangThai.setSelected(false);
        }
    }

    public KhachHang getData() {
        TheTichDiem theTichDiem = new TheTichDiem();
        theTichDiem.setMaThe(txtMathe.getText().trim());
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
        LocalDate localDate = cldNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        khachHang.setNgaySinh(localDate);
        khachHang.setDiaChi(txtDiaChi.getText().trim());
        if (chkTrangThai.isSelected()) {
            khachHang.setTrangThai(1);
        } else {
            khachHang.setTrangThai(0);
        }

        khachHang.setTheTichDiem(theTichDiem);
        return khachHang;
    }

    private Boolean kiemTra(int id, String email, String maThe) {

        StringBuilder sb = new StringBuilder();
        KhachHangResponse kh = service.getKhachHangByEmail(email);
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
                for (KhachHangResponse s : listKhachHang) {
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
        KhachHangResponse khId = service.getKhachHangByMaThe(maThe);
        if (txtMathe.getText().isBlank()) {
            sb.append("Không để trống thẻ tích điểm");
        } else if (khId != null) {
            if (id == 0) {
                String str = "Mã Thẻ đã tồn tại\n";

                if (khId.getTrangThai() == 0) {
                    str = str + " trong phần đã xóa\n";
                }
                sb.append(str);
            } else if (id > 0) {

                String str = "";
                for (KhachHangResponse s : listKhachHang) {
                    if (s.getId() != id) {

                        if (txtMathe.getText().trim().toLowerCase().equals(s.getMaThe().toLowerCase()) == true) {

                            str = "Mã Thẻ đã tồn tại\n";
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
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangDaXoa = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        txtSearchDaXoa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSortDaXoaAZ = new javax.swing.JButton();
        btnSortDaXoaZA = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearchTichDiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTheTichDiem = new javax.swing.JTable();
        ThemKhachHang = new javax.swing.JPanel();
        jplThongTin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboNam = new javax.swing.JRadioButton();
        cboNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMathe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        chkTrangThai = new javax.swing.JCheckBox();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cldNgaySinh = new com.toedter.calendar.JDateChooser();
        btnTaoMa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "HỌ VÀ TÊN", "EMAIL", "SDT", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "ĐIỂM", "TRẠNG THÁI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(47, 85, 212));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        tblKhachHang.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tblKhachHangCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblKhachHang.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblKhachHang.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblKhachHang.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        jLabel2.setText("TÌM KIẾM SĐT:");

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(47, 85, 212));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-20.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(47, 85, 212));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-reversed-20.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("SẮP XẾP THEO TÊN:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel12)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(81, 81, 81))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("KHÁCH HÀNG", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHangDaXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblKhachHangDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "HỌ VÀ TÊN", "EMAIL", "SDT", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "ĐIỂM", "TRẠNG THÁI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHangDaXoa.setFocusable(false);
        tblKhachHangDaXoa.setGridColor(new java.awt.Color(47, 85, 212));
        tblKhachHangDaXoa.setRowHeight(25);
        tblKhachHangDaXoa.setShowGrid(true);
        tblKhachHangDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangDaXoaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHangDaXoa);
        if (tblKhachHangDaXoa.getColumnModel().getColumnCount() > 0) {
            tblKhachHangDaXoa.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHangDaXoa.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblKhachHangDaXoa.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblKhachHangDaXoa.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblKhachHangDaXoa.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblKhachHangDaXoa.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblKhachHangDaXoa.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblKhachHangDaXoa.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblKhachHangDaXoa.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.setText("Khôi Phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        txtSearchDaXoa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchDaXoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchDaXoaCaretUpdate(evt);
            }
        });

        jLabel13.setText("TÌM KIẾM SĐT:");

        jLabel14.setText("SẮP XẾP THEO TÊN:");

        btnSortDaXoaAZ.setBackground(new java.awt.Color(47, 85, 212));
        btnSortDaXoaAZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-20.png"))); // NOI18N
        btnSortDaXoaAZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSortDaXoaAZMouseClicked(evt);
            }
        });

        btnSortDaXoaZA.setBackground(new java.awt.Color(47, 85, 212));
        btnSortDaXoaZA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-reversed-20.png"))); // NOI18N
        btnSortDaXoaZA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSortDaXoaZAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchDaXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(35, 35, 35)
                        .addComponent(btnSortDaXoaAZ)
                        .addGap(18, 18, 18)
                        .addComponent(btnSortDaXoaZA)
                        .addGap(19, 19, 19)
                        .addComponent(btnKhoiPhuc))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearchDaXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSortDaXoaAZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSortDaXoaZA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ĐÃ XÓA", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("TÌM KIẾM THEO MÃ");

        txtSearchTichDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchTichDiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchTichDiemCaretUpdate(evt);
            }
        });

        tblTheTichDiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblTheTichDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MÃ THẺ", "TÊN KHÁCH HÀNG", "NGÀY KÍCH HOẠT", "SỐ ĐIỂM", "TRẠNG  THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTheTichDiem.setRowHeight(25);
        jScrollPane3.setViewportView(tblTheTichDiem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtSearchTichDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 424, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearchTichDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );

        jTabbedPane1.addTab("QUẢN LÝ TÍCH ĐIỂM", jPanel3);

        ThemKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        ThemKhachHang.setLayout(new javax.swing.BoxLayout(ThemKhachHang, javax.swing.BoxLayout.LINE_AXIS));

        jplThongTin.setBackground(new java.awt.Color(255, 255, 255));
        jplThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel4.setText("HỌ VÀ TÊN:");

        jLabel5.setText("EMAIL:");

        jLabel6.setText("SDT:");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(51, 51, 51));
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel7.setText("GIỚI TÍNH:");

        cboNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cboNam);
        cboNam.setSelected(true);
        cboNam.setText("NAM");

        cboNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cboNu);
        cboNu.setText("NỮ");

        jLabel8.setText("NGÀY SINH:");

        jLabel9.setText("ĐỊA CHỈ:");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel10.setText("THẺ TÍCH ĐIỂM:");

        txtMathe.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtMathe.setForeground(new java.awt.Color(204, 0, 0));
        txtMathe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtMathe.setEnabled(false);

        jLabel11.setText("TRẠNG THÁI:");

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

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/EDIT.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.setBorderPainted(false);
        btnSua.setFocusable(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-remove-30.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusable(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cldNgaySinh.setDateFormatString("yyyy-MM-dd");
        cldNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnTaoMa.setBackground(new java.awt.Color(47, 85, 212));
        btnTaoMa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoMa.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMa.setText("TẠO MÃ");
        btnTaoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplThongTinLayout = new javax.swing.GroupLayout(jplThongTin);
        jplThongTin.setLayout(jplThongTinLayout);
        jplThongTinLayout.setHorizontalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(28, 28, 28)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(68, 68, 68)
                                .addComponent(txtSdt))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(54, 54, 54)
                                .addComponent(txtEmail)))
                        .addGap(78, 78, 78)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(cboNam)
                        .addGap(26, 26, 26)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(cboNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGap(0, 13, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addGap(79, 79, 79)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(chkTrangThai)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtMathe)
                            .addComponent(cldNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(btnTaoMa)
                        .addGap(99, 99, 99))))
        );
        jplThongTinLayout.setVerticalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cldNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTaoMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMathe, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboNam)
                                .addComponent(cboNu)
                                .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua)
                    .addComponent(btnThem)
                    .addComponent(btnXoa))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        ThemKhachHang.add(jplThongTin);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 85, 212));
        jLabel1.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int rowIndex = tblKhachHang.getSelectedRow();
        showDataToText(rowIndex);

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        KhachHang kh = getData();
        if (kiemTra(0, txtEmail.getText().trim(), txtMathe.getText().trim())) {
            JOptionPane.showMessageDialog(this, service.add(kh));

            listKhachHang = service.getAll();
            KhachHangResponse s = service.getKhachHangById(kh.getId());
            service.updateKhoiPhuc(s, chkTrangThai.isSelected() ? 1 : 0);
            showData(listKhachHang);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int rowIndex = tblKhachHang.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa");
            return;
        }

        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        KhachHangResponse kh = service.getKhachHangById(id);

        if (kiemTra(kh.getId(), txtEmail.getText().trim(), txtMathe.getText().trim())) {
            int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa khách hàng này không?", "UPDATE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == 0) {
                KhachHang s = getData();
                JOptionPane.showMessageDialog(this, service.update(new KhachHangResponse(kh.getId(), s.getHoTen(), s.getEmail(), s.getSdt(), s.isGioiTinh(), s.getNgaySinh(), s.getDiaChi(), s.getTrangThai())));
                if (s.getTrangThai() == 0) {
                    service.updateKhoiPhuc(kh, 0);
                }
                listKhachHang = service.getAll();
                showData(listKhachHang);
                showDataRemove(listKhachHang);
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblKhachHangDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangDaXoaMouseClicked

    }//GEN-LAST:event_tblKhachHangDaXoaMouseClicked

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        int rowIndex = tblKhachHangDaXoa.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn khôi phục");
            return;
        }
        int id = (int) tblKhachHangDaXoa.getValueAt(rowIndex, 0);
        KhachHangResponse kh = service.getKhachHangById(id);
        service.updateKhoiPhuc(kh, 1);
        listKhachHang = service.getAll();
        showDataRemove(listKhachHang);
        JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        showData(listKhachHang);

    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        listKhachHang = service.getAll();
        showData(listKhachHang);
        showDataRemove(listKhachHang);
        listTheTichDiem = service.getAllTheTichDiem();
        showDataTichDiem(listTheTichDiem);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowIndex = tblKhachHang.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn xóa");
            return;
        }
        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        System.out.println(id);
        KhachHangResponse kh = service.getKhachHangById(id);
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa khách hàng này không?", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choose == 0) {
            service.updateKhoiPhuc(kh, 0);
            listKhachHang = service.getAll();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            showData(listKhachHang);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        listKhachHang = service.findBySdt(txtSearch.getText().trim(), 1);
        showData(listKhachHang);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tblKhachHangCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblKhachHangCaretPositionChanged

    }//GEN-LAST:event_tblKhachHangCaretPositionChanged

    private void txtSearchDaXoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchDaXoaCaretUpdate
        listKhachHang = service.findBySdt(txtSearchDaXoa.getText().trim(), 0);
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_txtSearchDaXoaCaretUpdate

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listKhachHang = service.sortByName(true, 1);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showData(listKhachHang);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        listKhachHang = service.sortByName(false, 1);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showData(listKhachHang);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSortDaXoaAZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortDaXoaAZMouseClicked
        listKhachHang = service.sortByName(true, 0);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_btnSortDaXoaAZMouseClicked

    private void btnSortDaXoaZAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortDaXoaZAMouseClicked
        listKhachHang = service.sortByName(false, 0);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_btnSortDaXoaZAMouseClicked
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
    private void btnTaoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMaActionPerformed
        String randomNumericString = generateRandomNumericString(STRING_LENGTH);
        txtMathe.setText(randomNumericString);
    }//GEN-LAST:event_btnTaoMaActionPerformed

    private void txtSearchTichDiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchTichDiemCaretUpdate
        if (txtSearchTichDiem.getText().isBlank()) {
            listTheTichDiem = service.getAllTheTichDiem();
            showDataTichDiem(listTheTichDiem);
            return;
        }
        listTheTichDiem = service.findByMa(Integer.parseInt(txtSearchTichDiem.getText().trim()));
        showDataTichDiem(listTheTichDiem);
    }//GEN-LAST:event_txtSearchTichDiemCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThemKhachHang;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnSortDaXoaAZ;
    private javax.swing.JButton btnSortDaXoaZA;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cboNam;
    private javax.swing.JRadioButton cboNu;
    private javax.swing.JCheckBox chkTrangThai;
    private com.toedter.calendar.JDateChooser cldNgaySinh;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jplThongTin;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHangDaXoa;
    private javax.swing.JTable tblTheTichDiem;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMathe;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchDaXoa;
    private javax.swing.JTextField txtSearchTichDiem;
    // End of variables declaration//GEN-END:variables
}
