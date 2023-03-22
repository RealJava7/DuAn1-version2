package service.impl;

import java.util.List;
import model.HeDieuHanh;
import repository.HeDieuHanhRepository;
import service.HeDieuHanhService;

public class HeDieuHanhServiceImpl implements HeDieuHanhService {
    
    private HeDieuHanhRepository heDieuHanhRepository = new HeDieuHanhRepository();

    @Override
    public List<HeDieuHanh> getAll() {
        return heDieuHanhRepository.getAllEntity();
    }
    
}
