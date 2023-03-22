package service.impl;

import java.util.List;
import repository.DienThoaiRepository;
import service.DienThoaiService;
import viewmodel.DienThoaiResponse;

public class DienThoaiServiceImpl implements DienThoaiService {
    
    private DienThoaiRepository dienThoaiRepository = new DienThoaiRepository();

    @Override
    public List<DienThoaiResponse> getAll() {
        return dienThoaiRepository.getAll();
    }
    
}
