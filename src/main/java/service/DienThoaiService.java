package service;

import java.util.List;
import model.DienThoai;
import viewmodel.DienThoaiResponse;

public interface DienThoaiService {

    List<DienThoaiResponse> getAllResponseByStatus(boolean status);
    
    String add(DienThoai dienThoai);
    
    DienThoai getByMaDT(String maDT);
    
    String update(DienThoaiResponse dienThoaiResponse);
    
    String changeStatus(DienThoaiResponse dienThoaiResponse, boolean newStatus);
    
    List<DienThoaiResponse> getAllResponseByGiaBan(String order);
    
    List<DienThoaiResponse> searchAllResponseByName(String keyword);
}
