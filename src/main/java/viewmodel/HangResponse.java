package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HangResponse {
    
    private int id;
    private String tenHang;
    
    @Override
    public String toString() {
        return "Hang{" + "id=" + id + ", tenHang=" + tenHang + '}';
    }
}
