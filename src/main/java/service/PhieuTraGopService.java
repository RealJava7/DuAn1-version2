/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.PhieuTraGop;
import viewmodel.PhieuTraGopViewModel;

/**
 *
 * @author Administrator
 */
public interface PhieuTraGopService {

    String insert(PhieuTraGop phieuTraGop);

    String update(String id, PhieuTraGop phieuTraGop);

    List<PhieuTraGopViewModel> getAll();

    List<PhieuTraGopViewModel> getByString(String s);

    List<PhieuTraGopViewModel> getByTime(int index);

    List<PhieuTraGopViewModel> getByTrangThai(int index);
}
