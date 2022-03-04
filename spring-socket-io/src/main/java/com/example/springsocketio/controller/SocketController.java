package com.example.springsocketio.controller;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    private final SocketIONamespace namespace;

    @Autowired
    public SocketController(SocketIOServer server) {
        this.namespace = server.addNamespace("/ws");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("ws", String.class, onChatReceived());
    }

    private DataListener<String> onChatReceived() {
        return (client, data, ackSender) -> {
            namespace.getBroadcastOperations().sendEvent("ws", data);
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            System.out.println("연결됨");
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
        };
    }
}
