/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.oderfeadback;

/**
 *
 * @author Admin
 */
import control.AppController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.DonHang;

public class DonHangDaDanhGiaFrame extends JFrame {
    public DonHangDaDanhGiaFrame() {
        setTitle("Đơn hàng đã đánh giá");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnNames = {"STT", "Họ tên", "Tổng tiền", "Hình thức", "Số sao"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        int stt = 1;
        for (DonHang dh : AppController.lichSuDonHang) {
            if (dh.getDanhGia() > 0) { // Chỉ hiện đơn hàng đã đánh giá
                model.addRow(new Object[]{
                    stt++,
                    dh.getHoTen(),
                    dh.getTongTien(),
                    dh.getHinhThucNhanHang(),
                    dh.getDanhGia() + " ★"
                });
            }
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

