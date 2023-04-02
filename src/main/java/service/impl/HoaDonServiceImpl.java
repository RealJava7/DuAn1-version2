package service.impl;

import model.HoaDon;
import repository.HoaDonRepository;
import service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public String add(HoaDon hoaDon) {
        boolean addResult = hoaDonRepository.add(hoaDon);
        return addResult ? "Thêm thành công" : "Thêm thất bại";
    }

}
