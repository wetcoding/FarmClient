package com.wetcoding.farmclient;

public class Task {
    private String id;
    private String status;

    public Task(String id,String status){
        this.id=id;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
