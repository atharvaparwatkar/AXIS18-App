package com.developer.kartikraut.axis.Events;


import java.io.Serializable;

public class EventsClass implements Serializable {

    int id,max_team_mem,category_id,image;
    String name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone;
    String date,last_date;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public EventsClass(int id, int max_team_mem, int category_id, int image, String name, String slug, String description, String rules, String ps, String em1_name, String em2_name, String em3_name, String em4_name, String em5_name, String em6_name, String em1_phone, String em2_phone, String em3_phone, String em4_phone, String em5_phone, String em6_phone, String date, String last_date) {
        this.id = id;
        this.max_team_mem = max_team_mem;
        this.category_id = category_id;
        this.image = image;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.rules = rules;
        this.ps = ps;
        this.em1_name = em1_name;
        this.em2_name = em2_name;
        this.em3_name = em3_name;
        this.em4_name = em4_name;
        this.em5_name = em5_name;
        this.em6_name = em6_name;
        this.em1_phone = em1_phone;
        this.em2_phone = em2_phone;
        this.em3_phone = em3_phone;
        this.em4_phone = em4_phone;
        this.em5_phone = em5_phone;
        this.em6_phone = em6_phone;
        this.date = date;
        this.last_date = last_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax_team_mem() {
        return max_team_mem;
    }

    public void setMax_team_mem(int max_team_mem) {
        this.max_team_mem = max_team_mem;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getEm1_name() {
        return em1_name;
    }

    public void setEm1_name(String em1_name) {
        this.em1_name = em1_name;
    }

    public String getEm2_name() {
        return em2_name;
    }

    public void setEm2_name(String em2_name) {
        this.em2_name = em2_name;
    }

    public String getEm3_name() {
        return em3_name;
    }

    public void setEm3_name(String em3_name) {
        this.em3_name = em3_name;
    }

    public String getEm4_name() {
        return em4_name;
    }

    public void setEm4_name(String em4_name) {
        this.em4_name = em4_name;
    }

    public String getEm5_name() {
        return em5_name;
    }

    public void setEm5_name(String em5_name) {
        this.em5_name = em5_name;
    }

    public String getEm6_name() {
        return em6_name;
    }

    public void setEm6_name(String em6_name) {
        this.em6_name = em6_name;
    }

    public String getEm1_phone() {
        return em1_phone;
    }

    public void setEm1_phone(String em1_phone) {
        this.em1_phone = em1_phone;
    }

    public String getEm2_phone() {
        return em2_phone;
    }

    public void setEm2_phone(String em2_phone) {
        this.em2_phone = em2_phone;
    }

    public String getEm3_phone() {
        return em3_phone;
    }

    public void setEm3_phone(String em3_phone) {
        this.em3_phone = em3_phone;
    }

    public String getEm4_phone() {
        return em4_phone;
    }

    public void setEm4_phone(String em4_phone) {
        this.em4_phone = em4_phone;
    }

    public String getEm5_phone() {
        return em5_phone;
    }

    public void setEm5_phone(String em5_phone) {
        this.em5_phone = em5_phone;
    }

    public String getEm6_phone() {
        return em6_phone;
    }

    public void setEm6_phone(String em6_phone) {
        this.em6_phone = em6_phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
}
