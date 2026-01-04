package com.faculty.view;

 CS_2022_042
public class AdminDashboardView {
}

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDashboardView extends JFrame {

    // --- COLORS ---
    private final Color PRIMARY_PURPLE = new Color(124, 77, 255);
    private final Color BG_WHITE = Color.WHITE;
    private final Color BTN_GRAY = new Color(180, 180, 180);

    // --- COMPONENTS ---
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Sidebar Buttons
    private JButton btnStudents, btnLecturers, btnCourses, btnDepartments, btnDegrees, btnLogout;

    // We store the UI components for each section in a "ManagementPanel" object
    // This makes it easy for the Controller to access specific tables and buttons.
    public final ManagementPanel studentPanel;
    public final ManagementPanel lecturerPanel;
    public final ManagementPanel coursePanel;
    public final ManagementPanel deptPanel;
    public final ManagementPanel degreePanel;

    public AdminDashboardView() {
        setTitle("Admin Dashboard - Faculty Management System");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        // 1. Sidebar
        add(createSidebar(), BorderLayout.WEST);

        // 2. Content Area
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG_WHITE);

        // 3. Create Panels
        studentPanel = createManagementPanel("Students",
                new String[]{"Full Name", "Student ID", "Degree", "Email", "Mobile"},
                new String[][]{{"Kumar Sangakkara", "ET/2022/007", "Eng Tech", "k@k.lk", "0771234567"}});

        lecturerPanel = createManagementPanel("Lecturers",
                new String[]{"Full Name", "Department", "Courses", "Email", "Mobile"},
                new String[][]{{"Dr. Mahela", "Software Eng", "OOP", "m@k.lk", "0777654321"}});

        coursePanel = createManagementPanel("Courses",
                new String[]{"Code", "Name", "Credits", "Lecturer"},
                new String[][]{{"ETEC21062", "OOP", "2", "Dr. Mahela"}});

        deptPanel = createManagementPanel("Departments",
                new String[]{"Name", "HOD", "Degree", "Staff Count"},
                new String[][]{{"Software Eng", "Dr. Mahela", "IT", "15"}});

        degreePanel = createManagementPanel("Degrees",
                new String[]{"Degree", "Department", "Students"},
                new String[][]{{"Eng Tech", "Applied Computing", "375"}});

        // 4. Add to CardLayout
        contentPanel.add(studentPanel.mainPanel, "STUDENTS");
        contentPanel.add(lecturerPanel.mainPanel, "LECTURERS");
        contentPanel.add(coursePanel.mainPanel, "COURSES");
        contentPanel.add(deptPanel.mainPanel, "DEPARTMENTS");
        contentPanel.add(degreePanel.mainPanel, "DEGREES");

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // ==========================================
    // UI BUILDER HELPERS
    // ==========================================

    /**
     * Creates a standard management panel (Title + Buttons + Table)
     */
    private ManagementPanel createManagementPanel(String titleStr, String[] columns, String[][] data) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_WHITE);
        wrapper.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Title
        JLabel title = new JLabel(titleStr, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PRIMARY_PURPLE);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Top Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(BG_WHITE);

        JButton btnAdd = createActionButton("Add new", PRIMARY_PURPLE);
        JButton btnEdit = createActionButton("Edit", BTN_GRAY);
        JButton btnDelete = createActionButton("Delete", BTN_GRAY);

        actionPanel.add(btnAdd);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);

        JPanel topBox = new JPanel(new BorderLayout());
        topBox.setBackground(BG_WHITE);
        topBox.add(title, BorderLayout.NORTH);
        topBox.add(actionPanel, BorderLayout.CENTER);
        wrapper.add(topBox, BorderLayout.NORTH);

        // Table
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        styleTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new LineBorder(PRIMARY_PURPLE, 1));
        sp.getViewport().setBackground(Color.WHITE);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(BG_WHITE);
        tableWrapper.setBorder(new EmptyBorder(20, 0, 20, 0));
        tableWrapper.add(sp, BorderLayout.CENTER);
        wrapper.add(tableWrapper, BorderLayout.CENTER);

        // Save Button
        JButton btnSave = new JButton("Save changes");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSave.setBackground(PRIMARY_PURPLE);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new Dimension(300, 50));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BG_WHITE);
        bottomPanel.add(btnSave);
        wrapper.add(bottomPanel, BorderLayout.SOUTH);

        // Return the logic container
        return new ManagementPanel(wrapper, table, model, btnAdd, btnEdit, btnDelete, btnSave);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(PRIMARY_PURPLE);
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel welcome = new JLabel("Welcome, Admin");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(welcome);
        sidebar.add(Box.createVerticalStrut(40));

        btnStudents = createSidebarButton("   Students", true);
        btnLecturers = createSidebarButton("   Lecturers", false);
        btnCourses = createSidebarButton("   Courses", false);
        btnDepartments = createSidebarButton("   Departments", false);
        btnDegrees = createSidebarButton("   Degrees", false);
        btnLogout = createSidebarButton("   Logout", false);

        sidebar.add(btnStudents); sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnLecturers); sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnCourses); sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnDepartments); sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnDegrees);

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        return sidebar;
    }

    private JButton createSidebarButton(String text, boolean isActive) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBackground(isActive ? Color.WHITE : PRIMARY_PURPLE);
        btn.setForeground(isActive ? PRIMARY_PURPLE : Color.WHITE);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        return btn;
    }

    private JButton createActionButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 25, 10, 25));
        return btn;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(PRIMARY_PURPLE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setForeground(PRIMARY_PURPLE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // ==========================================
    // EXPOSED METHODS FOR CONTROLLER
    // ==========================================

    // Navigation Logic
    public void switchScreen(String screenName) { cardLayout.show(contentPanel, screenName); }
    public void addNavListener(JButton btn, ActionListener l) { btn.addActionListener(l); }

    // Getters for Sidebar Buttons
    public JButton getBtnStudents() { return btnStudents; }
    public JButton getBtnLecturers() { return btnLecturers; }
    public JButton getBtnCourses() { return btnCourses; }
    public JButton getBtnDepartments() { return btnDepartments; }
    public JButton getBtnDegrees() { return btnDegrees; }
    public JButton getBtnLogout() { return btnLogout; }

    // Dialogs
    public String showInputDialog(String msg, String initial) {
        return JOptionPane.showInputDialog(this, msg, initial);
    }
    public boolean showConfirmDialog(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    public void close() { dispose(); }

    // ==========================================
    // INNER CLASS: ManagementPanel
    // ==========================================
    public static class ManagementPanel {
        public JPanel mainPanel;
        public JTable table;
        public DefaultTableModel model;
        public JButton btnAdd, btnEdit, btnDelete, btnSave;

        public ManagementPanel(JPanel mainPanel, JTable table, DefaultTableModel model,
                               JButton btnAdd, JButton btnEdit, JButton btnDelete, JButton btnSave) {
            this.mainPanel = mainPanel;
            this.table = table;
            this.model = model;
            this.btnAdd = btnAdd;
            this.btnEdit = btnEdit;
            this.btnDelete = btnDelete;
            this.btnSave = btnSave;
        }
    }
}
 main
