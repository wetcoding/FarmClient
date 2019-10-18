package com.wetcoding.farmclient;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Окно регистрации/входа
 */
public class LoginForm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm();
            }
        });
    }


    public LoginForm() {
        JFrame frame = new JFrame("RenderFarm login");
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

        JTextField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 180, 25);
        panel.add(passwordText);

        JLabel labelMessage=new JLabel();
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
                JSONObject jsonObject=doPost(true,emailText.getText(),passwordText.getText());
                try{
                    if(Objects.nonNull(jsonObject) && jsonObject.getString("status").equals("OK")){
                        frame.setVisible(false);
                        new TaskForm(jsonObject.getInt("id"),emailText.getText());
                    } else{
                        labelMessage.setForeground(Color.red);
                        labelMessage.setText("Incorrect email/password");
                    }
                } catch (JSONException v){
                    System.out.println(v.getMessage());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject jsonObject=doPost(false,emailText.getText(),passwordText.getText());
                try{
                    if(Objects.nonNull(jsonObject) && jsonObject.getString("status").equals("OK")){
                        labelMessage.setForeground(Color.green);
                        labelMessage.setText("Registration complete");
                    } else{
                        labelMessage.setForeground(Color.red);
                        labelMessage.setText("Registration failed!");
                    }
                } catch (JSONException v){
                    System.out.println(v.getMessage());
                }
            }
        });
    }

    private JSONObject doPost(boolean login,String email,String password){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        if(login)
            return WebUtils.sendPost("http://localhost:8080/RenderFarm/login",jsonObject);
        else
            return WebUtils.sendPost("http://localhost:8080/RenderFarm/signup",jsonObject);
    }



}