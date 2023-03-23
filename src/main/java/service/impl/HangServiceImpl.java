package service.impl;

import java.util.List;
import model.Hang;
import repository.HangRepository;
import service.HangService;
import viewmodel.HangResponse;

public class HangServiceImpl implements HangService {

    private HangRepository hangRepository = new HangRepository();

    @Override
    public List<Hang> getAllEntity() {
        return hangRepository.getAllEntity();
    }

    @Override
    public List<HangResponse> getAllResponse() {
        return hangRepository.getAllResponse();
    }

    @Override
    public String add(Hang hang) {
        boolean addResult = hangRepository.add(hang);
        return addResult ? "Thêm mới thành công!" : "Thêm mới thất bại!";
    }

    @Override
    public Hang getByTenHang(String tenHang) {
        return hangRepository.getByTenHang(tenHang);
    }

    @Override
    public String update(HangResponse hangResponse) {
        boolean updateResult = hangRepository.update(hangResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public List<HangResponse> getAllDaXoa() {
        return hangRepository.getAllDaXoa();
    }

    @Override
    public String delete(HangResponse hangResponse) {
        boolean deleteResult = hangRepository.delete(hangResponse);
        return deleteResult ? "Xóa thành công!" : "Xóa thất bại!";
    }

}
