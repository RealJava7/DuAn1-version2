package service.impl;

import java.util.List;
import model.DienThoai;
import repository.DienThoaiRepository;
import service.DienThoaiService;
import viewmodel.DienThoaiResponse;

public class DienThoaiServiceImpl implements DienThoaiService {

    private DienThoaiRepository dienThoaiRepository = new DienThoaiRepository();

    @Override
    public List<DienThoaiResponse> getAllResponse(boolean status) {
        return dienThoaiRepository.getAllResponse(status);
    }

    @Override
    public String add(DienThoai dienThoai) {
        boolean addResult = dienThoaiRepository.add(dienThoai);
        return addResult ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public DienThoai getByMaDT(String maDT) {
        return dienThoaiRepository.getByMaDT(maDT);
    }

    @Override
    public String update(DienThoaiResponse dienThoaiResponse) {
        boolean updateResult = dienThoaiRepository.update(dienThoaiResponse);
        return updateResult ? "Sửa thành công" : "Sửa thất bại";
    }

}
