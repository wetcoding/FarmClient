package com.wetcoding.farmclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    JFrame frame;
    JLabel labelMessage;
    public LoginForm(){
        placeComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm();
            }
        });
    }

    private void placeComponents() {
        frame = new JFrame("RenderFarm login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 10, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 10, 180, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 180, 25);
        panel.add(passwordText);

        labelMessage=new JLabel("Incorrect email/password");
        labelMessage.setForeground(Color.red);
        labelMessage.setBounds(10,65,200,25);
        panel.add(labelMessage);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(110, 90, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(200, 90, 80, 25);
        panel.add(registerButton);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskForm(0,emailText.getText());
                frame.setVisible(false);
            }
        });
    }

}