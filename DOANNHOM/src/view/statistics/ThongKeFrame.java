package view.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.JTableHeader;
import model.HoaDon;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.util.Rotation;
import view.admin.HomeAdmin;

public class ThongKeFrame extends JFrame {

    private List<HoaDon> danhSachHD = new ArrayList<>();
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel chartPanelContainer;

    public ThongKeFrame() {
        setTitle("Thống kê hóa đơn và sản phẩm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLayout(new BorderLayout(10, 10));
        Color bgColor = new Color(242, 220, 194);
        getContentPane().setBackground(bgColor);
        // In ra thư mục chạy hiện tại để debug
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        
        setLayout(new BorderLayout(10, 10));

        
        tableModel = new DefaultTableModel(new Object[]{"Tên SP", "Giá", "Số lượng"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(780, 150));
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);             
        header.setForeground(Color.DARK_GRAY);         
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Khu vực nút
        JPanel buttonPanel = new JPanel();
        JButton btnCapNhat = new JButton("Cập nhật");
        JButton btnThongKe = new JButton("Thống kê");
        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnThongKe);
JButton btnTroVe = new JButton("Trở về"); // Thêm nút Trở về
buttonPanel.add(btnTroVe); // Thêm nút vào panel

btnTroVe.setBackground(new Color(240, 240, 240));
btnTroVe.setForeground(Color.BLACK);
btnTroVe.setFont(new Font("Segoe UI", Font.BOLD, 12));

// Sự kiện nút Trở về
btnTroVe.addActionListener(e -> {
    new HomeAdmin().setVisible(true); // Mở giao diện HomeAdmin
    dispose(); // Đóng giao diện thống kê hiện tại
});
        // Panel chứa biểu đồ
        chartPanelContainer = new JPanel(new BorderLayout());
        chartPanelContainer.setPreferredSize(new Dimension(800, 300));

        // Thêm các phần vào giao diện
        add(scrollPane, BorderLayout.NORTH);
        add(chartPanelContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện nút "Cập nhật"
        btnCapNhat.addActionListener(e -> {
            danhSachHD.clear();
            danhSachHD.addAll(docFileThongThuong("thanhtoan.txt"));
            danhSachHD.addAll(docFileAdmin("thanhtoanadm.txt"));
            capNhatBang();
            JOptionPane.showMessageDialog(this, "Đã cập nhật dữ liệu!");
        });

        // Sự kiện nút "Thống kê"
        btnThongKe.addActionListener(e -> {
            veBieuDoTron();
        });
        btnCapNhat.setBackground(new Color(240, 240, 240));  // màu xám nhạt
        btnCapNhat.setForeground(Color.BLACK);  // màu chữ đổi thành đen để dễ nhìn trên nền sáng
        btnCapNhat.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnThongKe.setBackground(new Color(240, 240, 240)); // màu xám nhạt
        btnThongKe.setForeground(Color.BLACK); // đổi màu chữ sang đen
        btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    private void capNhatBang() {
        tableModel.setRowCount(0);
        for (HoaDon hd : danhSachHD) {
            tableModel.addRow(new Object[]{
                hd.getTenSP(), hd.getGia(), hd.getSoLuong()
            });
        }
    }

    private void veBieuDoTron() {
        Map<String, Integer> thongKe = new HashMap<>();
        for (HoaDon hd : danhSachHD) {
            thongKe.put(hd.getTenSP(), thongKe.getOrDefault(hd.getTenSP(), 0) + hd.getSoLuong());
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String tenSP : thongKe.keySet()) {
            dataset.setValue(tenSP, thongKe.get(tenSP));
        }

        // Tạo biểu đồ hình tròn 3D
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Biểu đồ thống kê số lượng sản phẩm ", dataset, true, true, false);

        // Cài đặt giao diện
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 18));

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.8f);

        Color[] colors = {
            new Color(255, 102, 102), new Color(102, 255, 178), new Color(102, 178, 255),
            new Color(255, 255, 102), new Color(255, 153, 204), new Color(153, 255, 204),
            new Color(204, 153, 255), new Color(255, 204, 153)
        };

        int i = 0;
        for (String key : thongKe.keySet()) {
            plot.setSectionPaint(key, colors[i % colors.length]);
            i++;
        }

        // Panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(780, 400));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.WHITE);

        // Cập nhật panel
        chartPanelContainer.removeAll();
        chartPanelContainer.add(chartPanel, BorderLayout.CENTER);
        chartPanelContainer.revalidate();
        chartPanelContainer.repaint();
    }

    private List<HoaDon> docFileThongThuong(String fileName) {
        List<HoaDon> list = new ArrayList<>();
        String filePath = "C:\\Users\\Admin\\Documents\\CMU_254ZIS\\DOANNHOM\\src\\other\\" + fileName;
        System.out.println("Đang đọc file thường tại: " + filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean batDauChiTiet = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("--- Chi tiết đơn hàng ---")) {
                    batDauChiTiet = true;
                    continue;
                }
                if (batDauChiTiet && !line.trim().isEmpty() && !line.contains("Tổng tiền") && !line.contains("===")) {
                    String[] parts = line.trim().split("\\t+");
                    if (parts.length >= 4) {
                        String ten = parts[1].trim();
                        int soLuong = Integer.parseInt(parts[2].trim());
                        double gia = Double.parseDouble(parts[3].trim());
                        list.add(new HoaDon(ten, gia, soLuong));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<HoaDon> docFileAdmin(String fileName) {
        List<HoaDon> list = new ArrayList<>();
        String filePath = "C:\\Users\\Admin\\Documents\\CMU_254ZIS\\DOANNHOM\\src\\other\\" + fileName;
        System.out.println("Đang đọc file admin tại: " + filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s{2,}");
                if (parts.length >= 3) {
                    String ten = parts[0].trim();
                    double gia = Double.parseDouble(parts[1].replace(".", "").trim());
                    int soLuong = Integer.parseInt(parts[2].trim());
                    list.add(new HoaDon(ten, gia, soLuong));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThongKeFrame().setVisible(true);
        });
    }
}
