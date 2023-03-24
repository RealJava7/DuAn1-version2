package service.impl;

import java.util.ArrayList;
import java.util.List;
import repository.LoaiBaoHanhRepository;
import repository.PhieuBaoHanhRepository;
import service.PhieuBaoHanhService;
import viewmodel.LoaiBaoHanhResponse;
import viewmodel.PhieuBaoHanhResponse;

public class PhieuBaoHanhServiceImpl implements PhieuBaoHanhService {

    private LoaiBaoHanhRepository lbh = new LoaiBaoHanhRepository();
    private PhieuBaoHanhRepository repository = new PhieuBaoHanhRepository();

    @Override
    public List<PhieuBaoHanhResponse> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(PhieuBaoHanhResponse pbh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PhieuBaoHanhResponse> getAllStatus(boolean status) {
        return repository.getList(status);
    }

    @Override
    public List<PhieuBaoHanhResponse> getAllListSearch(String tenKH) {
        return repository.getListSearch(tenKH);
    }

    @Override
    public List<String> getAllLoaiBaoHanh() {
        List<LoaiBaoHanhResponse> list = lbh.getAll();
        List<String> listString = new ArrayList<>();
        for (LoaiBaoHanhResponse lbh : list) {
            listString.add(lbh.getTen());
        }
        return listString;
    }

}
