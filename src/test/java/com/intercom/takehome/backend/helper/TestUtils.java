package com.intercom.takehome.backend.helper;

import com.google.gson.Gson;
import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.model.Invitation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    @Autowired
    protected Gson gson;

    public static final int EXPECTED_NUMBER_OF_USERS = 32;
    public static final String S_3_BUCKET = "intercom-take-home-test";
    public static final String FILE_NAME = "/customers.txt";
    public static final String NON_EXISTENT_FILE_NAME = "/not_there.txt";
    public static final String BROKEN_CUSTOMER_FILE = "/customers_broken.txt";
    public static final String EMPTY_CUSTOMER_FILE = "/customers_empty.txt";
    public static final String OUTPUT_FILE_NAME = "output.txt";
    public static final String CUSTOMER_NAME = "BOB";
    public static final double LONGITUDE_GT = -4.5;
    public static final double LATITUDE_GT = 53.339428;
    public static final double LONGITUDE_LT = -6.0;
    public static final double LATITUDE_LT = 53.339428;
    public static final double DEST_LONGITUDE = -6.257664;
    public static final double DEST_LATITUDE = 53.339428;
    public static final double ONE_HUNDRED = 100;


    public static List<CustomerRecord> getCustomerRecordList() {
        List<CustomerRecord> records = new ArrayList<>();
        CustomerRecord customerRecord = CustomerRecord.builder()
                .name(CUSTOMER_NAME)
                .userId(1)
                .latitude(LONGITUDE_LT)
                .longitude(LATITUDE_LT)
                .build();
        records.add(customerRecord);
        return records;
    }

    public static List<Invitation> getInvitationList() {
        List<Invitation> invitations = new ArrayList<>();
        Invitation invitation = Invitation.builder()
                .name(CUSTOMER_NAME)
                .userId(1)
                .build();
        invitations.add(invitation);
        return invitations;
    }

    public static CustomerRecord getCustomerRecord(double longitude, double latitude) {
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setName(CUSTOMER_NAME);
        customerRecord.setLongitude(longitude);
        customerRecord.setLatitude(latitude);
        return customerRecord;
    }
}
