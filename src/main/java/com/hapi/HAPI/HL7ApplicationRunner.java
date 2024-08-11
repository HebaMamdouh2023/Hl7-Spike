package com.hapi.HAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HL7ApplicationRunner implements CommandLineRunner {

    private final HL7Service hl7Service;

    @Autowired
    public HL7ApplicationRunner(HL7Service hl7Service) {
        this.hl7Service = hl7Service;
    }

    @Override
    public void run(String... args) {
        hl7Service.start();
    }
}
