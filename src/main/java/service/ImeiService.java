package service;

import java.util.List;
import model.Imei;
import viewmodel.ImeiResponse;

public interface ImeiService {
    
    Imei getByImei(String imei);
    
    String add(Imei imei);
    
    List<ImeiResponse> getAllNoneDienThoaiImei();
    
    void update(ImeiResponse imeiResponse);
    
    List<ImeiResponse> getAllDienThoaiId(int dienThoaiId);
    
    void deleteImeiWithDienThoaiNull();
    
    String delete(ImeiResponse imeiResponse);
    
    String updateImeiStr(ImeiResponse imeiResponse);
}
