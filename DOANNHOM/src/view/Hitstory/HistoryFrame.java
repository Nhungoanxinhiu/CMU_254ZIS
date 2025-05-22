package view.Hitstory;

import control.AppController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import model.DonHang;
import view.feedback.GiaoDienFeadback;
import view.payment.PaymentFrame;

public class HistoryFrame extends JFrame {

    public HistoryFrame() {
        setTitle("Lịch sử thanh toán");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Color mainColor = new Color(242, 220, 194);        // Màu nền chính
        Color tableGray = new Color(230, 230, 230);         // Màu nền bảng xám nhạt
        Color selectionBlue = new Color(173, 216, 230);     // Màu khi chọn hàng (xanh nhạt)

        String[] columnNames = {"STT", "Họ tên", "SĐT", "Tổng tiền", "Hình thức"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    comp.setBackground(row % 2 == 0 ? tableGray : Color.WHITE);
                    comp.setForeground(Color.BLACK);
                } else {
                    comp.setBackground(selectionBlue); // Màu xanh khi chọn
                    comp.setForeground(Color.BLACK);
                }
                return comp;
            }
        };

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        table.setBackground(tableGray);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int stt = 1;
        for (DonHang dh : AppController.lichSuDonHang) {
            model.addRow(new Object[]{
                stt++,
                dh.getHoTen(),
                dh.getSoDienThoai(),
                dh.getTongTien() + " đ",
                dh.getHinhThucNhanHang()
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(tableGray);
        add(scrollPane, BorderLayout.CENTER);

        // ==== Nút Đánh Giá ====
        JButton btnDanhGia = new JButton("Đánh giá sản phẩm");
        btnDanhGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDanhGia.setBackground(Color.WHITE);
        btnDanhGia.setForeground(Color.BLACK);
        btnDanhGia.setFocusPainted(false);
        btnDanhGia.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // ==== Nút Quay Lại ====
        JButton btnBack = new JButton("Quay lại");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(Color.BLACK);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        bottomPanel.setBackground(mainColor);
        bottomPanel.add(btnBack);
        bottomPanel.add(btnDanhGia);
        add(bottomPanel, BorderLayout.SOUTH);

        // ==== Sự kiện nút đánh giá ====
        btnDanhGia.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn hàng để đánh giá!");
                return;
            }
            DonHang dh = AppController.lichSuDonHang.get(selectedRow);
            GiaoDienFeadback dgFrame = new GiaoDienFeadback(dh);
            dgFrame.setVisible(true);
            // Đóng cửa sổ hiện tại sau khi mở giao diện đánh giá
            dispose();
        });

        // ==== Sự kiện nút quay lại ====
        btnBack.addActionListener(e -> {
            dispose(); // Đóng ThongKeFrame hiện tại

            // Mở lại trang Thanh toán
            new PaymentFrame().setVisible(true);
        });

        getContentPane().setBackground(mainColor);
    }
}
