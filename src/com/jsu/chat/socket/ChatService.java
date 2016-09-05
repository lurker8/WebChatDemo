package com.jsu.chat.socket;

import com.google.gson.Gson;
import com.jsu.chat.pojo.ChatType;
import com.jsu.chat.pojo.Message;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.*;

/**
 * com.jsu.chat.socket
 *
 * @desc    聊天socket通信类
 * @author:EumJi
 * @year: 2016
 * @month: 09
 * @day: 02
 * @time: 2016/9/2
 */

// @ServerEndpoint("/echo") 的 annotation 注释端点表示将 WebSocket 服务端运行在 ws://[Server 端 IP 或域名]:[Server 端口]/WebChatDemo/chat 的访问端点
@ServerEndpoint("/chat")
public class ChatService {
    /*聊天室集合 防止重复*/
    private static Set<ChatService> services = new HashSet<ChatService>();
    /*用户集合*/
    private static List<String> users = new ArrayList<String>();
    /*session对象*/
    private Session session;
    /*当前用户名*/
    private String username;
    /*gson对象*/
    private Gson gson = new Gson();

    /**
     * 第一次连接时调用的方法
     * OnOpen 在这个端点一个新的连接建立时被调用。
     * @param session
     */
    @OnOpen
    public void open(Session session) throws UnsupportedEncodingException {
        this.session = session;
        services.add(this);
        //请求的username
        String queryString = session.getQueryString().substring(session.getQueryString().indexOf("=")+1);;
        //转换成utf-8格式
        String username = URLDecoder.decode(queryString,"UTF-8");
        this.username =username;
        users.add(username);

        Message message = new Message();
        message.setAlert("欢迎"+username+" 进入聊天室!");
        message.setUsers(users);
        //群聊通知
        sendToAll(services,gson.toJson(message));
    }

    /**
     * 发送消息调用的方法
     * @onMessage 注解的 Java 方法用于接收传入的 WebSocket 信息，这个信息可以是文本格式，也可以是二进制格式。
     * @param session   session
     * @param msg   消息
     */
    @OnMessage
    public void message(Session session,String msg){
        ChatType chatType = gson.fromJson(msg, ChatType.class);
        //判断聊天类型
        if ("1".equals(chatType.getChatType())){
            Message message = new Message();
            message.setMsg(chatType.getMsg());
            message.setFrom(this.username);
            message.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            sendToAll(services,gson.toJson(message));

        }else {
            String to = chatType.getTo();
            Message message = new Message();
            message.setMsg("<font color='red'>[私聊]: "+chatType.getMsg()+"</font><br>");
            message.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            sendToOne(services,gson.toJson(message),to);
        }
    }

    /**
     * 关闭网页调用的方法
     * OnClose 在连接被终止时调用。
     * @param session
     */
    @OnClose
    public void close(Session session){
        services.remove(this);
        users.remove(this.username);
        Message message = new Message();
        message.setAlert(this.username+"退出聊天室!");
        message.setUsers(users);
        sendToAll(services,gson.toJson(message));
    }

    /**
     * 私聊
     * @param services  聊天室人员名单
     * @param s 消息
     * @param to    私聊对象
     */
    private void sendToOne(Set<ChatService> services, String s,String to) {
        if ( to == null){
            sendToAll(services,s);
        }else {
            for (ChatService service : services) {
                if (service.username.equals(to)){
                    try {
                        service.session.getBasicRemote().sendText(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 群聊方法
     * @param services
     * @param msg 消息
     */
    private void sendToAll(Set<ChatService> services, String msg) {
        for (ChatService service : services) {
            try {
                service.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
