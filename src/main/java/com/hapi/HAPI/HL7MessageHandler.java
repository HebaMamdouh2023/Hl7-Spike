package com.hapi.HAPI;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.ORM_O01;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class HL7MessageHandler implements ReceivingApplication {

    private final HL7ORMParser ormParser;

    public HL7MessageHandler(HL7ORMParser ormParser) {
        this.ormParser = ormParser;
    }

    @Override
    public boolean canProcess(Message message) {
        return message instanceof ORM_O01;
    }

    @Override
    public Message processMessage(Message message, Map<String, Object> metadata) throws ReceivingApplicationException, HL7Exception {
        try {
            if (message instanceof ORM_O01) {
                ORM_O01 ormMessage = (ORM_O01) message;
                ormParser.parseORMMessage(ormMessage);
                return ormMessage.generateACK(); // Acknowledge with AA
            } else {
                throw new HL7Exception("Received a non-ORM_O01 message.", HL7Exception.UNSUPPORTED_MESSAGE_TYPE);
            }
        } catch (HL7Exception e) {
            try {
                return message.generateACK("AE", new HL7Exception("Error processing message: " + e.getMessage()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            try {
                return message.generateACK("AR", new HL7Exception("Application error: " + e.getMessage()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
