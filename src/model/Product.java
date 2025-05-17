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
    private String ten;  // Tên sản phẩm
    private int gia;     // Giá sản phẩm

    // Constructor
    public Product(String ten, int gia) {
        this.ten = ten;
        this.gia = gia;
    }

    // Getter
    public String getTen() {
        return ten;
    }

    public int getGia() {
        return gia;
    }

    // Phương thức toString() để hiển thị tên và giá sản phẩm
    @Override
    public String toString() {
        return ten + " - " + gia + "đ";
    }
}


