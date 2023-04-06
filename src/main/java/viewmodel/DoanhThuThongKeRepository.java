/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.time.LocalDate;

/**
 *
 * @author Ma
 */
public class DoanhThuThongKeRepository {
    private LocalDate ngay;
    private Long doanhThu;

    public DoanhThuThongKeRepository() {
    }

    public DoanhThuThongKeRepository(LocalDate ngay, Long doanhThu) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public Long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Long doanhThu) {
        this.doanhThu = doanhThu;
    }
    
    
}
