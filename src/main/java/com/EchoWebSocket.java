package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.server.session.SessionDataMap;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

@WebSocket
public class EchoWebSocket {
    private Session session;
    public static HashMap<Integer,Session> sessionDataMap = new HashMap<>();
	public  static Map<Session,Integer> sessionUserIdMap = new ConcurrentHashMap<>();

    
    @OnWebSocketConnect
    public void connected(Session session) {
		String url = session.getUpgradeRequest().getRequestURI().toString();
		Integer user_id = Integer.parseInt(url.substring(url.lastIndexOf("/")+1,url.length()));
		sessionDataMap.put(user_id, session);
		sessionUserIdMap.put(session, user_id);
		
		System.out.println("current user is --- "+url);
        this.session = session;
        
        try {
        	for(Integer id: sessionDataMap.keySet()) {
   			 
        		
   			 if(sessionDataMap.get(id).isOpen()) {
   				JSONObject jsonObject=new JSONObject();
   	     		jsonObject.put("type", "connected");
   	     		jsonObject.put("user_id", user_id);
   			 sessionDataMap.get(id).getRemote().sendString(jsonObject.toString());

     		
			 
   			 }
   		 }		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @OnWebSocketClose
    public void closed(Session user,int statusCode, String reason) {
        int userId =sessionUserIdMap.get(user);
        sessionDataMap.remove(userId);
        sessionDataMap.remove(user);
        
    }

    @OnWebSocketMessage
    public void message(String message) throws IOException {
        System.out.println("Got: " + message);
        session.getRemote().sendString(message);
    }
}
