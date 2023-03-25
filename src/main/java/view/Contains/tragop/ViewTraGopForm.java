package view.Contains.tragop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.LichSuTraGop;
import model.PhieuTraGop;
import repository.LichSuTraGopRepository;
import repository.impl.LichSuTraGopRepositoryImpl;
import service.PhieuTraGopService;
import service.impl.PhieuTraGopServiceImpl;

public class ViewTraGopForm extends javax.swing.JPanel {

    private PhieuTraGopService phieuTraGopService = new PhieuTraGopServiceImpl();
    private LichSuTraGopRepository lstgRepository = new LichSuTraGopRepositoryImpl();

    public ViewTraGopForm() {
        initComponents();
        tinhLaiSuat();
        getPhieuTraGopForm();
    }

    private void tinhLaiSuat() {
        long tongTien = getTongTien();
        float laiSuat = getLaiSuat();
        int kyHan = getKyHan();

//        System.out.println(tongTien);
//        System.out.println(laiSuat);
//        System.out.println(kyHan);
        long traTruoc = (long) (tongTien * 0.3);
        long conLai = tongTien - traTruoc;
        long traHangThang = (long) ((conLai / kyHan) + ((conLai * laiSuat / 100) / kyHan));

//        System.out.println(traTruoc);
//        System.out.println(traHangThang);
        lblPhaiTra.setText(String.valueOf(traHangThang));
        lblTraTruoc.setText(String.valueOf(traTruoc));
    }

    private PhieuTraGop getPhieuTraGopForm() {
        //tạo Phieu Tra Gop
        PhieuTraGop ptg = new PhieuTraGop();
        //tạo lich su tra gop
        LichSuTraGop lstg = new LichSuTraGop();
        //set lich sử trả góp
        lstg.setMa(lstgRepository.generateMaLSTG());
        lstg.setGhiChu("");
        lstg.setNgayThanhToan(LocalDate.now());
        lstg.setPhieuTraGop(ptg);
        lstg.setTongTien(getTraTruoc());
        //set Phiếu trả góp
//        ptg.setHoaDon(null); // chưa có hóa đơn làm sau
        ptg.setKyHan(getKyHan());
        ptg.setLaiSuat(getLaiSuat());
        ptg.addLichSuTraGop(lstg);
        ptg.setMaPhieu("");
        ptg.setNgayDong(LocalDate.now().getDayOfMonth());
        ptg.setNgayTao(LocalDate.now());
        ptg.setPhaiTra(getPhaiTra());
        ptg.setTongPhaiTra(getTongTien());

        System.out.println(ptg.toString());
        System.out.println(lstg.toString());
        return ptg;
    }

    private long getTongTien() {
        return Long.parseLong(lblTongTien.getText());
    }

    private float getLaiSuat() {
        return Float.parseFloat(txtLaiSuat.getText());
    }

    private int getKyHan() {
        return Integer.parseInt(cbxKiHan.getSelectedItem().toString().substring(0, 2).trim());
    }

    private long getPhaiTra() {
        return Long.parseLong(lblPhaiTra.getText().trim());
    }

    private long getTraTruoc() {
        return Long.parseLong(lblTraTruoc.getText().trim());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnThanhToan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        btnThemKhachHang = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        txtLaiSuat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxKiHan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblTraTruoc = new javax.swing.JLabel();
        lblPhaiTra = new javax.swing.JLabel();

        jpnThanhToan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Khách Hàng :");

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachHangKeyReleased(evt);
            }
        });

        btnThemKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemKhachHang.setText("Thêm Khách Hàng");
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tổng Tiền :");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(233, 83, 83));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("2345678");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Lãi Suất :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Trả Trước :");

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtLaiSuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtLaiSuat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLaiSuat.setText("0");
        txtLaiSuat.setToolTipText("");
        txtLaiSuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLaiSuatKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("%");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Kì Hạn :");

        cbxKiHan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxKiHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 Tháng", "6 Tháng", "9 Tháng", "12 Tháng" }));
        cbxKiHan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKiHanItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Phải trả / Tháng :");

        lblTraTruoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTraTruoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTraTruoc.setText("0");

        lblPhaiTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhaiTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhaiTra.setText("0");

        javax.swing.GroupLayout jpnThanhToanLayout = new javax.swing.GroupLayout(jpnThanhToan);
        jpnThanhToan.setLayout(jpnThanhToanLayout);
        jpnThanhToanLayout.setHorizontalGroup(
            jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThanhToanLayout.createSequentialGroup()
                                .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxKiHan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(lblTraTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnThanhToanLayout.setVerticalGroup(
            jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnThemKhachHang)
                .addGap(30, 30, 30)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTongTien))
                .addGap(30, 30, 30)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxKiHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblPhaiTra))
                .addGap(30, 30, 30)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTraTruoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachHangKeyReleased
        //        // TODO add your handling code here:
        //        jPopupMenu1.removeAll();
        //        jPopupMenu1.add(jpnlSearchKH);
        //        listKH = khachHangService.getKhachHang(txtKhachHang.getText().trim());
        //        if (listKH.size() >= 0) {
        //            dlmKH.removeAllElements();
        //            for (KhachHang khachHang : listKH) {
        //                dlmKH.addElement(khachHang.getMaKH() + " - " + khachHang.getHo() + " " + khachHang.getTenDem() + " " + khachHang.getTen());
        //            }
        //            jPopupMenu1.show(txtKhachHang, 0, txtSearchSP.getHeight());
        //        }
    }//GEN-LAST:event_txtKhachHangKeyReleased

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        // TODO add your handling code here:
        //new ViewKhachHang().setVisible(true);
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        JOptionPane.showMessageDialog(this, phieuTraGopService.insert(getPhieuTraGopForm()));

        //thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtLaiSuatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLaiSuatKeyReleased
        // TODO add your handling code here:
        try {
            tinhLaiSuat();
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lãi suất không hơp lệ");
        }
    }//GEN-LAST:event_txtLaiSuatKeyReleased

    private void cbxKiHanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKiHanItemStateChanged
        // TODO add your handling code here:
        try {
            tinhLaiSuat();
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lãi suất không hơp lệ");
        }
    }//GEN-LAST:event_cbxKiHanItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKhachHang;
    private javax.swing.JComboBox<String> cbxKiHan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpnThanhToan;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraTruoc;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtLaiSuat;
    // End of variables declaration//GEN-END:variables
}
