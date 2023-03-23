package service;

import java.util.List;
import viewmodel.PhieuBaoHanhResponse;

public interface PhieuBaoHanhService {

    List<PhieuBaoHanhResponse> getAll();

    String add(PhieuBaoHanhResponse pbh);

}
