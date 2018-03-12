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

/**
 * Servlet implementation class SendToAllUser
 */
public class SendToAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendToAllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message=request.getParameter("message");
		 HashMap<Integer,Session> sessionDataMap =EchoWebSocket.sessionDataMap;
		 
		 for(Integer id: sessionDataMap.keySet()) {
			    JSONObject jsonObject=new JSONObject();
	     		jsonObject.put("type", "generic");
	     		jsonObject.put("user_id", 1);
			 
			 if(sessionDataMap.get(id).isOpen())
			 sessionDataMap.get(id).getRemote().sendString(message);
		 }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
