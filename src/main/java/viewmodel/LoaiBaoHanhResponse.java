package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoaiBaoHanhResponse {
    
    private int id;
    private String ten;
    private String dieuKien;

    @Override
    public String toString() {
        return "LoaiBaoHanhResponse{" + "id=" + id + ", ten=" + ten + ", dieuKien=" + dieuKien + '}';
    }
    
}
