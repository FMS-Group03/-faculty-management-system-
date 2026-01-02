package com.faculty.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class LoginView extends JFrame {


    private final Color PURPLE_THEME = new Color(124, 77, 255);


    public LoginView() {

        setTitle("Faculty Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1,2 ));

        JPanel leftPanel = createLeftPanel();

        JPanel rightPanel = createRightPanel();













        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        setContentPane(mainPanel);

    }



    private JPanel createLeftPanel(){

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(PURPLE_THEME);
        leftPanel.setSize(new Dimension(1000, 600));

        ImageIcon image = new ImageIcon("images/\u202Bimage_1.png");

        Image icon = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        image = new ImageIcon(icon);

        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
        leftPanel.add(imageLabel);



        JLabel titleLabel = new JLabel("<html><center>Faculty Management <br> System</center></html>");
        titleLabel.setFont(new Font("Sanserif",Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        leftPanel.add(titleLabel);




        JLabel name1 = new JLabel("Faculty of Computing & Technology");
        name1.setFont(new Font("Sanserif", Font.BOLD, 20));
        name1.setForeground(Color.WHITE);
        name1.setAlignmentX(Component.CENTER_ALIGNMENT);
        name1.setBorder(BorderFactory.createEmptyBorder(120, 0, 0, 0));
        leftPanel.add(name1);


        JLabel name2 = new JLabel("Manage your academic journey");
        name2.setFont(new Font("Sanserif", Font.BOLD, 16));
        name2.setForeground(Color.WHITE);
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);
        name2.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        leftPanel.add(name2);





        return leftPanel;

    }




    public class RoundedBorder extends LineBorder {
        private int radius;
        public RoundedBorder(Color color, int thickness, int radius) {
            super(color, thickness, true);
            this.radius = radius;
        }
    }


    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 1.0;

        JLabel title = new JLabel("Sign In");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(PURPLE_THEME);
        c.gridy = 0;
        c.insets = new Insets(40, 50, 20, 50);
        rightPanel.add(title, c);

        JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setForeground(PURPLE_THEME);
        userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        c.gridy = 1;
        c.insets = new Insets(5, 50, 5, 50);
        rightPanel.add(userNameLabel, c);

        JTextField userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(340, 45));
        userNameField.setBorder(new LineBorder(PURPLE_THEME, 3, true));
        c.gridy = 2;
        c.insets = new Insets(0, 50, 15, 50);
        rightPanel.add(userNameField, c);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(PURPLE_THEME);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        c.gridy = 3;
        c.insets = new Insets(5, 50, 5, 50);
        rightPanel.add(passwordLabel, c);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(340, 45));
        passwordField.setBorder(new LineBorder(PURPLE_THEME, 3, true));
        c.gridy = 4;
        c.insets = new Insets(0, 50, 15, 50);
        rightPanel.add(passwordField, c);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setForeground(PURPLE_THEME);
        roleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        c.gridy = 5;
        c.insets = new Insets(5, 50, 10, 50);
        rightPanel.add(roleLabel, c);

        JPanel roleButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        roleButtonPanel.setBackground(Color.WHITE);

        ModernButton adminBtn = new ModernButton("Admin", true, PURPLE_THEME);
        ModernButton studentBtn = new ModernButton("Student", false, PURPLE_THEME);
        ModernButton lecturerBtn = new ModernButton("Lecturer", false, PURPLE_THEME);

        roleButtonPanel.add(adminBtn);
        roleButtonPanel.add(studentBtn);
        roleButtonPanel.add(lecturerBtn);

        c.gridy = 6;
        c.insets = new Insets(0, 50, 30, 50);
        rightPanel.add(roleButtonPanel, c);

        ModernButton signInButton = new ModernButton("Sign In", true, PURPLE_THEME);
        signInButton.setPreferredSize(new Dimension(340, 50));
        signInButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        c.gridy = 7;
        c.weighty = 1.0;
        c.insets = new Insets(10, 50, 50, 50);
        c.anchor = GridBagConstraints.NORTH;
        rightPanel.add(signInButton, c);

        return rightPanel;
    }

    class ModernButton extends JButton {
        private boolean active;
        private Color themeColor;

        public ModernButton(String text, boolean active, Color themeColor) {
            super(text);
            this.active = active;
            this.themeColor = themeColor;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(100, 35));
            setFont(new Font("SansSerif", Font.BOLD, 12));
            setForeground(active ? Color.WHITE : Color.GRAY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (active) {
                g2.setColor(themeColor);
            } else {
                g2.setColor(new Color(230, 230, 230)); // Light Gray
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }
    }


}
