package com.wetcoding.farmclient;

/**
 * Вспомогательный класс для таблицы
 */
public class Task {
    private int id;
    private String status;

    public Task(int id,String status){
        this.id=id;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
