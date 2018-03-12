package restservice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FetchUser;

import pojo.User;

/**
 * Servlet implementation class AddBotChat
 */
public class AddBotChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBotChat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer sender_id=Integer.parseInt(request.getParameter("sender_id"));
		Integer receiver_id=Integer.parseInt(request.getParameter("receiver_id"));
		FetchUser fetchUser =new FetchUser();
		User receiverUser =fetchUser.getSpecific(receiver_id);
		
		String panel_div="<div class='col-lg-3' style='position: fixed; bottom: 20px; right: 20px; z-index: 100; z-index: 9999;' data-receiver_id="+receiver_id+" data-sender_id="+sender_id+"> "
				+ "<div class='panel panel-primary'> <div class='panel-heading'>"+receiverUser.getName()+"</div>"
				+ " <div class='panel-body' style='max-height:350px;padding:0px !important;'> "
				+ "<div class='row' style='margin: 0px !important;'> <div class='chat-discussion' style='padding:0px !important;max-height:250px'>"
				+ " <div class='chat-message left' style='padding:0px !important;margin-top:10px;margin-bottom:10px;margin-right:10px;'> "
				+ "<img class='message-avatar' src='img/a1.jpg' alt=''> <div class='message'> <a class='message-author' href='#'> "
				+ "Michael Smith </a> <span class='message-date'> Mon Jan 26 2015 - 18:39:23 </span> <span class='message-content'>"
				+ " Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. "
				+ "</span> </div> </div> <div class='chat-message right' style='padding:0px !important;margin-top:10px;margin-bottom:10px;margin-left:10px;'> "
				+ "<img class='message-avatar' src='img/a4.jpg' alt=''> <div class='message'>"
				+ " <a class='message-author' href='#'> Karl Jordan </a> <span class='message-date'> "
				+ "Fri Jan 25 2015 - 11:12:36 </span> <span class='message-content'>"
				+ " Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover."
				+ " </span> </div> </div>  </div> </div> <div class='row ' style='margin:10px;'> <textarea class='user-text-message' rows='2' cols='50' style='width:100%'>"
				+ " </textarea></div> <div class='row' style='margin:10px;'> "
				+ "<button class='btn btn-sm btn-primary chat-sents pull-right m-t-n-xs' type='submit' style='float:right !important;'> <strong>Send</strong> "
				+ "</button> </div> </div> </div> </div>";
		response.getWriter().print(panel_div);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
