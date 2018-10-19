package com.developer.kartikraut.axis.Events;


public class CategoryClass {

    String title;
    int imageId;

    public CategoryClass(String title, int imageId) {
        setTitle(title);
        setimageId(imageId);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getimageId() {
        return imageId;
    }

    public void setimageId(int imageId) {
        this.imageId = imageId;
    }

}
