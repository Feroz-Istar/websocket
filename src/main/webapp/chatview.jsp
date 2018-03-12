
<!DOCTYPE html>
<%@page import="restservice.AddBotChat"%>
<%@page import="com.EchoWebSocket"%>
<%@page import="com.FetchUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojo.User"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Chat view</title>

    <link href="http://webapplayers.com/inspinia_rtl_v2.6.2/css/bootstrap.min.css" rel="stylesheet"> <link href="css/plugins/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet">
    <link href="http://webapplayers.com/inspinia_rtl_v2.6.2/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="http://webapplayers.com/inspinia_rtl_v2.6.2/css/animate.css" rel="stylesheet">
    <link href="http://webapplayers.com/inspinia_rtl_v2.6.2/css/style.css" rel="stylesheet">
<%
User current_user = new User();
try{
	current_user= (User)request.getSession().getAttribute("user");


}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);//method may be include or forward  

}

%>
</head>

<body class="rtls">

<div id="wrapper">
<div class="current_user_id" data-user_id= "<%=current_user.getId()%>" data-user_name="<%=current_user.getName()%>"></div>

<div id="page-wrapper" class="gray-bg" style="padding: 0px !important; margin:0px !important;">

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Chat view</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a>Miscellaneous</a>
            </li>
            <li class="active">
                <strong>Chat view</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>


<div class="wrapper wrapper-content animated fadeInRight">
    
    <div class="row">
        <div class="col-lg-12">

                <div class="ibox chat-view">

                    <div class="ibox-title">
                        <small class="pull-right text-muted">Last message:  Mon Jan 26 2015 - 18:39:23</small>
                         Chat room panel
                    </div>


                    <div class="ibox-content">

                        <div class="row">

                            
                            <div class="col-md-3">
                                <div class="chat-users">


                                    <div class="users-list parent_chat">
                                        <% ArrayList<User> users = new FetchUser().getUser();
                                        
                                        for(User user: users){
                                        	if(user.getId() != current_user.getId()){
                                        		String status = "offline";
                                        	for(Integer key:new EchoWebSocket().sessionDataMap.keySet()){
                                        		if(user.getId() == key){
                                        			user.setStatus("online");
                                        		}else{
                                        			user.setStatus("offline");
                                        			
                                        		}
                                        		
                                        		if(user.getStatus().equalsIgnoreCase("online")){
                                        	
                                        %>
                                        <div class="chat-user click-user " data-user_id="<%=user.getId()%>">
                                            <span class="pull-right label label-primary"><%=user.getStatus() %></span>
                                            <img class="chat-avatar" src="http://webapplayers.com/inspinia_rtl_v2.6.2/img/a2.jpg" alt="" >
                                            <div class="chat-user-name">
                                                <a ><%=user.getName() %></a>
                                            </div>
                                        </div>
                                        <%}}
                                    		 }} %>
                                       


                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="chat-message-form">

                                    <div class="form-group">

                                        <textarea class="form-control message-input" name="message" placeholder="Enter message text"></textarea>
                                    </div>

                                </div>
                            </div>
                        </div>


                    </div>

                </div>
        </div>

    </div>


</div>

			 <div id="chat_bot"></div>

			<div class="footer" >
    <div class="pull-right">
        10GB of <strong>250GB</strong> Free.
    </div>
    <div>
        <strong>Copyright</strong> Example Company &copy; 2014-2017
    </div>
</div>

</div>
    <div class="small-chat-box fadeInRight animated">

        <div class="heading" draggable="true">
            <small class="chat-date pull-right">
                02.19.2015
            </small>
            Small chat
        </div>

        <div class="content">

            <div class="left">
                <div class="author-name">
                    Monica Jackson <small class="chat-date">
                    10:02 am
                </small>
                </div>
                <div class="chat-message active">
                    Lorem Ipsum is simply dummy text input.
                </div>

            </div>
            <div class="right">
                <div class="author-name">
                    Mick Smith
                    <small class="chat-date">
                        11:24 am
                    </small>
                </div>
                <div class="chat-message">
                    Lorem Ipsum is simpl.
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Alice Novak
                    <small class="chat-date">
                        08:45 pm
                    </small>
                </div>
                <div class="chat-message active">
                    Check this stock char.
                </div>
            </div>
            <div class="right">
                <div class="author-name">
                    Anna Lamson
                    <small class="chat-date">
                        11:24 am
                    </small>
                </div>
                <div class="chat-message">
                    The standard chunk of Lorem Ipsum
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Mick Lane
                    <small class="chat-date">
                        08:45 pm
                    </small>
                </div>
                <div class="chat-message active">
                    I belive that. Lorem Ipsum is simply dummy text.
                </div>
            </div>


        </div>
        <div class="form-chat">
            <div class="input-group input-group-sm"><input type="text" class="form-control message-data"> <span class="input-group-btn"> <button
                    class="btn btn-primary sendall" type="button">Send
            </button> </span></div>
        </div>

    </div>
    <div id="small-chat">

        <span class="badge badge-warning pull-right">5</span>
        <a class="open-small-chat">
            <i class="fa fa-comments"></i>

        </a>
    </div>
</div>



<!-- Mainly scripts -->
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/jquery-2.1.1.js"></script>
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/bootstrap.min.js"></script>
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/inspinia.js"></script>
<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/plugins/pace/pace.min.js"></script>

<script src="http://webapplayers.com/inspinia_rtl_v2.6.2/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="websocketDemo.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
    console.log( "ready!" );
    $('.click-user').unbind().click(function (){
        console.log( $(this).attr('data-user_id') );
        var collegue_id = $(this).attr('data-user_id')
       var chat_title= $('.ibox-title');
        var name=$(this).find('.chat-user-name').text().trim();
      //  webSocket.send("chat with" +name);
       
       chat_title.text('Chat with  :- ' +name);
       $('.panel-heading').text(name);
       $('.panel-heading').attr('data-receiver_id',collegue_id);
       
       $.get( "addbotchat", { sender_id: <%= current_user.getId()%> ,receiver_id :collegue_id} )
 	  .done(function( data ) {
 	   		$('#chat_bot').html(data);
 	   		setTimeout(function(){
 	   			$('.chat-sents').unbind().click(function(){
 	   				var msg= $('.user-text-message').val();
 	   				var sender_name=$('.current_user_id').attr('data-user_name');
 	   				var left_div='<div class="chat-message left" style="padding:0px !important;margin-top:10px;margin-bottom:10px;margin-right:10px;"> <img class="message-avatar" src="img/a1.jpg" alt=""> <div class="message"> <a class="message-author" href="#"> '+sender_name+' </a> <span class="message-date"> Mon Jan 26 2015 - 18:39:23 </span> <span class="message-content"> '+ msg+'</span> </div> </div>'
 	   				$('.chat-discussion').append(left_div);
 	   			 $('.user-text-message').val('');
 	   			$(".chat-discussion").animate({ scrollTop: 20000000 }, "slow");
 	   			
 	   		$.get( "sendchatmessage", { message: msg,id:collegue_id  } )
 	    	  .done(function( data ) {
 	    	    //alert( "Data Loaded: " + data );
 	    	  });
 	   			
 	   			})
 	   		},200)
 	  });
    	
    });
    
    
    
});

</script>
</body>

</html>
