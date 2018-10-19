package com.developer.kartikraut.axis.Inaugration;

public class InaugClass {

    int id;
    String speaker_name,speaker_desc,speaker_image;
    Boolean reg_closed;

    public InaugClass(int id, String speaker_name, String speaker_desc, String speaker_image,Boolean reg_closed) {
        this.id = id;
        this.speaker_name = speaker_name;
        this.speaker_desc = speaker_desc;
        this.speaker_image = speaker_image;
        this.reg_closed = reg_closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public void setSpeaker_name(String speaker_name) {
        this.speaker_name = speaker_name;
    }

    public String getSpeaker_desc() {
        return speaker_desc;
    }

    public void setSpeaker_desc(String speaker_desc) {
        this.speaker_desc = speaker_desc;
    }

    public String getSpeaker_image() {
        return speaker_image;
    }

    public void setSpeaker_image(String speaker_image) {
        this.speaker_image = speaker_image;
    }
}
