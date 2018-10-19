package com.developer.kartikraut.axis.Schedule;

public class ScheduleClass {

    String name,time,venue;

    public ScheduleClass(String name, String time, String venue) {
        this.name = name;
        this.time = time;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
