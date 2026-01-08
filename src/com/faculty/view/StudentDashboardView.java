
package com.faculty.view;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class StudentDashboardView extends JFrame {

    private final Color PRIMARY_PURPLE = new Color(140, 82, 255);
    private final Color SIDEBAR_PURPLE = new Color(140, 82, 255);
    private final Color TEXT_GRAY = new Color(150, 150, 150);

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String studentName;
    private JPanel btnProfile, btnTime, btnCourse;

    // me last do night
    private JButton profileSaveButton;
    private JTextField tfName;
    private JTextField tfId;
    private JTextField tfDegree;
    private JTextField tfEmail;
    private JTextField tfMobile;


    public StudentDashboardView(String name) {
        this.studentName = name;
        setTitle("Student Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);

        cardPanel.add(createProfilePanel(), "Profile");
        cardPanel.add(createTimetablePanel(), "Timetable");
        cardPanel.add(createCoursesPanel(), "Courses");

        add(cardPanel, BorderLayout.CENTER);
        showCard("Profile", btnProfile);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_PURPLE);
        sidebar.setPreferredSize(new Dimension(320, 700));
        sidebar.setLayout(null);

        // Big User Avatar Icon
        JLabel lblIcon = new JLabel("👤", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 95));
        lblIcon.setForeground(Color.WHITE);
        lblIcon.setBounds(0, 20, 320, 120);
        sidebar.add(lblIcon);

        JLabel lblWelcome = new JLabel("Welcome, " + studentName, SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(0, 130, 320, 50);
        sidebar.add(lblWelcome);

        // Navigation Buttons
        btnProfile = createNavButton("👤", "Profile Details", 230, "Profile");
        btnTime = createNavButton("📅", "Time table", 310, "Timetable");
        btnCourse = createNavButton("📖", "Course Enrolled", 390, "Courses");

        sidebar.add(btnProfile);
        sidebar.add(btnTime);
        sidebar.add(btnCourse);

        // UPDATED LOGOUT BUTTON (Matching the Photo Exit Icon)
        JButton btnLogout = new JButton("\u21B3") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnLogout.setFont(new Font("Segoe UI Symbol", Font.BOLD, 35));
        btnLogout.setForeground(PRIMARY_PURPLE);
        btnLogout.setBounds(125, 550, 70, 70);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> {
            this.dispose();
            new LoginView().setVisible(true);
        });
        sidebar.add(btnLogout);

        return sidebar;
    }

    private JPanel createNavButton(String icon, String text, int y, String cardName) {
        JPanel btn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Enable anti-aliasing for smooth corners
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                // Draw the rounded rectangle (Change 40, 40 to adjust radius)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setBounds(20, y, 280, 55);
        btn.setBackground(Color.WHITE);
        btn.setOpaque(false); // 2. Vital: This makes the sharp rectangular corners transparent
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Note: I removed the DashRoundedBorder line because the custom paint
        // now handles the shape perfectly without needing an outline.

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        lblIcon.setForeground(TEXT_GRAY);
        lblIcon.setBounds(15, 5, 40, 45);
        btn.add(lblIcon);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblText.setForeground(TEXT_GRAY);
        lblText.setBounds(65, 5, 200, 45);
        btn.add(lblText);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showCard(cardName, btn);
            }
        });
        return btn;
    }

    //me do private to public
    public void showCard(String cardName, JPanel activeBtn) {
        cardLayout.show(cardPanel, cardName);
        for (JPanel btn : new JPanel[]{btnProfile, btnTime, btnCourse}) {
            btn.getComponent(0).setForeground(TEXT_GRAY);
            btn.getComponent(1).setForeground(TEXT_GRAY);
        }
        activeBtn.getComponent(0).setForeground(PRIMARY_PURPLE);
        activeBtn.getComponent(1).setForeground(PRIMARY_PURPLE);
    }

    // Sub-panels
    private JPanel createProfilePanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);

        JLabel title = new JLabel("Profile Details", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 35));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 40, 780, 50);
        p.add(title);

        int y = 140;

        JLabel l1 = new JLabel("Full Name");
        l1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l1.setForeground(PRIMARY_PURPLE);
        l1.setBounds(60, y, 150, 30);
        p.add(l1);

        tfName = new JTextField("");
        tfName.setBounds(230, y, 480, 45);
        tfName.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tfName);
        y += 70;

        JLabel l2 = new JLabel("Student ID");
        l2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l2.setForeground(PRIMARY_PURPLE);
        l2.setBounds(60, y, 150, 30);
        p.add(l2);

        tfId = new JTextField("");
        tfId.setBounds(230, y, 480, 45);
        tfId.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tfId);
        y += 70;

        JLabel l3 = new JLabel("Degree");
        l3.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l3.setForeground(PRIMARY_PURPLE);
        l3.setBounds(60, y, 150, 30);
        p.add(l3);

        tfDegree = new JTextField("");
        tfDegree.setBounds(230, y, 480, 45);
        tfDegree.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tfDegree);
        y += 70;

        JLabel l4 = new JLabel("Email");
        l4.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l4.setForeground(PRIMARY_PURPLE);
        l4.setBounds(60, y, 150, 30);
        p.add(l4);

        tfEmail = new JTextField("");
        tfEmail.setBounds(230, y, 480, 45);
        tfEmail.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tfEmail);
        y += 70;

        JLabel l5 = new JLabel("Mobile Number");
        l5.setFont(new Font("Segoe UI", Font.BOLD, 20));
        l5.setForeground(PRIMARY_PURPLE);
        l5.setBounds(60, y, 150, 30);
        p.add(l5);

        tfMobile = new JTextField("");
        tfMobile.setBounds(230, y, 480, 45);
        tfMobile.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tfMobile);

        profileSaveButton = createStyledButton("Save changes", 230, y + 90, 480, 55);
        p.add(profileSaveButton);

        return p;
    }




    private JPanel createTimetablePanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);

        JLabel title = new JLabel("Time table", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 40, 780, 50);
        p.add(title);

        String[] cols = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        Object[][] data = {
                {"08.00", "OOP", "Network", "DataBase", "Stastic", "OOP"},
                {"10.00", "Network", "Static", "DSA", "OOP", "Network"},
                {"", "", "", "INTERVAL", "", ""},
                {"01.00", "English", "Database", "SE", "English", "Management"},
                {"03.00", "SE", "Management", "SE", "DSA", "Management"}
        };

        JTable table = new JTable(data, cols);
        styleTable(table);

        // Custom renderer for INTERVAL row
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Check if this row contains "INTERVAL"
                boolean isIntervalRow = false;
                for (int col = 0; col < table.getColumnCount(); col++) {
                    if ("INTERVAL".equals(table.getValueAt(row, col))) {
                        isIntervalRow = true;
                        break;
                    }
                }
                if (isIntervalRow) {
                    c.setBackground(PRIMARY_PURPLE);
                    c.setForeground(Color.WHITE);
                    setHorizontalAlignment(SwingConstants.CENTER); // center text
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(PRIMARY_PURPLE);
                    setHorizontalAlignment(SwingConstants.LEFT); // default alignment
                }
                return c;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40, 130, 700, 280);
        sp.setBorder(BorderFactory.createLineBorder(PRIMARY_PURPLE, 2));

        sp.getViewport().setBackground(Color.WHITE);

        p.add(sp);
        return p;
    }



    private JPanel createCoursesPanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        JLabel title = new JLabel("Courses Enrolled", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 35));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 40, 780, 50);
        p.add(title);

        String[] cols = {"Course code", "Course name", "Credits", "Grade"};
        Object[][] data = {
                {"CSCI 21062", "OOP", "3", "A"}, {"CSCI 21052", "Network", "2", "B"},
                {"CSCI 21042", "SE", "2", "A-"}, {"CSCI 21032", "DataBase", "3", "B-"},
                {"CSCI 21022", "Statics", "3", "A"}, {"CSCI 21012", "DSA", "3", "A"},
                {"CSCI 21100", "English", "2", "C"}, {"CSCI 21101", "Manegement", "2", "C+"}

        };
        JTable table = new JTable(data, cols);
        styleTable(table);

        // --- FIX FOR GRAY AREA (Added here for consistency) ---
        table.setFillsViewportHeight(true);
        // ----------------------------------------------------

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40, 130, 700, 385);
        sp.setBorder(BorderFactory.createLineBorder(PRIMARY_PURPLE, 2));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Ensure viewport background is white
        sp.getViewport().setBackground(Color.WHITE);

        p.add(sp);
        return p;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(50);
        table.setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.setForeground(PRIMARY_PURPLE);
        table.setGridColor(PRIMARY_PURPLE);
        table.setShowGrid(true);

        // STYLING THE HEADER
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(PRIMARY_PURPLE);
        header.setBackground(Color.WHITE);

        // FIX: Added explicit border to the header to show the missing bottom and side lines
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, PRIMARY_PURPLE));

        // CENTERING COLUMN NAMES (HEADERS)
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // CENTERING DATA ROWS
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }


    private JButton createStyledButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(PRIMARY_PURPLE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    public static class DashRoundedBorder implements javax.swing.border.Border {
        private int radius;
        private Color color;
        public DashRoundedBorder(Color color, int radius) { this.color = color; this.radius = radius; }
        public Insets getBorderInsets(Component c) { return new Insets(5, 15, 5, 15); }
        public boolean isBorderOpaque() { return true; }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }


    // me last do night
    public JPanel getBtnProfile() { return btnProfile; }
    public JPanel getBtnTime() { return btnTime; }
    public JPanel getBtnCourse() { return btnCourse; }
    public JButton getProfileSaveButton() { return profileSaveButton; }

    public JTextField[] getProfileFields() {
        return new JTextField[]{
                tfName,
                tfId,
                tfDegree,
                tfEmail,
                tfMobile
        };
    }


    // me last do night
    public JTextField getTfName() { return tfName; }
    public JTextField getTfId() { return tfId; }
    public JTextField getTfDegree() { return tfDegree; }
    public JTextField getTfEmail() { return tfEmail; }
    public JTextField getTfMobile() { return tfMobile; }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }




}