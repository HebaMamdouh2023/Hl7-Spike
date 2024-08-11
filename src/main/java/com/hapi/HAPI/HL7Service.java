package com.hapi.HAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HL7Service {

    private final HL7TCPServer hl7TcpServer;
    private final HL7MessageHandler hl7MessageHandler;

    @Autowired
    public HL7Service(HL7TCPServer hl7TcpServer, HL7MessageHandler hl7MessageHandler) {
        this.hl7TcpServer = hl7TcpServer;
        this.hl7MessageHandler = hl7MessageHandler;
    }

    public void start() {
        hl7TcpServer.startServer(hl7MessageHandler);
    }

    public void stop() {
        hl7TcpServer.stopServer();
    }
}
