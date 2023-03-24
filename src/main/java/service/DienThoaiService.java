package service;

import java.util.List;
import model.DienThoai;
import viewmodel.DienThoaiResponse;

public interface DienThoaiService {

    List<DienThoaiResponse> getAllResponse(boolean status);
    
    String add(DienThoai dienThoai);
    
    DienThoai getByMaDT(String maDT);
    
    String update(DienThoaiResponse dienThoaiResponse);
}
