package service;

import java.util.List;
import model.MauSac;
import viewmodel.MauSacResponse;

public interface MauSacService {

    List<MauSac> getAll();
    
    MauSac getByMa(String maMau);
    
    String add(MauSac mauSac);
    
    List<MauSacResponse> getAllResponse(boolean status);
    
    String update(MauSacResponse mauSacResponse);
    
    String changeStatus(MauSacResponse mauSacResponse, boolean newStatus);
    
}
