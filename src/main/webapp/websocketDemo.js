//Establish the WebSocket connection and set up event handlers


var current_user_id= $('.current_user_id').attr('data-user_id');
var webSocket = new WebSocket("ws://" + location.hostname + ":4567"+ "/echo"+"/"+current_user_id);
webSocket.onmessage = function (msg) { updateChat(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };

$(window).on("unload", function(e) {
    // Do Something
	webSocket.close();
});

//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
    if (message !== "") {
        webSocket.send(message);
        id("message").value = "";
    }
}

//Update the chat-panel, and the list of connected users
function updateChat(msg) {
	console.log("message reciever "+msg.data)
	var json= JSON.parse(msg.data);
	
    switch(json.type){
    case "connected":
    	if(parseInt(current_user_id) != parseInt(json.user_id)){
    		$.get( "fetchspecificuser", { id: json.user_id } )
    		  .done(function( data ) {
    		    alert( "Data Loaded: " + data );
    		    if(data !== "")
    		    $('.parent_chat').html(data);
    		  });
    	}
    	break;
    	
    case "chat_message":
    	var right_div='<div class="chat-message right" style="padding:0px !important;margin-top:10px;margin-bottom:10px;margin-left:10px;"> <img class="message-avatar" src="img/a4.jpg" alt=""> <div class="message"> <a class="message-author" > '+json.sender_name+' </a> <span class="message-date"> Fri Jan 25 2015 - 11:12:36 </span> <span class="message-content"> '+json.message+'</span> </div> </div>';
			$('.chat-discussion').append(right_div);
	   			$(".chat-discussion").animate({ scrollTop: 20000000 }, "slow");
    	break;
    }
	

}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}