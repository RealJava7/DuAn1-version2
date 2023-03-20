/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author virus
 */
public class ViewModelLoaiBaoHanh {

    private int id;
    private String tenLoaiBaoHanh;

    public ViewModelLoaiBaoHanh() {
    }

    public ViewModelLoaiBaoHanh(int id, String tenLoaiBaoHanh) {
        this.id = id;
        this.tenLoaiBaoHanh = tenLoaiBaoHanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiBaoHanh() {
        return tenLoaiBaoHanh;
    }

    public void setTenLoaiBaoHanh(String tenLoaiBaoHanh) {
        this.tenLoaiBaoHanh = tenLoaiBaoHanh;
    }

}
