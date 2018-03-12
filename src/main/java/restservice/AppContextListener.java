package restservice;
import static spark.Spark.init;
import static spark.Spark.webSocket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.EchoWebSocket;
import com.PingWebSocket;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("destroying listner ...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("initalizing listner ...");
		 webSocket("/echo/*", EchoWebSocket.class);
	        webSocket("/ping", PingWebSocket.class);
	        init();
	}

}
