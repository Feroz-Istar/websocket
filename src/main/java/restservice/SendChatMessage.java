package restservice;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import com.EchoWebSocket;
import com.FetchUser;
import com.SaveChat;

import pojo.User;

/**
 * Servlet implementation class SendChatMessage
 */
public class SendChatMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChatMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message=request.getParameter("message");
		int reciever_id = Integer.parseInt(request.getParameter("id").toString());
		User current_user= (User) request.getSession().getAttribute("user");
		SaveChat saveChat = new SaveChat();
		saveChat.saveChat(current_user.getId(), reciever_id, message);
		 HashMap<Integer,Session> sessionDataMap =EchoWebSocket.sessionDataMap;
		 
		 for(Integer id: sessionDataMap.keySet()) {
			    JSONObject jsonObject=new JSONObject();
	     		jsonObject.put("type", "chat_message");
	     		jsonObject.put("sender_user_id", current_user.getId());
	     		jsonObject.put("reciever_user_id", reciever_id);
	     		jsonObject.put("message",message );
	     		jsonObject.put("sender_name",current_user.getName() );
  
	     		
	     		
	     		
	     		
	     		
	     		
	     		if(id == reciever_id) {
			 if(sessionDataMap.get(id).isOpen())
			 sessionDataMap.get(id).getRemote().sendString(jsonObject.toString());
	     		}
	     		}
		 response.getWriter().println("badhiya");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
