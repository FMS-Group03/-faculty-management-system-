package com.faculty.view;

import com.faculty.controller.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame implements ActionListener {

    private final Color PRIMARY_PURPLE = new Color(124, 77, 255);
    private final Color BG_LIGHT_PURPLE = new Color(140, 82, 255);

    private final Color BTN_DEFAULT = new Color(140, 82, 255);
    private final Color BTN_HOVER = new Color(160, 110, 255);
    private final Color BTN_CLICK = new Color(100, 60, 200);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel lblUser, lblPass, lblConfirmPass, lblRole;
    private JButton btnSignIn;
    private JToggleButton tglAdmin, tglStudent, tglLecturer;
    private ButtonGroup roleGroup;
    private JPanel rolePanel;

    private JLabel lblSignInTab;
    private JLabel lblSignUpTab;

    public LoginView() {
        setTitle("Faculty Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        initLeftPanel();
        initRightPanel();

    }

    private void initLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(BG_LIGHT_PURPLE);
        leftPanel.setLayout(null);

        int panelWidth = 500;

        JLabel lblIcon = new JLabel("🎓", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        lblIcon.setForeground(Color.WHITE);
        lblIcon.setBounds(0, 160, panelWidth, 140);
        leftPanel.add(lblIcon);

        JLabel lblTitle = new JLabel("Faculty Management", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 280, panelWidth, 50);
        leftPanel.add(lblTitle);

        JLabel lblTitle2 = new JLabel("System", SwingConstants.CENTER);
        lblTitle2.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle2.setForeground(Color.WHITE);
        lblTitle2.setBounds(0, 330, panelWidth, 50);
        leftPanel.add(lblTitle2);

        JLabel lblSub = new JLabel("Faculty of Computing & Technology", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblSub.setForeground(Color.WHITE);
        lblSub.setBounds(0, 550, panelWidth, 30);
        leftPanel.add(lblSub);

        JLabel lblTag = new JLabel("Manage your academic journey", SwingConstants.CENTER);
        lblTag.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTag.setForeground(new Color(230, 230, 230));
        lblTag.setBounds(0, 590, panelWidth, 30);
        leftPanel.add(lblTag);

        add(leftPanel);
    }

    private void initRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        lblSignInTab = new JLabel("     Sign In     ");
        lblSignInTab.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblSignInTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSignInTab.setBounds(60, 30, 180, 40);

        lblSignUpTab = new JLabel("     Sign Up     ");
        lblSignUpTab.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblSignUpTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSignUpTab.setBounds(260, 30, 200, 40);

        lblSignInTab.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { toggleAuthMode("SignIn"); }
        });
        lblSignUpTab.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { toggleAuthMode("SignUp"); }
        });

        rightPanel.add(lblSignInTab);
        rightPanel.add(lblSignUpTab);

        lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblUser.setForeground(PRIMARY_PURPLE);
        rightPanel.add(lblUser);

        txtUsername = new JTextField();
        styleTextField(txtUsername);
        txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtUsername.setForeground(PRIMARY_PURPLE);
        rightPanel.add(txtUsername);

        lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblPass.setForeground(PRIMARY_PURPLE);
        rightPanel.add(lblPass);

        txtPassword = new JPasswordField();
        styleTextField(txtPassword);
        txtPassword.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtPassword.setForeground(PRIMARY_PURPLE);
        rightPanel.add(txtPassword);

        lblConfirmPass = new JLabel("Confirm Password");
        lblConfirmPass.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblConfirmPass.setForeground(PRIMARY_PURPLE);
        rightPanel.add(lblConfirmPass);

        txtConfirmPassword = new JPasswordField();
        styleTextField(txtConfirmPassword);
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtConfirmPassword.setForeground(PRIMARY_PURPLE);
        rightPanel.add(txtConfirmPassword);

        lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblRole.setForeground(PRIMARY_PURPLE);
        rightPanel.add(lblRole);

        rolePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        rolePanel.setBackground(Color.WHITE);
        roleGroup = new ButtonGroup();
        tglAdmin = createRoleButton("Admin");
        tglStudent = createRoleButton("Student");
        tglLecturer = createRoleButton("Lecturer");
        tglAdmin.setSelected(true);
        updateRoleButtonStyles();
        rolePanel.add(tglAdmin);
        rolePanel.add(tglStudent);
        rolePanel.add(tglLecturer);
        rightPanel.add(rolePanel);

        btnSignIn = new JButton("Sign In") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) g2.setColor(BTN_CLICK);
                else if (getModel().isRollover()) g2.setColor(BTN_HOVER);
                else g2.setColor(BTN_DEFAULT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnSignIn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setFocusPainted(false);
        btnSignIn.setContentAreaFilled(false);
        btnSignIn.setBorderPainted(false);
        btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ✅ Add ActionListener
        btnSignIn.addActionListener(this);

        rightPanel.add(btnSignIn);

        toggleAuthMode("SignIn");

        add(rightPanel);
    }

    private void toggleAuthMode(String mode) {
        boolean isSignUp = mode.equals("SignUp");

        if (isSignUp) {
            lblSignUpTab.setForeground(PRIMARY_PURPLE);
            lblSignUpTab.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, PRIMARY_PURPLE));
            lblSignInTab.setForeground(Color.LIGHT_GRAY);
            lblSignInTab.setBorder(null);

            btnSignIn.setText("Sign Up");

            lblConfirmPass.setVisible(true);
            txtConfirmPassword.setVisible(true);

            setupSignUpLayout();

        } else {
            lblSignInTab.setForeground(PRIMARY_PURPLE);
            lblSignInTab.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, PRIMARY_PURPLE));
            lblSignUpTab.setForeground(Color.LIGHT_GRAY);
            lblSignUpTab.setBorder(null);

            btnSignIn.setText("Sign In");

            lblConfirmPass.setVisible(false);
            txtConfirmPassword.setVisible(false);

            setupSignInLayout();
        }
    }

    private void setupSignInLayout() {
        lblUser.setBounds(60, 140, 200, 30);
        txtUsername.setBounds(60, 185, 370, 50);

        lblPass.setBounds(60, 280, 200, 30);
        txtPassword.setBounds(60, 325, 370, 50);

        lblRole.setBounds(60, 400, 200, 30);
        rolePanel.setBounds(60, 450, 370, 45);

        btnSignIn.setBounds(60, 560, 370, 55);
    }

    private void setupSignUpLayout() {
        lblUser.setBounds(60, 110, 200, 30);
        txtUsername.setBounds(60, 150, 370, 50);

        lblPass.setBounds(60, 220, 200, 30);
        txtPassword.setBounds(60, 260, 370, 50);

        lblConfirmPass.setBounds(60, 330, 250, 30);
        txtConfirmPassword.setBounds(60, 370, 370, 50);

        lblRole.setBounds(60, 440, 200, 30);
        rolePanel.setBounds(60, 480, 370, 45);

        btnSignIn.setBounds(60, 560, 370, 55);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.DARK_GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(PRIMARY_PURPLE, 20),
                new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private JToggleButton createRoleButton(String text) {
        JToggleButton btn = new JToggleButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBackground(BTN_DEFAULT);
        btn.setForeground(Color.WHITE);

        btn.addActionListener(e -> updateRoleButtonStyles());
        roleGroup.add(btn);
        return btn;
    }

    private void updateRoleButtonStyles() {
        JToggleButton[] buttons = {tglAdmin, tglStudent, tglLecturer};
        for (JToggleButton btn : buttons) {
            if (btn.isSelected()) {
                btn.setBackground(BTN_DEFAULT);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(220, 220, 220));
                btn.setForeground(Color.WHITE);
            }
        }
    }

    // ✅ ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignIn) {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String role = tglAdmin.isSelected() ? "Admin" : tglStudent.isSelected() ? "Student" : "Lecturer";

            // Controller එකේ instance එකක් සාදා (හෝ දැනට පවතින එකක් භාවිතා කර) දත්ත යවන්න
            LoginController controller = new LoginController();

            if (btnSignIn.getText().equals("Sign In")) {
                // Controller එකේ login logic එක call කිරීම
                boolean success = controller.handleLogin(username, password, role);
                if (success) {
                    this.dispose(); // Login සාර්ථක නම් window එක වසන්න
                }
            } else {
                String confirmPass = new String(txtConfirmPassword.getPassword());
                if (!password.equals(confirmPass)) {
                    JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Controller එකේ signup logic එක call කිරීම
                    controller.handleSignUp(username, password, role);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}

class RoundedBorder implements javax.swing.border.Border {
    private int radius;
    private Color color;

    public RoundedBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius / 2, this.radius / 2, this.radius / 2, this.radius / 2);
    }

    public boolean isBorderOpaque() { return true; }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, radius, radius);
    }
}