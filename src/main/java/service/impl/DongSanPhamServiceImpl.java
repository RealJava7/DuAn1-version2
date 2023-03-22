
package service.impl;

import java.util.List;
import model.DongSanPham;
import repository.DongSanPhamRepository;
import service.DongSanPhamService;

public class DongSanPhamServiceImpl implements DongSanPhamService {
    
    private DongSanPhamRepository dongSanPhamRepository = new DongSanPhamRepository();

    @Override
    public List<DongSanPham> getAll(int id) {
        return dongSanPhamRepository.getAllEntityByHang(id);
    }
    
}
