package com.faculty.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboardView extends JFrame {

    // --- COLORS ---
    private final Color PRIMARY_PURPLE = new Color(124, 77, 255);
    private final Color HOVER_PURPLE = new Color(150, 100, 255);
    private final Color ACCENT_PURPLE = new Color(140, 82, 255);
    private final Color BG_GREY = new Color(245, 245, 250);
    private final Color TEXT_DARK = new Color(50, 50, 50);

    // --- STATE TRACKING ---
    private JButton activeSidebarButton = null;

    // --- COMPONENTS ---
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JButton btnStudents, btnLecturers, btnCourses, btnDepartments, btnDegrees, btnLogout;

    public final ManagementPanel studentPanel;
    public final ManagementPanel lecturerPanel;
    public final ManagementPanel coursePanel;
    public final ManagementPanel deptPanel;
    public final ManagementPanel degreePanel;

    public AdminDashboardView() {
        setTitle("Faculty Management System - Admin Dashboard");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Sidebar
        add(createSidebar(), BorderLayout.WEST);

        // 2. Content Area
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG_GREY);

        // 3. Initialize Panels
        studentPanel = createManagementPanel("Students", new String[]{"Full Name", "Student ID", "Degree", "Email", "Mobile Number"});
        lecturerPanel = createManagementPanel("Lecturers", new String[]{"Full Name", "Department", "Courses Teaching", "Email", "Mobile Number"});
        coursePanel = createManagementPanel("Courses", new String[]{"Course Code", "Course Name", "Credits", "Lecturer"});
        deptPanel = createManagementPanel("Departments", new String[]{"Name", "HOD", "Degree", "No of Staff"});
        degreePanel = createManagementPanel("Degrees", new String[]{"Degree", "Department", "No of Students"});

        // 4. Add to CardLayout
        contentPanel.add(studentPanel.mainPanel, "STUDENTS");
        contentPanel.add(lecturerPanel.mainPanel, "LECTURERS");
        contentPanel.add(coursePanel.mainPanel, "COURSES");
        contentPanel.add(deptPanel.mainPanel, "DEPARTMENTS");
        contentPanel.add(degreePanel.mainPanel, "DEGREES");

        add(contentPanel, BorderLayout.CENTER);
    }

    // ==========================================
    // UI BUILDER: WHITE CARD & TABLE
    // ==========================================
    private ManagementPanel createManagementPanel(String titleStr, String[] columns) {
        JPanel outerWrapper = new JPanel(new GridBagLayout());
        outerWrapper.setBackground(BG_GREY);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(950, 600));
        card.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        JLabel title = new JLabel(titleStr);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PRIMARY_PURPLE);

        JPanel buttonBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonBox.setBackground(Color.WHITE);
        JButton btnAdd = createRoundedButton("Add new", PRIMARY_PURPLE);
        JButton btnEdit = createRoundedButton("Edit", Color.LIGHT_GRAY);
        JButton btnDelete = createRoundedButton("Delete", Color.LIGHT_GRAY);
        buttonBox.add(btnAdd); buttonBox.add(btnEdit); buttonBox.add(btnDelete);
        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(buttonBox, BorderLayout.EAST);
        card.add(headerPanel, BorderLayout.NORTH);

        // Table
        DefaultTableModel model = new DefaultTableModel(null, columns) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(model);
        styleTable(table);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
        sp.getViewport().setBackground(Color.WHITE);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(Color.WHITE);
        tableWrapper.setBorder(new EmptyBorder(25, 0, 25, 0));
        tableWrapper.add(sp, BorderLayout.CENTER);
        card.add(tableWrapper, BorderLayout.CENTER);

        // Footer
        JButton btnSave = createRoundedButton("Save changes", ACCENT_PURPLE);
        btnSave.setPreferredSize(new Dimension(300, 50));
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(btnSave);
        card.add(footerPanel, BorderLayout.SOUTH);

        outerWrapper.add(card);
        return new ManagementPanel(outerWrapper, table, model, btnAdd, btnEdit, btnDelete, btnSave);
    }

    // ==========================================
    // UI BUILDER: SIDEBAR
    // ==========================================
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(PRIMARY_PURPLE);
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(40, 20, 40, 20));

        JLabel lblWelcome = new JLabel("Welcome,");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblWelcome.setForeground(new Color(220, 220, 220));
        lblWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lblAdmin = new JLabel("Admin");
        lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblAdmin.setForeground(Color.WHITE);
        lblAdmin.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(lblWelcome);
        sidebar.add(lblAdmin);
        sidebar.add(Box.createVerticalStrut(60));

        btnStudents = createSidebarButton("Students");
        btnLecturers = createSidebarButton("Lecturers");
        btnCourses = createSidebarButton("Courses");
        btnDepartments = createSidebarButton("Departments");
        btnDegrees = createSidebarButton("Degrees");

        sidebar.add(btnStudents); sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(btnLecturers); sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(btnCourses); sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(btnDepartments); sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(btnDegrees);

        sidebar.add(Box.createVerticalGlue());
        btnLogout = createSidebarButton("Sign Out");
        btnLogout.setBackground(PRIMARY_PURPLE);
        btnLogout.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        sidebar.add(btnLogout);

        return sidebar;
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(PRIMARY_PURPLE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new RoundedBorder(Color.WHITE, 15));

        // HOVER LOGIC
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn != activeSidebarButton) {
                    btn.setBackground(HOVER_PURPLE);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (btn != activeSidebarButton) {
                    btn.setBackground(PRIMARY_PURPLE);
                }
            }
        });

        return btn;
    }

    // --- METHOD MISSING IN YOUR ERROR ---
    public void setActiveTab(JButton newActiveBtn) {
        if (activeSidebarButton != null) {
            activeSidebarButton.setBackground(PRIMARY_PURPLE);
            activeSidebarButton.setForeground(Color.WHITE);
        }
        activeSidebarButton = newActiveBtn;
        activeSidebarButton.setBackground(Color.WHITE);
        activeSidebarButton.setForeground(PRIMARY_PURPLE);
    }

    // ==========================================
    // STYLING & HELPERS
    // ==========================================
    private JButton createRoundedButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setForeground(PRIMARY_PURPLE);
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(new Color(230, 230, 250));
        table.setShowVerticalLines(true);
        table.setSelectionBackground(new Color(240, 240, 255));
        table.setSelectionForeground(TEXT_DARK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    public void switchScreen(String screenName) { cardLayout.show(contentPanel, screenName); }
    public void addNavListener(JButton btn, ActionListener l) { btn.addActionListener(l); }

    public JButton getBtnStudents() { return btnStudents; }
    public JButton getBtnLecturers() { return btnLecturers; }
    public JButton getBtnCourses() { return btnCourses; }
    public JButton getBtnDepartments() { return btnDepartments; }
    public JButton getBtnDegrees() { return btnDegrees; }
    public JButton getBtnLogout() { return btnLogout; }

    // Dialog Helpers
    public String showInputDialog(String msg, String initialValue) {
        return (String) JOptionPane.showInputDialog(this, msg, "Edit Details", JOptionPane.PLAIN_MESSAGE, null, null, initialValue);
    }
    public String showInputDialog(String msg) { return JOptionPane.showInputDialog(this, msg); }
    public boolean showConfirmDialog(String msg) { return JOptionPane.showConfirmDialog(this, msg, "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION; }
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void close() { dispose(); }

    public static class ManagementPanel {
        public JPanel mainPanel;
        public JTable table;
        public DefaultTableModel model;
        public JButton btnAdd, btnEdit, btnDelete, btnSave;

        public ManagementPanel(JPanel mainPanel, JTable table, DefaultTableModel model, JButton btnAdd, JButton btnEdit, JButton btnDelete, JButton btnSave) {
            this.mainPanel = mainPanel;
            this.table = table;
            this.model = model;
            this.btnAdd = btnAdd;
            this.btnEdit = btnEdit;
            this.btnDelete = btnDelete;
            this.btnSave = btnSave;
        }
    }

    private static class RoundedBorder implements Border {
        private int radius;
        private Color color;
        public RoundedBorder(Color color, int radius) { this.color = color; this.radius = radius; }
        public Insets getBorderInsets(Component c) { return new Insets(radius/2, radius/2, radius/2, radius/2); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}