/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.PhieuTraGop;

/**
 *
 * @author Administrator
 */
public interface PhieuTraGopRepository {

    public boolean delete(String id);

    boolean update(String id, PhieuTraGop phieuTraGop);

    boolean insert(PhieuTraGop phieuTraGop);

    List<PhieuTraGop> getAll();

    List<PhieuTraGop> getByString(String s);

    List<PhieuTraGop> getByTime(int index);

    List<PhieuTraGop> getByTrangThai(int index);
}
