/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Contains.tragop;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.LichSuTraGop;
import model.PhieuTraGop;
import service.LichSuTraGopService;
import service.PhieuTraGopService;
import service.impl.LichSuTraGopServiceImpl;
import service.impl.PhieuTraGopServiceImpl;
import utility.PhieuTraGopUtil;

/**
 *
 * @author Administrator
 */
public class ViewLichSuTraGop extends javax.swing.JFrame {

    private PhieuTraGopService ptgService = new PhieuTraGopServiceImpl();
    private LichSuTraGopService lstgService = new LichSuTraGopServiceImpl();
    private PhieuTraGop ptg;
    private DefaultTableModel dtm;
    private List<LichSuTraGop> listView;

    /**
     * Creates new form ViewTraGopDetail
     */
//    public ViewLichSuTraGop() {
//        initComponents();
//    }
    public ViewLichSuTraGop(int id) {
        initComponents();
        viewTable();
        dtm = (DefaultTableModel) this.tblLichSuThuNo.getModel();
        ptg = ptgService.getByID(id);
        listView = lstgService.getByID(ptg.getId());
        fillPhieuTraGop();
        showDataTable(listView);

    }

    private void viewTable() {
        JTableHeader tbHead = tblLichSuThuNo.getTableHeader();
        tbHead.setFont(new Font("tahoma", Font.BOLD, 15));
        tbHead.setBackground(new Color(47, 85, 212));
        tbHead.setForeground(Color.white);
    }

    private void fillPhieuTraGop() {
        lblMaPhieu.setText(ptg.getMaPhieu());
        lblKiHan.setText(String.valueOf(ptg.getKyHan()) + " Tháng");
        lblLaiSuat.setText(String.valueOf(ptg.getLaiSuat()) + " %");
        lblNgayTao.setText(String.valueOf(ptg.getNgayTao()));
        lblPhaiTraHangThang.setText(convertVND(ptg.getPhaiTra()));
        lblTongTien.setText(convertVND(ptg.getTongPhaiTra()));
        lblNgayDong.setText(String.valueOf(ptg.getNgayTao().getDayOfMonth()));
        rdoChuaHoanThanh.setSelected(true);
        if (ptg.isTrangThai()) {
            rdoHoanThanh.setSelected(true);
        }
    }

    private PhieuTraGop newPhieuTraGop() {
        PhieuTraGop ptg = new PhieuTraGop();
        ptg.setTrangThai(false);
        if (rdoHoanThanh.isSelected()) {
            ptg.setTrangThai(true);
        }
        return ptg;
    }

    public String convertVND(long value) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String fomatValue = numberFormat.format(value);
        return fomatValue;
    }

    private LichSuTraGop newLichSuTraGop() {
        LichSuTraGop lstg = new LichSuTraGop();
        lstg.setMa(txtMaLSTG.getText().trim());
        lstg.setNgayThanhToan(LocalDate.now());
        lstg.setPhieuTraGop(ptg);
        lstg.setTongTien(Long.parseLong(txtTienThanhToan.getText().trim()));
        lstg.setGhiChu(txtGhiChu.getText().trim());
        return lstg;
    }

    private void showDataTable(List<LichSuTraGop> list) {
        dtm.setRowCount(0);
        for (LichSuTraGop lichSuTraGop : list) {
            dtm.addRow(lichSuTraGop.toDataRow());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnuXuatPhieuThu = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTienThanhToan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnTaoPhieuThu = new javax.swing.JButton();
        btnSuaPhieuThu = new javax.swing.JButton();
        btnXoaPhieuThu = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtMaLSTG = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSuThuNo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdoHoanThanh = new javax.swing.JRadioButton();
        rdoChuaHoanThanh = new javax.swing.JRadioButton();
        lblPhaiTraHangThang = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        lblLaiSuat = new javax.swing.JLabel();
        lblKiHan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblNgayDong = new javax.swing.JLabel();

        mnuXuatPhieuThu.setText("Xuất Phiếu Thu");
        mnuXuatPhieuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuXuatPhieuThuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuXuatPhieuThu);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Phiếu Trả Góp Chi Tiết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thu Nợ"));

        jLabel5.setText("Ghi chú :");

        jLabel6.setText("Nhập số tiền thanh toàn :");

        txtTienThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTienThanhToan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienThanhToan.setText("0");
        txtTienThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("VNĐ");

        txtGhiChu.setColumns(1);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnTaoPhieuThu.setBackground(new java.awt.Color(47, 85, 212));
        btnTaoPhieuThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoPhieuThu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieuThu.setText("Tạo Phiếu Thu");
        btnTaoPhieuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoPhieuThuActionPerformed(evt);
            }
        });

        btnSuaPhieuThu.setBackground(new java.awt.Color(47, 85, 212));
        btnSuaPhieuThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaPhieuThu.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaPhieuThu.setText("Sửa Phiếu Thu");
        btnSuaPhieuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPhieuThuActionPerformed(evt);
            }
        });

        btnXoaPhieuThu.setBackground(new java.awt.Color(47, 85, 212));
        btnXoaPhieuThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaPhieuThu.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaPhieuThu.setText("Xóa Phiếu thu");
        btnXoaPhieuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPhieuThuActionPerformed(evt);
            }
        });

        jLabel12.setText("Mã Phiếu Thu :");

        txtMaLSTG.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMaLSTG.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienThanhToan)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaLSTG)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTaoPhieuThu, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(btnSuaPhieuThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaPhieuThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTaoPhieuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaPhieuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaPhieuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaLSTG, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTienThanhToan)
                                .addComponent(jLabel7))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lịch Sử Thu Nợ"));

        tblLichSuThuNo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày trả", "Mã", "Số tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSuThuNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuThuNoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLichSuThuNoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichSuThuNo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cập Nhật Phiếu Trả Góp");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Phiếu Trả Góp"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã Phiếu :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Lãi Suất :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Kì Hạn :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Phải trả / Tháng :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Trạng thái :");

        rdoHoanThanh.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoHoanThanh);
        rdoHoanThanh.setText("Hoàn Thành");

        rdoChuaHoanThanh.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoChuaHoanThanh);
        rdoChuaHoanThanh.setText("Chưa Hoàn Thành");

        lblPhaiTraHangThang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPhaiTraHangThang.setForeground(new java.awt.Color(47, 85, 212));
        lblPhaiTraHangThang.setText("0");

        lblMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(47, 85, 212));
        lblMaPhieu.setText("0");

        lblLaiSuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLaiSuat.setForeground(new java.awt.Color(47, 85, 212));
        lblLaiSuat.setText("0");

        lblKiHan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKiHan.setForeground(new java.awt.Color(47, 85, 212));
        lblKiHan.setText("0");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Ngày Tạo :");

        lblNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(47, 85, 212));
        lblNgayTao.setText("0");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Tổng Tiền :");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(47, 85, 212));
        lblTongTien.setText("0");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Ngày Đóng :");

        lblNgayDong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNgayDong.setForeground(new java.awt.Color(47, 85, 212));
        lblNgayDong.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKiHan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(203, 203, 203)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(75, 75, 75))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblPhaiTraHangThang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHoanThanh, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoChuaHoanThanh)
                        .addGap(10, 10, 10))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(lblNgayTao))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblMaPhieu)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(lblTongTien))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblLaiSuat)))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblKiHan)
                    .addComponent(jLabel21)
                    .addComponent(lblNgayDong))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(lblPhaiTraHangThang))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoHoanThanh)
                        .addComponent(rdoChuaHoanThanh)
                        .addComponent(jLabel11)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLichSuThuNoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuThuNoMousePressed

    }//GEN-LAST:event_tblLichSuThuNoMousePressed

    private void tblLichSuThuNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuThuNoMouseClicked
        // TODO add your handling code here:
        LichSuTraGop lstg = listView.get(tblLichSuThuNo.getSelectedRow());
        txtGhiChu.setText(lstg.getGhiChu());
        txtMaLSTG.setText(lstg.getMa());
        txtTienThanhToan.setText(String.valueOf(lstg.getTongTien()));

        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tblLichSuThuNo.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < tblLichSuThuNo.getRowCount()) {
                tblLichSuThuNo.setRowSelectionInterval(row, row);//đánh dấu
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblLichSuThuNoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, ptgService.update(ptg.getId(), newPhieuTraGop()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTaoPhieuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuThuActionPerformed
        // TODO add your handling code here:
        //validate
        if (txtTienThanhToan.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Tiền thanh toán không được trống.");
            return;
        }

        try {
            if (Long.parseLong(txtTienThanhToan.getText().trim()) < ptg.getPhaiTra()) {
                JOptionPane.showMessageDialog(this, "Tiền thanh toán không được nhỏ hơn số tiền phải trả hàng tháng.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền thanh toán không hợp lệ.");
            return;
        }
        //xử lý
        JOptionPane.showMessageDialog(this, lstgService.insert(newLichSuTraGop()));
        listView = lstgService.getByID(ptg.getId());
        showDataTable(listView);
    }//GEN-LAST:event_btnTaoPhieuThuActionPerformed

    private void btnSuaPhieuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPhieuThuActionPerformed
        // TODO add your handling code here:
        if (txtTienThanhToan.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Tiền thanh toán không được trống.");
            return;
        }

        try {
            if (Long.parseLong(txtTienThanhToan.getText().trim()) < ptg.getPhaiTra()) {
                JOptionPane.showMessageDialog(this, "Tiền thanh toán không được nhỏ hơn số tiền phải trả hàng tháng.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền thanh toán không hợp lệ.");
            return;
        }
        int index = tblLichSuThuNo.getSelectedRow();
        if (index < 0 || index > tblLichSuThuNo.getRowCount()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại phiếu trong bảng");
            return;
        }
        LichSuTraGop lstg = listView.get(index);
        JOptionPane.showMessageDialog(this, lstgService.update(lstg.getId(), newLichSuTraGop()));
        listView = lstgService.getByID(ptg.getId());
        showDataTable(listView);
    }//GEN-LAST:event_btnSuaPhieuThuActionPerformed

    private void btnXoaPhieuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPhieuThuActionPerformed
        // TODO add your handling code here:
        int index = tblLichSuThuNo.getSelectedRow();
        if (index < 0 || index > tblLichSuThuNo.getRowCount()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại phiếu trong bảng");
            return;
        }

        LichSuTraGop lstg = listView.get(index);
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa phiếu thu này chứ ?");
        if (check != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            return;
        }
        JOptionPane.showMessageDialog(this, lstgService.delete(lstg.getId()));
        listView = lstgService.getByID(ptg.getId());
        showDataTable(listView);
    }//GEN-LAST:event_btnXoaPhieuThuActionPerformed

    private void mnuXuatPhieuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuXuatPhieuThuActionPerformed
        // TODO add your handling code here:
// Tạo đối tượng JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");

        // Chỉ cho phép chọn tập tin với định dạng .pdf
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);
        //set Tên cho File

        fileChooser.setSelectedFile(new File("Phieu Thu No " + ptg.getHoaDon().getKhachHang().getHoTen() + " " + LocalDate.now() + ".pdf"));
        // Hiển thị hộp thoại chọn vị trí và tên tập tin
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath();
            // LSTG
            LichSuTraGop lstg = listView.get(tblLichSuThuNo.getSelectedRow());
//            PhieuTraGopUtil.xuatPhieuThuPDF(lstg, path);
            new PhieuTraGopUtil().exportLichSuTraGop(lstg, path);
        }
    }//GEN-LAST:event_mnuXuatPhieuThuActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViewLichSuTraGop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViewLichSuTraGop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViewLichSuTraGop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViewLichSuTraGop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewLichSuTraGop().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaPhieuThu;
    private javax.swing.JButton btnTaoPhieuThu;
    private javax.swing.JButton btnXoaPhieuThu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblKiHan;
    private javax.swing.JLabel lblLaiSuat;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayDong;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblPhaiTraHangThang;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuItem mnuXuatPhieuThu;
    private javax.swing.JRadioButton rdoChuaHoanThanh;
    private javax.swing.JRadioButton rdoHoanThanh;
    private javax.swing.JTable tblLichSuThuNo;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaLSTG;
    private javax.swing.JTextField txtTienThanhToan;
    // End of variables declaration//GEN-END:variables
}
