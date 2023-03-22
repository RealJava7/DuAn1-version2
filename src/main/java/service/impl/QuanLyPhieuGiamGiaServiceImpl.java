package service.impl;

import java.util.List;
import model.PhieuGiamGia;
import repository.PhieuGiamGiaRepository;
import service.QuanLyPhieuGiamGiaService;
import viewmodel.PhieuGiamGiaResponse;

/**
 *
 * @author DELL
 */
public class QuanLyPhieuGiamGiaServiceImpl implements QuanLyPhieuGiamGiaService {

    PhieuGiamGiaRepository pr = new PhieuGiamGiaRepository();

    @Override
    public List<PhieuGiamGiaResponse> getall() {
        return pr.getAll();
    }

    @Override
    public String add(PhieuGiamGia phieu) {
   if (pr.add(phieu) == true) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(PhieuGiamGiaResponse phieu) {
  if (pr.update(phieu)==true) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }
    public static void main(String[] args) {
        QuanLyPhieuGiamGiaService qs = new QuanLyPhieuGiamGiaServiceImpl();
        System.out.println(qs.getall());
    }
}
