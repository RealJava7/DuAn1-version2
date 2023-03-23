package service.impl;

import java.util.List;
import repository.PhieuBaoHanhRepository;
import service.PhieuBaoHanhService;
import viewmodel.PhieuBaoHanhResponse;

public class PhieuBaoHanhServiceImpl implements PhieuBaoHanhService {

    private PhieuBaoHanhRepository repository = new PhieuBaoHanhRepository();

    @Override
    public List<PhieuBaoHanhResponse> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(PhieuBaoHanhResponse pbh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
