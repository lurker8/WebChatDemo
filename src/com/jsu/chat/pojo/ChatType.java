package com.jsu.chat.pojo;

/**
 * com.jsu.chat.pojo
 *
 * @desc 消息的类型实体类
 * @author:EumJi
 * @year: 2016
 * @month: 09
 * @day: 02
 * @time: 2016/9/2
 */
public class ChatType {
    private String chatType;
    private String msg;
    private String to;

    public ChatType() {
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
