package com.developer.kartikraut.axis.Techinite;


import java.io.Serializable;

public class Techie_Class implements Serializable{

    int id;
    String name,desc,image,date,time,venue;
    Boolean reg_closed;

    public Techie_Class(int id, String name, String desc, String image,Boolean reg_closed,String date, String time, String venue) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.reg_closed = reg_closed;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
