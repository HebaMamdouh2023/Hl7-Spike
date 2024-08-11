package com.hapi.HAPI;

import ca.uhn.hl7v2.app.SimpleServer;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import org.springframework.stereotype.Component;

@Component
public class HL7TCPServer {

    private static final int PORT_NUMBER = 8888;
    private SimpleServer hl7Server;

    public void startServer(ReceivingApplication hl7Application) {
        // Create a pipe parser
        Parser parser = new PipeParser();

        // Create an HL7 server using MinLowerLayerProtocol (MLLP)
        LowerLayerProtocol llp = new MinLowerLayerProtocol();
        hl7Server = new SimpleServer(PORT_NUMBER, llp, parser);

        // Set up a receiving application
        hl7Server.registerApplication(hl7Application);

        // Start the server
        hl7Server.start();
        System.out.println("HL7 TCP/IP Server is running on port " + PORT_NUMBER);
    }

    public void stopServer() {
        if (hl7Server != null) {
            hl7Server.stop();
            System.out.println("HL7 TCP/IP Server has been stopped.");
        }
    }
}
