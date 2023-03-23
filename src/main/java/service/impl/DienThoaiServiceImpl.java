package service.impl;

import java.util.List;
import model.DienThoai;
import repository.DienThoaiRepository;
import service.DienThoaiService;
import viewmodel.DienThoaiResponse;

public class DienThoaiServiceImpl implements DienThoaiService {

    private DienThoaiRepository dienThoaiRepository = new DienThoaiRepository();

    @Override
    public List<DienThoaiResponse> getAll() {
        return dienThoaiRepository.getAll();
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

}
