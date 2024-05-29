package com.flyfiref.schedule.pojo;

public class Item {
    private int id;
    private String startTime;
    private String endTime;
    private String desc;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Item(int id, String startTime, String endTime, String desc) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
    }

    public Item() {
    }
}
