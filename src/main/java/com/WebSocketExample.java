package com;

import static spark.Spark.init;
import static spark.Spark.webSocket;

public class WebSocketExample {

    public static void main(String[] args) {
        webSocket("/echo", EchoWebSocket.class);
        webSocket("/ping", PingWebSocket.class);
        init();
    }
}