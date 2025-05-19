/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thao Nguyen
 */
public class DoUong {

    private String tenMon;
    private int gia;

    public DoUong(String tenMon, int gia) {
        this.tenMon = tenMon;
        this.gia = gia;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getGia() {
        return gia;
    }
}
