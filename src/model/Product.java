/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private String ten;     // Tên sản phẩm
    private int gia;        // Giá đơn vị
    private int soLuong;    // Số lượng

    // Constructor
    public Product(String ten, int gia) {
        this.ten = ten;
        this.gia = gia;
        this.soLuong = 1;
    }

    // Getter
    public String getTen() {
        return ten;
    }

    public int getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Setter nếu bạn muốn thay đổi số lượng
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // toString() để hiển thị thông tin
    @Override
    public String toString() {
        return ten + " - " + gia + "đ x" + soLuong;
    }
}



