package com.chatOnline.websocket_server.component;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * onopen：WebSocket 连接成功后触发
 * onmessage：浏览器接收到 WebSocket 服务器发送过来的数据时触发
 * onclose：浏览器接收到 WebSocket 服务器传来的关闭连接请求时触发
 * onerror：连接失败，发送、接收数据失败或者处理数据出现错误时触发
 */
@ServerEndpoint("/socket/{userId}")
@Component
public class WebSocketServer {
    /**
    *记录当前在线数量
     **/
    public static final Map<String,Session> sessionMap = new ConcurrentHashMap<>();

//    执行“广播消息”操作
    @OnOpen
    public void onOpen(Session session, @PathParam("userId")String userId){
        if(userId==null)    return;
        //判断该用户是否已经登,如果已经登录 则让另外一个设备下线
        if(sessionMap.get(userId)!=null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type",-1);
            this.sendMessage(jsonObject.toString(),sessionMap.get(userId));
            sessionMap.remove(userId);
        }
        System.out.println("用户："+userId+"已连接server1");
        sessionMap.put(userId,session);

        Date date = new DateTime();
        String dataString = DateUtil.formatChineseDate(date,false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",0);
        jsonObject.put("time",dataString);
        this.sendMessage(jsonObject.toString(),session);
        //群发
        JSONObject online = new JSONObject();
        online.put("type",1);
        StringBuilder ids = new StringBuilder();
        for(Object key:sessionMap.keySet())
        {
            ids.append(key.toString());//序列化id
            ids.append(',');
        }
        String iDs = ids.substring(0,ids.length()-1);
        online.put("ids",iDs);
        this.sendAllMessage(online.toString());
    }
//    断开连接
    @OnClose
    public void onClose(Session session,@PathParam("userId") String userId){
        sessionMap.remove(userId);
        //群发
        System.out.println("用户"+userId+" 已从server1下线了！");
        JSONObject online = new JSONObject();
        online.put("type",1);
        StringBuilder ids = new StringBuilder();
        for(Object key:sessionMap.keySet())
        {
            ids.append(key.toString());//序列化id
            ids.append(',');
        }
        String iDs = ids.length()>0?ids.substring(0,ids.length()-1):ids.toString();
        online.put("ids",iDs);
        this.sendAllMessage(online.toString());
    }
//   向指定接收方法发送消息
    @OnMessage
    public void onMessage(String message,Session session,@PathParam("userId") String userId){
        JSONObject obj = JSONUtil.parseObj(message);
        String type = obj.getStr("type");
        if(type.equals("1")){//单发
            String toUser = obj.getStr("to");
            String text = obj.getStr("text");
            Session toSession = sessionMap.get(toUser);
            if(toSession!=null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("from",userId);
                jsonObject.put("text",text);
                jsonObject.put("type",2);
                this.sendMessage(jsonObject.toString(),toSession);
            }else {
                System.out.println("未找到目标session："+toUser);
            }
        }
        else if(type.equals("3")){
            List<Integer> ids = (List<Integer>) obj.get("ids");
            String from = obj.getStr("from");
            for(Integer id:ids){
                if (!from.equals(id.toString())&&sessionMap.get(id.toString())!=null){
                    this.sendMessage(message,sessionMap.get(id.toString()));
                }
            }
        }
        //加好友消息的实时转发
        else if(type.equals("4")||type.equals("5")||type.equals("6")){
            //先找找对方是否在线
            String to = obj.getStr("to");
            System.out.println("准备向用户"+to+"转发通知");
            Session session1 = sessionMap.get(to);
            if(session1!=null){
                this.sendMessage(message, session1);
            }
        }
        //群发(没有用到)
        else{
            String toUser = obj.getStr("to");
            String text = obj.getStr("text");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("from",userId);
            jsonObject.put("text",text);
            jsonObject.put("type",4);
            this.sendAllMessage(jsonObject.toString());
        }

    }
//    创建对象失败
    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }

    private void sendMessage(String message,Session toSession){
        try {
            toSession.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendAllMessage(String message){
        try {
            for(Session session : sessionMap.values()){
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
