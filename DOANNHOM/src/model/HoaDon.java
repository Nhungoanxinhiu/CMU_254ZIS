/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HoaDon {

    private String tenSP;
    private double gia;
    private int soLuong;

    public HoaDon(String tenSP, double gia, int soLuong) {
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public double getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getTongTien() {
        return gia * soLuong;
    }
}
