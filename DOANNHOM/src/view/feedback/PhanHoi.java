/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.feedback;

/**
 *
 * @author Thao Nguyen
 */
public class PhanHoi {

        private String id;
        private String tenKhachHang;
        private String noiDung;
        private int danhGia;  // số sao
        private String ngayGui;
        private String trangThai;

        // Constructor
        public PhanHoi(String id, String tenKhachHang, String noiDung, int danhGia, String ngayGui, String trangThai) {
            this.id = id;
            this.tenKhachHang = tenKhachHang;
            this.noiDung = noiDung;
            this.danhGia = danhGia;
            this.ngayGui = ngayGui;
            this.trangThai = trangThai;
        }

        // Getter
        public String getId() {
            return id;
        }

        public String getTenKhachHang() {
            return tenKhachHang;
        }

        public String getNoiDung() {
            return noiDung;
        }

        public int getDanhGia() {
            return danhGia;
        }

        public String getNgayGui() {
            return ngayGui;
        }

        public String getTrangThai() {
            return trangThai;
        }
    }
