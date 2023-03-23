package service;

import java.util.List;
import model.Hang;
import viewmodel.HangResponse;

public interface HangService {
    
     List<Hang> getAllEntity();
    
    List<HangResponse> getAllResponse();
    
    String add(Hang hang);
    
    String update(HangResponse hangResponse);
    
    Hang getByTenHang(String tenHang);
    
    List<HangResponse> getAllDaXoa();
    
    String delete(HangResponse hangResponse);
    
}
