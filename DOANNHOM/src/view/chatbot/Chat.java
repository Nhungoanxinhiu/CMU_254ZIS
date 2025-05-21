package view.chatbot;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Chat extends JFrame {
    private JPanel chatPanel;  
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JButton sendButton;

    public Chat() {
        setTitle("ChatBot ");
        setSize(450, 450);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color backgroundColor = new Color(242,220,194);
        getContentPane().setBackground(backgroundColor);

  
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ô nhập và nút gửi
        inputField = new JTextField();
        inputField.setText("bạn có gì cần hỏi hãy hỏi mình nhé");
        inputField.setForeground(Color.GRAY);
        inputField.setFont(new Font("Segoe UI", Font.ITALIC, 13));

        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (inputField.getText().startsWith("bạn có gì")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                    inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                }
            }
        });

        sendButton = new JButton("Gửi");
        sendButton.setBackground(new Color(242, 220, 194));
        sendButton.setFocusPainted(false);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

    
        JButton suggestionButton = new JButton(" Gợi ý");
        suggestionButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        suggestionButton.setForeground(new Color(30, 144, 255));
        suggestionButton.setContentAreaFilled(false);
        suggestionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        suggestionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        suggestionButton.setFocusPainted(false);

        suggestionButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                suggestionButton.setForeground(new Color(0, 102, 204));
                suggestionButton.setFont(suggestionButton.getFont().deriveFont(Font.BOLD | Font.ITALIC));
            }
            public void mouseExited(MouseEvent e) {
                suggestionButton.setForeground(new Color(30, 144, 255));
                suggestionButton.setFont(suggestionButton.getFont().deriveFont(Font.BOLD));
            }
        });

        JPanel suggestionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        suggestionPanel.setBackground(backgroundColor);
        suggestionPanel.add(suggestionButton);
        add(suggestionPanel, BorderLayout.NORTH);

        suggestionButton.addActionListener(e -> openSuggestionDialog());

        
        addMessage("Bot: Xin chào bạn! Tôi có thể giúp gì hôm nay?", false);

        sendButton.addActionListener(e -> handleSend());

        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleSend();
                }
            }
        });

        setVisible(true);
    }

    private void addMessage(String message, boolean isUser) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("<html><p style=\"width:200px\">" + message + "</p></html>");
        messageLabel.setOpaque(true);
        messageLabel.setBorder(new EmptyBorder(8,12,8,12));
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        if (isUser) {
            messageLabel.setBackground(new Color(220, 248, 198)); 
            messagePanel.add(Box.createHorizontalGlue());
            messagePanel.add(messageLabel);
        } else {
            messageLabel.setBackground(new Color(240, 240, 240)); 
            messagePanel.add(messageLabel);
            messagePanel.add(Box.createHorizontalGlue());
        }

        messagePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        chatPanel.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();

        // Tự cuộn xuống cuối
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }
   private void saveChatToFile(String message) {
    String path = "C:\\Users\\Admin\\Documents\\CMU_254ZIS\\DOANNHOM\\src\\other\\chat.txt";
    // Tạo thư mục nếu chưa tồn tại
    File file = new File(path);
    File parentDir = file.getParentFile();
    if (!parentDir.exists()) {
        parentDir.mkdirs();
    }
   try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
        writer.write(message);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }}

    private void handleSend() {
    String userInput = inputField.getText().trim();
    if (!userInput.isEmpty()) {
        String userMsg = "Bạn: " + userInput;
        addMessage(userMsg, true);
        saveChatToFile(userMsg);

        String botResponse = "Bot: " + generateResponse(userInput);
        addMessage(botResponse, false);
        saveChatToFile(botResponse);

        inputField.setText("");
    }
}

    private String generateResponse(String input) {
        if (input.equalsIgnoreCase("Thực đơn")) {
            return "Dưới đây là thực đơn: Trà sữa, Trà trái cây, coffee, matcha... bạn tham khảo thêm ở menu nhé bạn yêu";
        } else if (input.equalsIgnoreCase("Khuyến mãi")) {
            return "Hiện tại bạn không có mã giảm giá nào!";
        } else if (input.toLowerCase().contains("giá")) {
            return "Giá của shop giao đông từ 30-45.000 VNĐ bạn tham khảo ở menu nha.";
        } else if (input.equalsIgnoreCase("Gợi ý món ngon")) {
            return "Bạn có thể thử Trà sữa trân châu đường đen, rất ngon và nổi tiếng!";
        } else {
            return "Xin lỗi, tôi chưa hiểu. Bạn có thể hỏi lại hoặc gõ 'Thực đơn' để xem lựa chọn.";
        }
    }

  

    private void openSuggestionDialog() {
        JDialog dialog = new JDialog(this, "Gợi ý từ Chatbot", true);
        dialog.setSize(380, 280);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(30, 144, 255));
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 20, 20);
                g2.dispose();
            }
        };
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String suggestionText = " Gợi ý:\n\n"
                + "• Gõ 'Thực đơn' để xem danh sách món\n"
                + "• Gõ 'Gợi ý món ngon' để được tư vấn\n"
                + "• Gõ 'Giá ' để hỏi giá\n"
                + "• Gõ 'Khuyến mãi' để xem ưu đãi hiện tại";

        JTextArea suggestionArea = new JTextArea(suggestionText);
        suggestionArea.setEditable(false);
        suggestionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        suggestionArea.setLineWrap(true);
        suggestionArea.setWrapStyleWord(true);
        suggestionArea.setBorder(new EmptyBorder(10,10,10,10));
        suggestionArea.setBackground(new Color(245, 245, 245));
        suggestionArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(suggestionArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton copyBtn = new JButton(" Copy");
        copyBtn.setFocusPainted(false);
        copyBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        copyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        copyBtn.setBackground(new Color(30, 144, 255));
        copyBtn.setForeground(Color.WHITE);
        copyBtn.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        copyBtn.setOpaque(true);

        copyBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                copyBtn.setBackground(new Color(0, 102, 204));
            }
            public void mouseExited(MouseEvent e) {
                copyBtn.setBackground(new Color(30, 144, 255));
            }
        });

        copyBtn.addActionListener(evt -> {
            StringSelection stringSelection = new StringSelection(suggestionArea.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(dialog, "Đã sao chép vào clipboard!", "Copy thành công", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton closeBtn = new JButton("✖ Đóng");
        closeBtn.setFocusPainted(false);
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.setBackground(new Color(220, 53, 69));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        closeBtn.setOpaque(true);

        closeBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                closeBtn.setBackground(new Color(200, 35, 51));
            }
            public void mouseExited(MouseEvent e) {
                closeBtn.setBackground(new Color(220, 53, 69));
            }
        });

        closeBtn.addActionListener(evt -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(copyBtn);
        buttonPanel.add(closeBtn);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(mainPanel);
        dialog.setVisible(true);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Chat::new);
    }
}
