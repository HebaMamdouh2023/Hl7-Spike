package com.hapi.HAPI;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v231.message.ORM_O01;
import ca.uhn.hl7v2.model.v231.segment.*;
import org.springframework.stereotype.Component;

@Component
public class HL7ORMParser {

    public void parseORMMessage(ORM_O01 ormMessage) throws HL7Exception {
        // Parse MSH Segment
        MSH msh = ormMessage.getMSH();
        System.out.println("MSH Segment:");
        System.out.println("Field Separator: " + msh.getFieldSeparator().getValue());
        System.out.println("Encoding Characters: " + msh.getEncodingCharacters().getValue());
        System.out.println("Sending Application: " + msh.getSendingApplication().getNamespaceID().getValue());
        System.out.println("Sending Facility: " + msh.getSendingFacility().getNamespaceID().getValue());
        System.out.println("Receiving Application: " + msh.getReceivingApplication().getNamespaceID().getValue());
        System.out.println("Receiving Facility: " + msh.getReceivingFacility().getNamespaceID().getValue());
        System.out.println("Date/Time of Message: " + msh.getDateTimeOfMessage().getTimeOfAnEvent().getValue());
        System.out.println("Message Type: " + msh.getMessageType().getMessageType().getValue());
        System.out.println("Message Control ID: " + msh.getMessageControlID().getValue());
        System.out.println("Processing ID: " + msh.getProcessingID().getProcessingID().getValue());
        System.out.println("Version ID: " + msh.getVersionID().getVersionID().getValue());
        System.out.println();

        // Parse PID Segment
        PID pid = ormMessage.getPIDPD1NTEPV1PV2IN1IN2IN3GT1AL1().getPID();
        System.out.println("PID Segment:");
        System.out.println("Patient ID: " + (pid.getPatientID() != null ? pid.getPatientID().getID().getValue() : "N/A"));
        if (pid.getPatientName().length > 0) {
            System.out.println("Patient Name: " + pid.getPatientName()[0].getGivenName().getValue() + " " + pid.getPatientName()[0].getFamilyLastName().getFamilyName().getValue());
        } else {
            System.out.println("Patient Name: N/A");
        }
        System.out.println("Patient Birth Date: " + (pid.getDateTimeOfBirth().getTimeOfAnEvent().getValue() != null ? pid.getDateTimeOfBirth().getTimeOfAnEvent().getValue() : "N/A"));
        System.out.println("Patient Sex: " + (pid.getSex().getValue() != null ? pid.getSex().getValue() : "N/A"));
        if (pid.getPatientAddress().length > 0) {
            System.out.println("Patient Address: " + pid.getPatientAddress()[0].getStreetAddress().getValue());
        } else {
            System.out.println("Patient Address: N/A");
        }
        System.out.println();

        // Parse ORC Segment
        ORC orc = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getORC();
        System.out.println("ORC Segment:");
        System.out.println("Order Control: " + orc.getOrderControl().getValue());
        System.out.println("Placer Order Number: " + orc.getPlacerOrderNumber().getEntityIdentifier().getValue());
        System.out.println("Filler Order Number: " + orc.getFillerOrderNumber().getEntityIdentifier().getValue());
        System.out.println("Entered By: " + orc.getEnteredBy(0).getIDNumber().getValue());
        System.out.println("Ordering Provider: " + orc.getOrderingProvider(0).getIDNumber().getValue());
        System.out.println();

        // Parse OBR Segment
        OBR obr = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTE().getOBR();
        System.out.println("OBR Segment:");
        System.out.println("Set ID: " + obr.getSetIDOBR().getValue());
        System.out.println("Placer Order Number: " + obr.getPlacerOrderNumber().getEntityIdentifier().getValue());
        System.out.println("Filler Order Number: " + obr.getFillerOrderNumber().getEntityIdentifier().getValue());
        System.out.println("Universal Service ID: " + obr.getUniversalServiceID().getIdentifier().getValue());
        System.out.println("Observation Date/Time: " + obr.getObservationDateTime().getTimeOfAnEvent().getValue());
        System.out.println("Observation End Date/Time: " + obr.getObservationEndDateTime().getTimeOfAnEvent().getValue());
        System.out.println();

        // Parse BLG Segment
        BLG blg = ormMessage.getORCOBRRQDRQ1ODSODTRXONTEDG1RXRRXCNTEOBXNTECTIBLG().getBLG();
        System.out.println("BLG Segment:");
        System.out.println("When to Charge: " + blg.getWhenToCharge().getCcd1_WhenToChargeCode().getValue());
        System.out.println();
    }
}
