package com.jsu.chat.pojo;

import java.util.List;

/**
 * com.jsu.chat.pojo
 *
 * @desc 消息实体类
 * @author:EumJi
 * @year: 2016
 * @month: 09
 * @day: 02
 * @time: 2016/9/2
 */
public class Message {

    private String alert;
    private String msg;
    private String from;
    private String date;
    private List<String> users;

    public Message() {
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String toJson(){
        return "<font color='red'>[私聊]: "+this.msg+"</font><br>";
    }
}
