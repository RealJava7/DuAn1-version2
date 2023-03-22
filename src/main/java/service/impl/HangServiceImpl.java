package service.impl;

import java.util.List;
import model.Hang;
import repository.HangRepository;
import service.HangService;
import viewmodel.HangResponse;

public class HangServiceImpl implements HangService {
    
    private HangRepository hangRepository = new HangRepository();

    @Override
    public List<Hang> getAll() {
        return hangRepository.getAllEntity();
    }
    
}
