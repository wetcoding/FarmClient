package com.wetcoding.farmclient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    List<Task> taskList;

    public TaskTableModel(List<Task> taskList) {
        super();
        this.taskList = taskList;
    }

    @Override
    public int getRowCount() {
        return taskList.size();
    }
    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return taskList.get(r).getId();
            case 1:
                return taskList.get(r).getStatus();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "id";
                break;
            case 1:
                result = "status";
                break;
        }
        return result;
    }
}
