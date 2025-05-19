/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.util.List;

public class DonHang {
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String ghiChu;
    private String hinhThucNhanHang;
    private List<String> tenMon;
    private List<Integer> soLuong;
    private List<Integer> giaTien;
    private int tongTien;
    private int danhGia = 0; 


    public DonHang(String hoTen, String soDienThoai, String diaChi, String ghiChu, 
                   String hinhThucNhanHang, List<String> tenMon, 
                   List<Integer> soLuong, List<Integer> giaTien, int tongTien) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ghiChu = ghiChu;
        this.hinhThucNhanHang = hinhThucNhanHang;
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.tongTien = tongTien;
    }

    // Getters
    public String getHoTen() { return hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public String getDiaChi() { return diaChi; }
    public String getGhiChu() { return ghiChu; }
    public String getHinhThucNhanHang() { return hinhThucNhanHang; }
    public List<String> getTenMon() { return tenMon; }
    public List<Integer> getSoLuong() { return soLuong; }
    public List<Integer> getGiaTien() { return giaTien; }
    public int getTongTien() { return tongTien; }

    // ⭐ Thêm getter và setter cho đánh giá
    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}