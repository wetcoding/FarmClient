package com.wetcoding.farmclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Форма отображения списка задач пользователя
 */
public class TaskForm extends JFrame {

    private List<Task> taskList=new ArrayList<>();
    private int userId;
    private TaskTableModel tModel;
    private boolean addTaskEvent=false;

    /**
     * Конструктор окна
     * @param userId - id пользователя
     * @param userName - имя пользователя
     */
    public TaskForm(int userId, String userName){
        this.userId=userId;
        setTitle("RenderFarm   user: "+userName);
        setSize(600 , 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel borderPanel = new JPanel(new BorderLayout(0,20));
        setContentPane(borderPanel);

        tModel = new TaskTableModel(taskList);
        JTable table=new JTable(tModel);
        borderPanel.add(new JScrollPane(table),BorderLayout.CENTER);

        JButton btnAddTask=new JButton("+ Add task");
        borderPanel.add(btnAddTask,BorderLayout.SOUTH);

        btnAddTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskEvent=true;
                btnAddTask.setEnabled(false);
            }
        });

        //Поток для обновления списка задач
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", userId);

                        if(addTaskEvent){
                            jsonObject.put("type","post");
                            WebUtils.sendPost("http://localhost:8080/RenderFarm/task",jsonObject);
                            addTaskEvent=false;
                            btnAddTask.setEnabled(true);
                        }

                        jsonObject.put("type", "get");
                        JSONObject result = WebUtils.sendPost("http://localhost:8080/RenderFarm/task", jsonObject);
                        updateTable(result);
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        System.out.println("Thread interrupted");
                    }
                }
            }
        }).start();

        setVisible(true);
    }

    /**
     * Метод для обновления содержимого таблицы задач
     * @param jsonObject - список задач в формате JSON
     */
    private void updateTable(JSONObject jsonObject){
        try {
            if (Objects.nonNull(jsonObject)) {
                taskList.clear();
                JSONArray tasks = jsonObject.getJSONArray("tasks");
                for(Object jsonIterator:tasks){
                    JSONObject json = (JSONObject)jsonIterator;
                    int id = json.getInt("id");
                    String status = json.getString("status");
                    Task task = new Task(id, status);
                    taskList.add(task);
                }
                tModel.fireTableDataChanged();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
