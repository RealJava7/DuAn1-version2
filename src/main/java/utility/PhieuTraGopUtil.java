/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.NumberFormat;
import javax.swing.JTable;
import model.LichSuTraGop;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopUtil {

    public static String convertVND(long value) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String fomatValue = numberFormat.format(value);
        return fomatValue;
    }

    public static void xuatEXCEL(JTable table, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Sheet1");
            Row headerRow = sheet.createRow(0);
            // Xuất tiêu đề cột
            for (int i = 0; i < table.getColumnCount(); i++) {
                String columnName = table.getColumnName(i);
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnName);
            }
            // Xuất dữ liệu từng dòng
            for (int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    Cell cell = row.createCell(j);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }
            // Ghi tệp Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void xuatPhieuThuPDF(LichSuTraGop lstg, String filePath) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            Paragraph title = new Paragraph("PHIEU THU", boldFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            Paragraph maPhieu = new Paragraph("Ma phieu: " + lstg.getMa(), normalFont);
            document.add(maPhieu);

            document.add(new Paragraph("\n"));

            Paragraph date = new Paragraph("Ngay: " + lstg.getNgayThanhToan(), normalFont);
            document.add(date);

            document.add(new Paragraph("\n"));

            String khachHangStr = lstg.getPhieuTraGop().getHoaDon().getKhachHang().getHoTen() + " - " + lstg.getPhieuTraGop().getHoaDon().getKhachHang().getSdt();
            khachHangStr = Normalizer.normalize(khachHangStr, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "");
            Paragraph customerInfo = new Paragraph("Khach hang: " + khachHangStr, normalFont);
            document.add(customerInfo);

            document.add(new Paragraph("\n"));

            Paragraph soTien = new Paragraph("So tien: " + convertVND(lstg.getTongTien()) + " VND", normalFont);
            document.add(soTien);

            document.add(new Paragraph("\n"));

            String ghiChuStr = lstg.getGhiChu();
            ghiChuStr = Normalizer.normalize(ghiChuStr, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "");
            Paragraph ghiChu = new Paragraph("Ghi chu: " + ghiChuStr, normalFont);
            document.add(ghiChu);

            document.add(new Paragraph("\n"));

            Paragraph chuKy = new Paragraph("                 Khach Hang                                              Dai Dien", normalFont);
            document.add(chuKy);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
