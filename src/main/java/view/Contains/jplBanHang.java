package view.Contains;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.Contains.tragop.jplFormThanhToan;

public class jplBanHang extends javax.swing.JPanel {

    public jplBanHang() {
        initComponents();
        //khởi tạo giở hàng
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(++soDon));
        //Khởi tạo cách thanh toán
        addFormThanhToan(new jplFormThanhToan());
    }

    private void addFormThanhToan(JPanel jpl) {
        jplThanhToanContain.removeAll();
        jplThanhToanContain.add(jpl);
        jplThanhToanContain.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnFormBanHang = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTaoDonHang = new javax.swing.JButton();
        btnXoaDonHang = new javax.swing.JButton();
        jplGioHang = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jplDeXuatSanPham = new javax.swing.JPanel();
        Jpanel20 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jplThanhToanContain = new javax.swing.JPanel();
        jplFormThanhToan1 = new view.Contains.tragop.jplFormThanhToan();

        jpnFormBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N

        btnTaoDonHang.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnTaoDonHang.setText("Tạo Đơn");
        btnTaoDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonHangActionPerformed(evt);
            }
        });

        btnXoaDonHang.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnXoaDonHang.setText("Xóa Đơn");
        btnXoaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDonHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoDonHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaDonHang)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnXoaDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jplGioHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jplGioHangLayout = new javax.swing.GroupLayout(jplGioHang);
        jplGioHang.setLayout(jplGioHangLayout);
        jplGioHangLayout.setHorizontalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jplGioHangLayout.setVerticalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jpnFormBanHangLayout = new javax.swing.GroupLayout(jpnFormBanHang);
        jpnFormBanHang.setLayout(jpnFormBanHangLayout);
        jpnFormBanHangLayout.setHorizontalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnFormBanHangLayout.setVerticalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFormBanHangLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jplDeXuatSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jplDeXuatSanPhamLayout = new javax.swing.GroupLayout(jplDeXuatSanPham);
        jplDeXuatSanPham.setLayout(jplDeXuatSanPhamLayout);
        jplDeXuatSanPhamLayout.setHorizontalGroup(
            jplDeXuatSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jplDeXuatSanPhamLayout.setVerticalGroup(
            jplDeXuatSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );

        Jpanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jCheckBox1.setText("Trả Góp");

        jplThanhToanContain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jplThanhToanContainLayout = new javax.swing.GroupLayout(jplThanhToanContain);
        jplThanhToanContain.setLayout(jplThanhToanContainLayout);
        jplThanhToanContainLayout.setHorizontalGroup(
            jplThanhToanContainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThanhToanContainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplFormThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplThanhToanContainLayout.setVerticalGroup(
            jplThanhToanContainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThanhToanContainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplFormThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Jpanel20Layout = new javax.swing.GroupLayout(Jpanel20);
        Jpanel20.setLayout(Jpanel20Layout);
        Jpanel20Layout.setHorizontalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplThanhToanContain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Jpanel20Layout.setVerticalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jplThanhToanContain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jplDeXuatSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jpanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplDeXuatSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Jpanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonHangActionPerformed
        // TODO add your handling code here:
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(soDon + 1));
    }//GEN-LAST:event_btnTaoDonHangActionPerformed

    private void btnXoaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDonHangActionPerformed
        // TODO add your handling code here:
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel20;
    private javax.swing.JButton btnTaoDonHang;
    private javax.swing.JButton btnXoaDonHang;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jplDeXuatSanPham;
    private view.Contains.tragop.jplFormThanhToan jplFormThanhToan1;
    private javax.swing.JPanel jplGioHang;
    private javax.swing.JPanel jplThanhToanContain;
    private javax.swing.JPanel jpnFormBanHang;
    // End of variables declaration//GEN-END:variables
}
