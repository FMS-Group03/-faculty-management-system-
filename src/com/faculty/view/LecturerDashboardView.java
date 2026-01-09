package com.faculty.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LecturerDashboardView extends JFrame {

    private final Color PRIMARY_PURPLE = new Color(140, 82, 255);
    private final Color SIDEBAR_PURPLE = new Color(140, 82, 255);
    private final Color TEXT_GRAY = new Color(150, 150, 150);

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String lecturerName;
    private JPanel btnProfile, btnSchedule, btnCourses;

    private JButton profileSaveButton;
    private JTextField tfName, tfLecturerId, tfDepartment, tfEmail, tfMobile;

    private DefaultTableModel scheduleModel;
    private JButton btnUpdateSchedule;

    private DefaultTableModel courseModel;
    private JTextField tfCourseCode, tfCourseName;
    private JButton btnAddCourse;

    public LecturerDashboardView(String name) {
        this.lecturerName = name;
        setTitle("Lecturer Dashboard - Faculty Management System");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);

        cardPanel.add(createProfilePanel(), "Profile");
        cardPanel.add(createSchedulePanel(), "Schedule");
        cardPanel.add(createCoursesPanel(), "MyCourses");

        add(cardPanel, BorderLayout.CENTER);
        showCard("Profile", btnProfile);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_PURPLE);
        sidebar.setPreferredSize(new Dimension(300, 700));
        sidebar.setLayout(null);

        JLabel lblIcon = new JLabel("👤", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 95));
        lblIcon.setForeground(Color.WHITE);
        lblIcon.setBounds(0, 20, 300, 120);
        sidebar.add(lblIcon);

        JLabel lblWelcome = new JLabel("Welcome, " + lecturerName, SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(0, 130, 300, 40);
        sidebar.add(lblWelcome);

        btnProfile = createNavButton("👤", "Profile Details", 230, "Profile");
        btnSchedule = createNavButton("📅", "Schedule", 300, "Schedule");
        btnCourses = createNavButton("📖", "My Courses", 370, "MyCourses");

        sidebar.add(btnProfile);
        sidebar.add(btnSchedule);
        sidebar.add(btnCourses);

        // Logout Button Implementation
        JButton btnLogout = new JButton("\u21B3") {
            @Override
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
        btnLogout.setBounds(115, 600, 70, 70);
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

    private JPanel createSchedulePanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        JLabel title = new JLabel("Weekly Teaching Schedule", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 30, 800, 50);
        p.add(title);

        String[] cols = {"Day", "Time Slot", "Subject / Hall"};
        scheduleModel = new DefaultTableModel(cols, 0);
        scheduleModel.addRow(new Object[]{"Monday", "08:00 - 10:00", "OOP - Hall A"});
        scheduleModel.addRow(new Object[]{"Wednesday", "13:00 - 15:00", "Data Structures - Hall B"});

        JTable table = new JTable(scheduleModel);
        styleTable(table);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 100, 700, 300);
        p.add(sp);

        btnUpdateSchedule = createStyledButton("Confirm Schedule Changes", 200, 450, 400, 50);
        p.add(btnUpdateSchedule);
        return p;
    }

    private JPanel createCoursesPanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        JLabel title = new JLabel("My Conducted Courses", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 30, 800, 50);
        p.add(title);

        p.add(createLabel("Course Code", 100));
        tfCourseCode = new JTextField();
        tfCourseCode.setBounds(200, 100, 150, 35);
        tfCourseCode.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 15));
        p.add(tfCourseCode);

        p.add(createLabel("Course Name", 100, 370));
        tfCourseName = new JTextField();
        tfCourseName.setBounds(500, 100, 250, 35);
        tfCourseName.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 15));
        p.add(tfCourseName);

        btnAddCourse = createStyledButton("Add New Course", 250, 160, 300, 40);
        p.add(btnAddCourse);

        String[] cols = {"Course Code", "Course Name"};
        courseModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(courseModel);
        styleTable(table);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 230, 700, 250);
        p.add(sp);
        return p;
    }

    private JPanel createProfilePanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        JLabel title = new JLabel("Profile Details", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 35));
        title.setForeground(PRIMARY_PURPLE);
        title.setBounds(0, 40, 800, 50);
        p.add(title);

        int y = 120;
        tfName = addField(p, "Full Name", y); y += 65;
        tfLecturerId = addField(p, "Lecturer ID", y); y += 65;
        tfDepartment = addField(p, "Department", y); y += 65;
        tfEmail = addField(p, "Email", y); y += 65;
        tfMobile = addField(p, "Mobile Number", y);

        profileSaveButton = createStyledButton("Save Changes", 230, y + 80, 350, 50);
        p.add(profileSaveButton);
        return p;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(40);
        table.setGridColor(PRIMARY_PURPLE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTableHeader header = table.getTableHeader();
        header.setBackground(PRIMARY_PURPLE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
    }

    private JLabel createLabel(String text, int y) { return createLabel(text, y, 50); }
    private JLabel createLabel(String text, int y, int x) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l.setForeground(PRIMARY_PURPLE);
        l.setBounds(x, y, 150, 30);
        return l;
    }

    private JTextField addField(JPanel p, String labelText, int y) {
        p.add(createLabel(labelText, y));
        JTextField tf = new JTextField();
        tf.setBounds(230, y, 400, 40);
        tf.setBorder(new DashRoundedBorder(PRIMARY_PURPLE, 20));
        p.add(tf);
        return tf;
    }

    private JPanel createNavButton(String icon, String text, int y, String cardName) {
        JPanel btn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(20, y, 260, 50);
        btn.setBackground(Color.WHITE);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        lblIcon.setBounds(15, 5, 40, 40);
        btn.add(lblIcon);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblText.setForeground(TEXT_GRAY);
        lblText.setBounds(60, 5, 180, 40);
        btn.add(lblText);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { showCard(cardName, btn); }
        });
        return btn;
    }

    private void showCard(String cardName, JPanel activeBtn) {
        cardLayout.show(cardPanel, cardName);
        for (JPanel btn : new JPanel[]{btnProfile, btnSchedule, btnCourses}) {
            btn.getComponent(1).setForeground(TEXT_GRAY);
        }
        activeBtn.getComponent(1).setForeground(PRIMARY_PURPLE);
    }

    private JButton createStyledButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text) {
            @Override
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
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getBtnUpdateSchedule() { return btnUpdateSchedule; }
    public JButton getBtnAddCourse() { return btnAddCourse; }
    public JButton getProfileSaveButton() { return profileSaveButton; }
    public JTextField getTfCourseCode() { return tfCourseCode; }
    public JTextField getTfCourseName() { return tfCourseName; }
    public DefaultTableModel getCourseModel() { return courseModel; }
    public DefaultTableModel getScheduleModel() { return scheduleModel; }
    public JTextField[] getProfileFields() {
        return new JTextField[]{tfName, tfLecturerId, tfDepartment, tfEmail, tfMobile};
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
}