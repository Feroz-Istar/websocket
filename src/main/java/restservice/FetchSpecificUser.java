package restservice;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.EchoWebSocket;
import com.FetchUser;

import pojo.User;

/**
 * Servlet implementation class FetchSpecificUser
 */
public class FetchSpecificUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchSpecificUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FetchUser fetchUser = new FetchUser();
		User user = (User) request.getSession().getAttribute("user");
		StringBuffer sb = new StringBuffer();
		
		for(Integer id: EchoWebSocket.sessionDataMap.keySet()) {
		if(user.getId() != id) {
			ArrayList<User> users = null;
			try {
				users = fetchUser.getUser();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(User u:users) {
				if(u.getId()==id) {
					u.setStatus("online");
					String temp=" <div class='chat-user click-user ' data-user_id='"+u.getId()+"'>           "
							+ "<span class='pull-right label label-primary'>"+u.getStatus()+"</span> "
							+ "<img class='chat-avatar' src='http://webapplayers.com/inspinia_rtl_v2.6.2/img/a2.jpg' alt='' >"
							+ "<div class='chat-user-name'><a >"+u.getName()+"</a>                                             </div>                                         </div>";
					sb.append(temp);
				}
			}
				
			
		
		}
		}
		
		
		response.getWriter().println(sb.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
