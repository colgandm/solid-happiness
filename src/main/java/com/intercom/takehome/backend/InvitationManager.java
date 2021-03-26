package com.intercom.takehome.backend;


import com.google.gson.Gson;
import com.intercom.takehome.backend.exception.FileRetrievalException;
import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.model.Invitation;
import com.intercom.takehome.backend.services.CustomerRecordRetrieverService;
import com.intercom.takehome.backend.services.InvitationGeneratorService;
import com.intercom.takehome.backend.utils.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InvitationManager {

    @Autowired
    private Gson gson;

    @Autowired
    private CustomerRecordRetrieverService customerRecordRetrieverService;

    @Autowired
    private InvitationGeneratorService invitationGeneratorService;

    @Autowired
    private FileWriter fileWriter;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${customer.records.file.name}")
    private String fileName;

    public void createInvitationListFromFile() {
        log.info("Creating Customer Invitation List.");
        try {
            List<CustomerRecord> customerRecords = customerRecordRetrieverService.retrieveCustomerRecordsFromFile("/" + fileName);
            createInvitationListFromCustomerRecords(customerRecords);
        } catch (FileRetrievalException ex) {
            log.error("Error Occurred Retrieving Customer Records file", ex);
        }
    }

    public void createInvitationListFromS3() {
        log.info("Creating Customer Invitation List.");
        try {
            List<CustomerRecord> customerRecords = customerRecordRetrieverService.retrieveCustomerRecordsFromS3Bucket(bucketName, fileName);
            createInvitationListFromCustomerRecords(customerRecords);
        } catch (FileRetrievalException ex) {
            log.error("Error Occurred Retrieving Customer Records file", ex);
        }
    }

    private void createInvitationListFromCustomerRecords(List<CustomerRecord> customerRecords) {
        if (customerRecords.isEmpty()) {
            log.warn("Cannot create Invitation List as there are no Customer Records.");
            return;
        }
        List<Invitation> invitations = invitationGeneratorService.createInvitationListFromCustomerRecords(customerRecords);
        if (invitations.isEmpty()) {
            log.warn("No Customers within the required distance.");
            return;
        }
        fileWriter.writeInvitationsToFile(invitations);
    }
}
