#websocket简单例子

本案例很大程度上模仿tomcat提供的例子<br>
主要目的是为了熟悉WebSocket的使用。<br>
只实现最简单的聊天功能，可以发送简单的表情


##说明

###1.实验环境：

<code>
    开发IDE：Intellij idea(使用eclipse也可以，各有所爱)<br>
    JDK：1.8(理论上1.6以上即可)<br>
    tomcat：7.0.70（理论上7.0.27以上即可）
 
</code>

###修改配置
运行案例需要修改chat.jsp
>var url = "ws://localhost:80/WebChatDemo/chat?username=${sessionScope.username}";<br/>
80对应自己的端口<br>
WebChatDemo对应发布项目名

参考文章：[戳这里](http://www.ibm.com/developerworks/cn/java/j-lo-WebSocket/)