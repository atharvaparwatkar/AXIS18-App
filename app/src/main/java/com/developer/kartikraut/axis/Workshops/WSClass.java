package com.developer.kartikraut.axis.Workshops;

import java.io.Serializable;

public class WSClass implements Serializable{
    int id;
    String title;
    String imageId;
    String description,payment_link;
    Boolean reg_closed;

    public WSClass(int id,String title, String imageId,String description,String payment_link,Boolean reg_closed) {
        setId(id);
        setTitle(title);
        setimageId(imageId);
        setDescription(description);
        setPayment_link(payment_link);
        setReg_closed(reg_closed);
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getimageId() {
        return imageId;
    }

    public void setimageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_link() {
        return payment_link;
    }

    public void setPayment_link(String payment_link) {
        this.payment_link = payment_link;
    }

    public Boolean getReg_closed() {
        return reg_closed;
    }

    public void setReg_closed(Boolean reg_closed) {
        this.reg_closed = reg_closed;
    }


}
