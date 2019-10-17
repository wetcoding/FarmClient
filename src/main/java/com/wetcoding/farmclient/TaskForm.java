package com.wetcoding.farmclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskForm extends JFrame {
    List<Task> taskList=new ArrayList<>();
    int userId;
    public TaskForm(int userId, String userName){
        this.userId=userId;
        setTitle("RenderFarm   user: "+userName);
        setSize(600 , 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel borderPanel = new JPanel(new BorderLayout(0,20));
        setContentPane(borderPanel);

        //Таблица
        TaskTableModel tModel = new TaskTableModel(taskList);
        JTable table=new JTable(tModel);
        borderPanel.add(new JScrollPane(table),BorderLayout.CENTER);

        JButton button=new JButton("Add task");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskList.add(new Task("2","adad"));
                tModel.fireTableDataChanged();
            }
        });

        borderPanel.add(button,BorderLayout.SOUTH);

        setVisible(true);

    }

}
