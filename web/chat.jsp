<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chatRoom</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="js/jquery.qqFace.js"></script> 
<style type="text/css">
.comment{width:500px; margin:20px 0;clear: both; }
.com_form{width:100%;}
.input{width:99%; height:60px; border:1px solid #989898
}
.com_form p{height:28px; line-height:28px;}
span.emotion{width:42px; height:20px; background:url(icon.gif) no-repeat 2px 2px;  
padding-left:20px; cursor:pointer} 
span.emotion:hover{background-position:2px -28px} 
.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;} 
.qqFace table td{padding:0px;} 
.qqFace table td img{cursor:pointer;border:1px #fff solid;} 
.qqFace table td img:hover{border:1px #0066cc solid;}
</style>
<script type="text/javascript">
	function replace_em(str){ 
	    str = str.replace(/\</g,'<；'); 
	    str = str.replace(/\>/g,'>；'); 
	    str = str.replace(/\n/g,'<；br/>；'); 
	    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="face/$1.gif" border="0" />'); 
	    return str; 
	} 
	var contents;
	var socketObject;
	var url = "ws://localhost:80/WebChatDemo/chat?username=${sessionScope.username}";
	window.onload = content;
	function content(){
		//判读是否支持websocket
		if ('WebSocket' in window) {
			//创建websocket对象
            socketObject = new WebSocket(url);
        } else if ('MozWebSocket' in window) {
            socketObject = new MozWebSocket(url);
        } else {
            alert('WebSocket is not supported by this brosocketObjecter.');
            return;
        }
		socketObject.onmessage = function(event){
			eval("var result ="+event.data);
			/* 欢迎新用户 */
			if(result.alert != undefined){
				$("#content").append(result.alert+"<br/>");
			}
			/* 清空列表重新添加 */
			if(result.users != undefined){
				$("#userList").html("");
				$(result.users).each(function(){
					$("#userList").append("<input type='checkbox' value='"+this+"' />"+this+"<br/>");
				});
			}
			/*添加消息*/
			if(result.from != undefined){
				$("#content").append(result.from+" "+result.date+" 说：<br/>"+result.msg+"<br/>");
			}
		};
	}

	//发送数据
	function sendMsg(){
		var obj = null;
		var str = $("#saytext").val();
		var strs = replace_em(str);
		var to = $("#userList :checked");
		//单用户
		if(to.size()==0){
			obj ={
				chatType:1,
				to:to.val(),
				msg:strs
			};
		}else{
			obj ={
				chatType:2,
				to:to.val(),
				msg:strs
			};
		}
		//发送请求
		socketObject.send(JSON.stringify(obj));
		//清空输入框
		 $("#saytext").val("");
	}
	
	$(function(){ 
		 $('.emotion').qqFace({ 
		        assign:'saytext', //给输入框赋值 
		        path:'face/'    //表情图片存放的路径 
		    });
	}); 
	
	
</script>
</head>
<body>
	<h3>亲爱的 ${sessionScope.username }，欢迎使用JSU聊天系统！</h3>
	<div id="content" 
		style="border:1px solid black;width:400px;height:300px;float:left;">
	</div>
	<div id="userList" 
		style="border:1px solid black;width:100px;height:300px;float:left;">
	</div>
	<br>
	<div class="comment"> 
	    <div class="com_form"> 
	        <textarea class="input" id="saytext" name="saytext"></textarea> 
	        <p>
		        <span class="emotion">表情</span>
		        <button onclick="sendMsg();">发送</button>
	        </p> 
	    </div> 
	</div> 
</body>
</html>