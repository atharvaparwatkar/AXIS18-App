package com.developer.kartikraut.axis.Talks;


import java.io.Serializable;

public class TalkClass implements Serializable {

    int id;
    String name,description,venue,date,time,imageurl,link;
    int is_talk;  // to distinguish between talk and guest


    public TalkClass()
    {
        id=0;
        this.name = "";
        this.description = "";
        this.venue = "";
        this.date = "";
        this.time = "";
        this.imageurl = "";
        this.link = "";
    }

    public TalkClass(int id, String name, String description, String venue, String date, String time, String imageurl, String link, int is_talk) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.imageurl = imageurl;
        this.link = link;
        this.is_talk = is_talk;

    }

    public int getIs_talk() {
        return is_talk;
    }

    public void setIs_talk(int is_talk) {
        this.is_talk = is_talk;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
