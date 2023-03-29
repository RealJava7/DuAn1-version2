package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import service.PhieuBaoHanhService;
import service.impl.PhieuBaoHanhServiceImpl;
import view.Contains.entitybaohanh.QuanLyLoaiBaoHanh;
import viewmodel.PhieuBaoHanhResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class jplBaoHanh extends javax.swing.JPanel {

    DefaultTableModel dtm;
    PhieuBaoHanhService service;
    DefaultComboBoxModel dcbm;

    public jplBaoHanh() {
        initComponents();
        viewTable();
        dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) this.tbCTPBH.getModel();
        service = new PhieuBaoHanhServiceImpl();
        dcbm = new DefaultComboBoxModel();
        dcbm = (DefaultComboBoxModel) this.cbbLBH.getModel();
        showDataTable(service.getAll());
        showComboboxData(service.getAllLoaiBaoHanh());

    }

    private void viewTable() {
        JTableHeader Theader = tbCTPBH.getTableHeader();

        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);

    }

    private void showComboboxData(List<String> lbh) {
        cbbLBH.removeAllItems();
        for (String cbb : lbh) {
            cbbLBH.addItem(cbb);
        }
    }

    private void showDataTable(List<PhieuBaoHanhResponse> list) {
        dtm.setRowCount(0);
        for (PhieuBaoHanhResponse pbh : list) {
            dtm.addRow(pbh.toRowData());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTPBH = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        cbbLBH = new javax.swing.JComboBox<>();
        btnAddLoaiBaoHanh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdConHan = new javax.swing.JRadioButton();
        rdHetHan = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchTenKH = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnImportExcel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbCTPBH.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tbCTPBH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbCTPBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên LBH", "Điều Kiện BH", "Tên KH", "Tên ĐT", "Imei", "Giá", "Thời Hạn", "Ngày Mua", "Ngày Hết Hạn", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCTPBH.setRowHeight(25);
        jScrollPane2.setViewportView(tbCTPBH);
        if (tbCTPBH.getColumnModel().getColumnCount() > 0) {
            tbCTPBH.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("LOẠI BẢO HÀNH"));

        btnAddLoaiBaoHanh.setBackground(new java.awt.Color(47, 85, 212));
        btnAddLoaiBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        btnAddLoaiBaoHanh.setText("Quản Lý Loại Bảo Hành");
        btnAddLoaiBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLoaiBaoHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLBH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddLoaiBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(cbbLBH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddLoaiBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Phiếu Bảo Hành"));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trạng Thái:");

        rdConHan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdConHan);
        rdConHan.setText("Còn Hạn");
        rdConHan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdConHanMouseClicked(evt);
            }
        });

        rdHetHan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdHetHan);
        rdHetHan.setText("Hết Hạn");
        rdHetHan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdHetHanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(rdConHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(rdHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rdConHan)
                    .addComponent(rdHetHan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm Phiếu Bảo Hành"));

        jLabel2.setText("Tên KH:");

        txtSearchTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTenKHActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(47, 85, 212));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSearchTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnImportExcel.setBackground(new java.awt.Color(47, 85, 212));
        btnImportExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-30.png"))); // NOI18N
        btnImportExcel.setText("Export");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        jLabel5.setText("Danh Sách Phiếu Bảo Hành");

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 85, 212));
        jLabel3.setText("BẢO HÀNH");

        jButton2.setText("Cập Nhật");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnImportExcel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(100, 100, 100))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(46, 46, 46)
                        .addComponent(jButton1)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportExcel)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        // TODO add your handling code here:
        //chọn lơi lưu trữ
        showDataTable(service.getAll());
        FileOutputStream excelFOS = null;
        BufferedOutputStream excelBOS = null;
        XSSFWorkbook excelJtableExporter = null;
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\virus\\OneDrive\\My Desktop");
        //dổi tên của dialog
        excelFileChooser.setDialogTitle("Save As");
        //định dạng file excel
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {

                excelJtableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJtableExporter.createSheet("JTable Sheet");

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);

                        excelCell.setCellValue(dtm.getValueAt(i, j).toString());
                    }
                }

                excelFOS = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOS = new BufferedOutputStream(excelFOS);
                excelJtableExporter.write(excelBOS);

                JOptionPane.showMessageDialog(rdConHan, "Xuất file excel thành công");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rdConHan, "Lỗi không tìm thấy file");
                ex.printStackTrace();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rdConHan, "Một lỗi gì đó!......");
                ex.printStackTrace();
            } finally {
                try {
                    if (excelFOS != null) {
                        excelFOS.close();
                    }
                    if (excelBOS != null) {
                        excelBOS.close();
                    }
                    if (excelJtableExporter != null) {
                        excelJtableExporter.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rdConHan, "Lỗi khi cố gắng đóng các class");
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnAddLoaiBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLoaiBaoHanhActionPerformed
        // TODO add your handling code here:
        QuanLyLoaiBaoHanh qllbh = new QuanLyLoaiBaoHanh();
        qllbh.setVisible(true);
    }//GEN-LAST:event_btnAddLoaiBaoHanhActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtSearchTenKH.getText().matches("\\s+") && txtSearchTenKH.getText().matches("\\s")) {
            JOptionPane.showMessageDialog(rdConHan, "Không được để trống tìm kiếm");
        } else {
            showDataTable(service.getAllListSearch(txtSearchTenKH.getText()));
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        showDataTable(service.getAll());
        showComboboxData(service.getAllLoaiBaoHanh());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdConHanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdConHanMouseClicked
        // TODO add your handling code here:
        showDataTable(service.getAllStatus(true));
    }//GEN-LAST:event_rdConHanMouseClicked

    private void rdHetHanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdHetHanMouseClicked
        // TODO add your handling code here:
        showDataTable(service.getAllStatus(false));
    }//GEN-LAST:event_rdHetHanMouseClicked

    private void txtSearchTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchTenKHActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLoaiBaoHanh;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLBH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdConHan;
    private javax.swing.JRadioButton rdHetHan;
    private javax.swing.JTable tbCTPBH;
    private javax.swing.JTextField txtSearchTenKH;
    // End of variables declaration//GEN-END:variables
}
