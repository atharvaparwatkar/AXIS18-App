package com.developer.kartikraut.axis.Going_To;

public class GoingToClass {

    int event_id;
    String event_name;
    String category_name;

    public GoingToClass(int event_id,String event_name, String category_name) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.category_name = category_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
}
