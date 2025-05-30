package com.sicnu.wzh.Config.WebSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @Author: 海绵宝宝
 * @Explain: WebSocket
 * @DateTime: 2022/5/29 15:54
 * @Params: WebSocketServer.sendInfo(使用JSON,用户名);
 * @Return
 */
@ServerEndpoint("/api/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
//            webSocketMap.remove(userId);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("key","连接成功");
            sendMessage(JSON.toJSONString(map));
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+userId+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSONObject.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId",this.userId);
                String fromUserId=jsonObject.getString("fromUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(fromUserId) && webSocketMap.containsKey(fromUserId)){
                    webSocketMap.get(fromUserId).sendMessage(jsonObject.toJSONString());
                    //自定义-业务处理

//                    DeviceLocalThread.paramData.put(jsonObject.getString("group"),jsonObject.toJSONString());
                }else{
                    log.error("请求的userId:"+fromUserId+"不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  发生错误时候
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        //加入线程锁
        synchronized (session){
            try {
                //同步发送信息
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("服务器推送失败:"+e.getMessage());
            }
        }
    }


    /**
     * 发送自定义消息
     * */
    /**
     * 发送自定义消息
     * @param message 发送的信息
     * @param toUserId  如果为null默认发送所有
     * @throws IOException
     */
    public static void sendInfo(String message,String toUserId) throws IOException {
        //如果userId为空，向所有群体发送
        if(StringUtils.isEmpty(toUserId)) {
            //向所有用户发送信息
            Iterator<String> itera = webSocketMap.keySet().iterator();
            while (itera.hasNext()) {
                String keys = itera.next();
                WebSocketServer item = webSocketMap.get(keys);
                item.sendMessage(message);
            }
        }
        //如果不为空，则发送指定用户信息
        else if(webSocketMap.containsKey(toUserId)){
            WebSocketServer item = webSocketMap.get(toUserId);
            item.sendMessage(message);
            log.info("已向userId:【"+toUserId+"】发送message:【"+message+"】");
        }else{
            log.error("请求的userId:"+toUserId+"不在该服务器上");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static synchronized ConcurrentHashMap<String, WebSocketServer> getWebSocketMap(){
        return WebSocketServer.webSocketMap;
    }
}
