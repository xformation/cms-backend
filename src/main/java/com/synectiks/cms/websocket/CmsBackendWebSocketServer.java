package com.synectiks.cms.websocket;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebSocket handler
 *
 */

public class CmsBackendWebSocketServer extends WebSocketServer {

	private final static Logger logger = LoggerFactory.getLogger(CmsBackendWebSocketServer.class);
    
//    @Autowired
//    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

	private Set<WebSocket> conns;

	public CmsBackendWebSocketServer(int port) {
		super(new InetSocketAddress(port));
		conns = new HashSet<>();
	}
	
	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		conns.add(webSocket);
		logger.info("Connection established from: " + webSocket.getRemoteSocketAddress().getHostString());
        System.out.println("New connection from " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		conns.remove(conn);
		logger.info("Connection closed to: " + conn.getRemoteSocketAddress().getHostString());
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}
	
	@Override
	public void onMessage(WebSocket conn, String message) {
		logger.debug("Message received from UI client:: "+message);
		for (WebSocket sock : conns) {
            sock.send("Sending message to UI client :::::::::::");
        }
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		if (conn != null) {
            conns.remove(conn);
        }
		assert conn != null;
        logger.error("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

    
	
}
