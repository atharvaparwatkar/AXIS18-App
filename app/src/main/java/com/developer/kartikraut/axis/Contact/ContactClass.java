package com.developer.kartikraut.axis.Contact;

public class ContactClass {

    String name,post1,phone_no,mailId;
    int imageId;

    public ContactClass(String post1,String name, int imageId,String phone_no, String mailId) {
        this.post1 = post1;
        this.name = name;
        this.imageId = imageId;
        this.phone_no = phone_no;
        this.mailId = mailId;
    }

    public String getPost1(){
        return post1;
    }

    public void setPost1(String post1){
        this.post1 = post1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPhone_no(){
        return phone_no;
    }

    public void setPhone_no(String phone_no){
        this.phone_no = phone_no;
    }

    public String getMailId(){
        return mailId;
    }

    public void setMailId(String mailId){
        this.mailId = mailId;
    }
}
