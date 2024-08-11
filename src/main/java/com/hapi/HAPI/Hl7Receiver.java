package com.hapi.HAPI;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.SimpleServer;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.ORM_O01;
import ca.uhn.hl7v2.model.v231.segment.*;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Hl7Receiver{ //implements CommandLineRunner {

//    private static final int PORT_NUMBER = 8888;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create a pipe parser
//        Parser parser = new PipeParser();
//
//        // Create an HL7 server using MinLowerLayerProtocol (MLLP)
//        LowerLayerProtocol llp = new MinLowerLayerProtocol();
//        SimpleServer hl7Server = new SimpleServer(PORT_NUMBER, llp, parser);
//
//        // Set up a receiving application
//        ReceivingApplication application = new HL7Application();
//        hl7Server.registerApplication(application);
//
//        // Start the server
//        hl7Server.start();
//
//        System.out.println("HL7 TCP/IP Server is running on port " + PORT_NUMBER);
//    }
//
//    private static class HL7Application implements ReceivingApplication {
//
//        @Override
//        public boolean canProcess(Message message) {
//            // Check if the message is of type ORM^O01
//            System.out.println("Received ORM message " + message);
//
//            // Ensure the message is an ORM_O01 (version 2.3.1)
//            if (message instanceof ORM_O01) {
//                ORM_O01 ormMessage = (ORM_O01) message;
//                System.out.println("Parsed ORM message " + ormMessage);
//                return true;
//            } else {
//                System.out.println("Received a non-ORM_O01 message.");
//                return false;
//            }
//        }
//
//        @Override
//        public Message processMessage(Message message, Map<String, Object> metadata) throws ReceivingApplicationException, HL7Exception {
//            try {
//                System.out.println("Processing message:\n" + message);
//
//                if (message instanceof ORM_O01) {
//                    ORM_O01 ormMessage = (ORM_O01) message;
//
//                    // Parse MSH Segment
//                    MSH msh = ormMessage.getMSH();
//                    System.out.println("MSH Segment:");
//                    System.out.println("Field Separator: " + msh.getFieldSeparator().getValue());
//                    System.out.println("Encoding Characters: " + msh.getEncodingCharacters().getValue());
//                    System.out.println("Sending Application: " + msh.getSendingApplication().getNamespaceID().getValue());
//                    System.out.println("Sending Facility: " + msh.getSendingFacility().getNamespaceID().getValue());
//                    System.out.println("Receiving Application: " + msh.getReceivingApplication().getNamespaceID().getValue());
//                    System.out.println("Receiving Facility: " + msh.getReceivingFacility().getNamespaceID().getValue());
//                    System.out.println("Date/Time of Message: " + msh.getDateTimeOfMessage().getTimeOfAnEvent().getValue());
//                    System.out.println("Message Type: " + msh.getMessageType().getMessageType().getValue());
//                    System.out.println("Message Control ID: " + msh.getMessageControlID().getValue());
//                    System.out.println("Processing ID: " + msh.getProcessingID().getProcessingID().getValue());
//                    System.out.println("Version ID: " + msh.getVersionID().getVersionID().getValue());
//                    System.out.println();
//
//                    // Parse PID Segment
//                    PID pid = ormMessage.getPIDPD1NTEPV1PV2IN1IN2IN3GT1AL1().getPID(); // Ensure you use getPATIENT() to access the PID segment
//
//                    System.out.println("PID Segment:");
//                    System.out.println("Patient ID: " + (pid.getPatientID() != null ? pid.getPatientID().getID().getValue() : "N/A"));
//
//                    // Check if the patient name array has elements
//                    if (pid.getPatientName().length > 0) {
//                        System.out.println("Patient Name: " +
//                                pid.getPatientName()[0].getGivenName().getValue() + " " +
//                                pid.getPatientName()[0].getFamilyLastName().getFamilyName().getValue()
//                                        );
//                    } else {
//                        System.out.println("Patient Name: N/A");
//                    }
//
//                    System.out.println("Patient Birth Date: " + (pid.getDateTimeOfBirth().getTimeOfAnEvent().getValue() != null ? pid.getDateTimeOfBirth().getTimeOfAnEvent().getValue() : "N/A"));
//                    System.out.println("Patient Sex: " + (pid.getSex().getValue() != null ? pid.getSex().getValue() : "N/A"));
//
//                    // Check if the patient address array has elements
//                    if (pid.getPatientAddress().length > 0) {
//                        System.out.println("Patient Address: " + pid.getPatientAddress()[0].getStreetAddress().getValue());
//                    } else {
//                        System.out.println("Patient Address: N/A");
//                    }
//
//                    System.out.println();
//
//                    // Parse ORC Segment
//                    ORC orc = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getORC();
//                    System.out.println("ORC Segment:");
//                    System.out.println("Order Control: " + orc.getOrderControl().getValue());
//                    System.out.println("Placer Order Number: " + orc.getPlacerOrderNumber().getEntityIdentifier().getValue());
//                    System.out.println("Filler Order Number: " + orc.getFillerOrderNumber().getEntityIdentifier().getValue());
//                    System.out.println("Entered By: " + orc.getEnteredBy(0).getIDNumber().getValue());
//                    System.out.println("Ordering Provider: " + orc.getOrderingProvider(0).getIDNumber().getValue());
//                    System.out.println();
//
//                    // Parse OBR Segment
//                    OBR obr = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTE().getOBR();
//                    System.out.println("OBR Segment:");
//                    System.out.println("Set ID: " + obr.getSetIDOBR().getValue());
//                    System.out.println("Placer Order Number: " + obr.getPlacerOrderNumber().getEntityIdentifier().getValue());
//                    System.out.println("Filler Order Number: " + obr.getFillerOrderNumber().getEntityIdentifier().getValue());
//                    System.out.println("Universal Service ID: " + obr.getUniversalServiceID().getIdentifier().getValue());
//                    System.out.println("Observation Date/Time: " + obr.getObservationDateTime().getTimeOfAnEvent().getValue());
//                    System.out.println("Observation End Date/Time: " + obr.getObservationEndDateTime().getTimeOfAnEvent().getValue());
//                    System.out.println();
//
//                    // Parse BLG Segment
//                    BLG blg = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getBLG();
//                    System.out.println("BLG Segment:");
//                    System.out.println("When to Charge: " + blg.getWhenToCharge().getCcd1_WhenToChargeCode().getValue());
//                    System.out.println();
//                }
//
//                // Acknowledge the message
//                return message.generateACK();
//            }
//
//            catch (Exception e) {
//                e.printStackTrace();
//                throw new HL7Exception("Error processing message", e);
//            }
//        }
//    }
}
