package service;

import java.util.List;
import model.DienThoai;
import viewmodel.DienThoaiResponse;

public interface DienThoaiService {

    List<DienThoaiResponse> getAll();
    
    String add(DienThoai dienThoai);
    
    DienThoai getByMaDT(String maDT);
    
}
