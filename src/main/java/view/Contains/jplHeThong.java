package view.Contains;

import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import service.QuanLyNhanVienService;
import service.impl.QuanLyNhanVienServiceImpl;
import viewmodel.NhanVienResponse;

public class jplHeThong extends javax.swing.JPanel {

    private List<NhanVienResponse> list1;
    private List<NhanVienResponse> list2;
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    private DefaultComboBoxModel dcm1;
    private DefaultComboBoxModel dcm2;
    private QuanLyNhanVienService service;
    private int rowSelected;
    private String strHinhAnh = null;
    
    public jplHeThong() {
        initComponents();
        
        list1 = new ArrayList<>();
        dtm1= (DefaultTableModel) tbLamViec.getModel();
        dtm2 = (DefaultTableModel) tbNghiViec.getModel();
        dcm1 = (DefaultComboBoxModel) cbLocGioiTinh.getModel();
        dcm2 = (DefaultComboBoxModel) cbLocChucVu.getModel();
        service = new QuanLyNhanVienServiceImpl();
        
        showDataCboLocChucVu();
        showDataCboLocGioiTinh();
        
        list1 = service.getAllLam();
        showDataTblLam(list1);
        
        list2 = service.getAllNghi();
        showDataTblNghi(list2);
        
        
    }

    private void showDataTblLam(List<NhanVienResponse> lists) {
        dtm1.setRowCount(0);
        for (NhanVienResponse nv : lists) {
            dtm1.addRow(new Object[]{nv.getHoTen(), nv.isGioiTinh()? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(), 
                                    nv.getEmail(),nv.isChucVu()? "Nhân Viên" : "Quản lý", nv.isTrangThai()? "Đang làm việc" : "Nghỉ việc",
                                    nv.getHinhAnh(), nv.getTaiKhoan(), nv.getMatKhau()});
        }
    }
    
    private void showDataTblNghi(List<NhanVienResponse> lists) {
        dtm2.setRowCount(0);
        for (NhanVienResponse nv : lists) {
            dtm2.addRow(new Object[]{nv.getHoTen(), nv.isGioiTinh()? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(), 
                                    nv.getEmail(),nv.isChucVu()? "Nhân Viên" : "Quản lý", nv.isTrangThai()? "Đang làm việc" : "Nghỉ việc",
                                    nv.getHinhAnh(), nv.getTaiKhoan(), nv.getMatKhau()});
        }
    }
    
    private void showDataCboLocGioiTinh() {
        String[] gt = {"", "Nam", "Nữ"};
        for (String string : gt) {
            dcm1.addElement(string);
        }
    }
    
    private void showDataCboLocChucVu() {
        String[] gt = {"", "Nhân viên", "Quản lý"};
        for (String string : gt) {
            dcm2.addElement(string);
        }
    }
    
    private void showDetail(int rowSelected, List<NhanVienResponse> lists) {
        NhanVienResponse nv = lists.get(rowSelected);
        txtHoTen.setText(nv.getHoTen());
        if(nv.isGioiTinh()) {
            rdBtnNam.setSelected(true);
        } else {
            rdBtnNu.setSelected(true);
        }
        txtSdt.setText(nv.getSdt());
        txtNgaySinh.setDate(Date.from(nv.getNgaySinh().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        if(nv.isChucVu()) {
            rdBtnNhanVien.setSelected(true);
        } else {
            rdBtnQuanLy.setSelected(true);
        }
        if(nv.isTrangThai()) {
            rdBtnLamViec.setSelected(true);
        } else {
            rdBtnNghiViec.setSelected(true);
        }
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/" + nv.getHinhAnh()));    
        Image newImage = icon.getImage().getScaledInstance(148, 184, Image.SCALE_SMOOTH);
        lbAnh.setIcon(new ImageIcon(newImage));
        txtTaiKhoan.setText(nv.getTaiKhoan());
        txtMatKhau.setText(nv.getMatKhau());
    }
    
    private void clear() {
        txtHoTen.setText("");
        rdBtnNam.setSelected(true);
        txtSdt.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtEmail.setText("");
        rdBtnNhanVien.setSelected(true);
        rdBtnLamViec.setSelected(true);
        lbAnh.setIcon(null);
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
    }
    
    private boolean validated() {
        if(txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền họ tên");
            return false;
        }
        if(txtHoTen.getText().trim().matches(".*[0-9].*")) {
            JOptionPane.showMessageDialog(this, "Họ tên chỉ chứa chữ");
            return false;
        }
        if(txtHoTen.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ tên không quá 50 kí tự");
            return false;
        }
        if(txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền số điện thoại");
            return false;
        }
        if(!txtSdt.getText().trim().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Sđt chỉ chứa số và có 10 chữ số");
            return false;
        }
        if(txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Phải điền ngày sinh");
            return false;
        }
        if(txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền địa chỉ");
            return false;
        }
        if(txtDiaChi.getText().trim().length() > 128) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không quá 128 kí tự");
            return false;
        }
        if(txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền email");
            return false;
        }
        String regexEmail = "\\w+@\\w+\\.+\\w+";
        if(txtEmail.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Email không quá 50 kí tự");
            return false;
        }
        if(strHinhAnh == null) {
            JOptionPane.showMessageDialog(this, "Phải chọn ảnh");
            return false;
        }
        if(txtTaiKhoan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền tài khoản");
            return false;
        }
        if(txtTaiKhoan.getText().trim().length() > 15) {
            JOptionPane.showMessageDialog(this, "Tài khoản không quá 15 kí tự");
            return false;
        }
        if(txtMatKhau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền mật khẩu");
            return false;
        }
        if(txtMatKhau.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không quá 50 kí tự");
            return false;
        }
        
        return true;
    }
    
    private NhanVienResponse readForm() {
        Date date1 = txtNgaySinh.getDate();
        LocalDate ld1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new NhanVienResponse(txtHoTen.getText(), rdBtnNam.isSelected(), txtSdt.getText(), ld1, txtDiaChi.getText(),txtEmail.getText(), 
                                    rdBtnNhanVien.isSelected(), rdBtnLamViec.isSelected(), strHinhAnh, txtTaiKhoan.getText(), txtMatKhau.getText());
    }
    
    private void chooseImg() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Tệp JPG", "jpg");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            strHinhAnh = selectedFile.getName(); 
            ImageIcon icon = new ImageIcon(selectedFile.getPath());
            Image newImage = icon.getImage().getScaledInstance(148, 184, Image.SCALE_SMOOTH);
            lbAnh.setIcon(new ImageIcon(newImage));
        }
        
//        JFileChooser chooser = new JFileChooser("D:\\NetBeansProjects\\DuAn1\\DuAn1\\src\\main\\resources\\Icon");
//        JFileChooser chooser = new JFileChooser();
//        FileFilter filter = new FileNameExtensionFilter("Tệp JPG", "jpg");
//        chooser.setFileFilter(filter);
//        int result = chooser.showOpenDialog(null);
//        if(result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            strHinhAnh = selectedFile.getName();
//            File newFile = new File("D:\\NetBeansProjects\\DuAn1\\DuAn1\\src\\main\\resources\\Icon\\" + strHinhAnh);
//            selectedFile.renameTo(newFile);  
//            ImageIcon icon = new ImageIcon(newFile.getPath());
//            Image newImage = icon.getImage().getScaledInstance(148, 184, Image.SCALE_SMOOTH);
//            lbAnh.setIcon(new ImageIcon(newImage));
//        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpGioiTinh = new javax.swing.ButtonGroup();
        grpChucVu = new javax.swing.ButtonGroup();
        grpTrangThai = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdBtnNam = new javax.swing.JRadioButton();
        rdBtnNu = new javax.swing.JRadioButton();
        txtSdt = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        rdBtnLamViec = new javax.swing.JRadioButton();
        rdBtnNghiViec = new javax.swing.JRadioButton();
        rdBtnQuanLy = new javax.swing.JRadioButton();
        rdBtnNhanVien = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        lbAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLamViec = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNghiViec = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        cbLocGioiTinh = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbLocChucVu = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        jLabel2.setText("Họ tên");

        grpGioiTinh.add(rdBtnNam);
        rdBtnNam.setSelected(true);
        rdBtnNam.setText("Nam");

        grpGioiTinh.add(rdBtnNu);
        rdBtnNu.setText("Nữ");

        jLabel6.setText("Địa chỉ");

        jLabel5.setText("Ngày sinh");

        jLabel4.setText("Sđt");

        jLabel3.setText("Giới tính");

        jLabel7.setText("Email");

        jLabel8.setText("Chức vụ");

        jLabel9.setText("Trạng thái");

        jLabel10.setText("Tài khoản");

        jLabel11.setText("Mật khẩu");

        grpTrangThai.add(rdBtnLamViec);
        rdBtnLamViec.setSelected(true);
        rdBtnLamViec.setText("Đang làm việc");

        grpTrangThai.add(rdBtnNghiViec);
        rdBtnNghiViec.setText("Nghỉ việc");

        grpChucVu.add(rdBtnQuanLy);
        rdBtnQuanLy.setText("Quản lý");

        grpChucVu.add(rdBtnNhanVien);
        rdBtnNhanVien.setSelected(true);
        rdBtnNhanVien.setText("Nhân viên");

        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoTen)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdBtnNam)
                        .addGap(37, 37, 37)
                        .addComponent(rdBtnNu))
                    .addComponent(txtSdt)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdBtnLamViec)
                                .addComponent(rdBtnNhanVien))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdBtnQuanLy)
                                .addComponent(rdBtnNghiViec)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rdBtnNam)
                            .addComponent(rdBtnNu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdBtnNhanVien)
                            .addComponent(rdBtnQuanLy)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdBtnLamViec)
                            .addComponent(rdBtnNghiViec)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addContainerGap())
        );

        tbLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ tên", "Giới tính", "Sđt", "Ngày sinh", "Địa chỉ", "Email", "Chức vụ", "Trạng thái", "Tài khoản", "Mật khẩu"
            }
        ));
        tbLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLamViecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbLamViec);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Đang làm việc", jPanel5);

        tbNghiViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ tên", "Giới tính", "Sđt", "Ngày sinh", "Địa chỉ", "Email", "Chức vụ", "Trạng thái", "Tài khoản", "Mật khẩu"
            }
        ));
        tbNghiViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNghiViecMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbNghiViec);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        jTabbedPane2.addTab("Nghỉ việc", jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        cbLocGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocGioiTinhActionPerformed(evt);
            }
        });

        jLabel12.setText("Giới tính");

        jLabel13.setText("Chức vụ");

        cbLocChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocChucVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbLocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cbLocChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(cbLocChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("QUẢN LÝ NHÂN VIÊN", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("THỐNG KÊ", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(validated()) {
            JOptionPane.showMessageDialog(this, service.add(readForm()));
            list1 = service.getAllLam();
            showDataTblLam(list1);
        
            list2 = service.getAllNghi();
            showDataTblNghi(list2);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tbLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLamViecMouseClicked
        // TODO add your handling code here:
        rowSelected = tbLamViec.getSelectedRow();
        list1 = service.getAllLam();
        showDetail(rowSelected, list1);
    }//GEN-LAST:event_tbLamViecMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        list1 = service.findByNameNVLam(txtTimKiem.getText());
        showDataTblLam(list1);
        list2 = service.findByNameNVNghi(txtTimKiem.getText());
        showDataTblNghi(list2);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tbNghiViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNghiViecMouseClicked
        // TODO add your handling code here:
        rowSelected = tbNghiViec.getSelectedRow();
        list2 = service.getAllNghi();
        showDetail(rowSelected, list2);
    }//GEN-LAST:event_tbNghiViecMouseClicked

    private void cbLocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocGioiTinhActionPerformed
        // TODO add your handling code here:
        if(cbLocGioiTinh.getSelectedIndex() == 0) {
            list1 = service.getAllLam();
            showDataTblLam(list1);
            list2 = service.getAllNghi();
            showDataTblNghi(list2);
            return;
        }
        boolean gt = false;
        if(cbLocGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
            gt = true;
        }
        list1 = service.findByGioiTinhNVLam(gt);
        showDataTblLam(list1);
        list2 = service.findByGioiTinhNVNghi(gt);
        showDataTblNghi(list2);
    }//GEN-LAST:event_cbLocGioiTinhActionPerformed

    private void cbLocChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocChucVuActionPerformed
        // TODO add your handling code here:
        if(cbLocChucVu.getSelectedIndex() == 0) {
            list1 = service.getAllLam();
            showDataTblLam(list1);
            list2 = service.getAllNghi();
            showDataTblNghi(list2);
            return;
        }
        boolean cv = false;
        if(cbLocChucVu.getSelectedItem().toString().equals("Nhân viên")) {
            cv = true;
        }
        list1 = service.findByChucVuNVLam(cv);
        showDataTblLam(list1);
        list2 = service.findByChucVuNVNghi(cv);
        showDataTblNghi(list2);
    }//GEN-LAST:event_cbLocChucVuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        // TODO add your handling code here:
        chooseImg();
    }//GEN-LAST:event_lbAnhMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        rowSelected = tbLamViec.getSelectedRow();
        if(rowSelected >= 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Confirm", "Bạn có chắc chắn không?", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice == 0) {
                JOptionPane.showMessageDialog(this, service.update(readForm()));
                
                list1 = service.getAllLam();
                showDataTblLam(list1);
        
                list2 = service.getAllNghi();
                showDataTblNghi(list2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Phải chọn nhân viên đang làm việc muốn sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        rowSelected = tbLamViec.getSelectedRow();
        if(rowSelected >= 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Confirm", "Bạn có chắc chắn không?", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice == 0) {
                JOptionPane.showMessageDialog(this, service.delete(list1.get(rowSelected).getId()));
                
                list1 = service.getAllLam();
                showDataTblLam(list1);
        
                list2 = service.getAllNghi();
                showDataTblNghi(list2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Phải chọn nhân viên đang làm việc muốn xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLocChucVu;
    private javax.swing.JComboBox<String> cbLocGioiTinh;
    private javax.swing.ButtonGroup grpChucVu;
    private javax.swing.ButtonGroup grpGioiTinh;
    private javax.swing.ButtonGroup grpTrangThai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JRadioButton rdBtnLamViec;
    private javax.swing.JRadioButton rdBtnNam;
    private javax.swing.JRadioButton rdBtnNghiViec;
    private javax.swing.JRadioButton rdBtnNhanVien;
    private javax.swing.JRadioButton rdBtnNu;
    private javax.swing.JRadioButton rdBtnQuanLy;
    private javax.swing.JTable tbLamViec;
    private javax.swing.JTable tbNghiViec;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
