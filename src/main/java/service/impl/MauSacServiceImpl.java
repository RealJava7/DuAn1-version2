package service.impl;

import java.util.List;
import model.MauSac;
import repository.MauSacRepository;
import service.MauSacService;

public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAllEntity();
    }

}
