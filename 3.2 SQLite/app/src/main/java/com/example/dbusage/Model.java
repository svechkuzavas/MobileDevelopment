package com.example.dbusage;

public class Model {
    private String header;
    private String date;
    private String time;

    public Model(String header, String date, String time){
        this.header = header;
        this.date = date;
        this.time = time;
    }

    public String getHeader() { return header; }

    public void setHeader(String header) { this.header = header; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    @Override
    public String toString() {
        return "Model{" +
                "header='" + header + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
