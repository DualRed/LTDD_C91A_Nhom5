package com.example.chatbot_app.Models;

public class ChatbotModel {
    public String methods;
    public String success;
    public String noti;
    public String lang;
    public String donate;

    public ChatbotModel(String methods, String success, String noti, String lang, String donate) {
        this.methods = methods;
        this.success = success;
        this.noti = noti;
        this.lang = lang;
        this.donate = donate;
    }

    public ChatbotModel() {
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }
}
