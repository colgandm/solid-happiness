package com.intercom.takehome.backend.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.Gson;
import com.intercom.takehome.backend.exception.FileRetrievalException;
import com.intercom.takehome.backend.model.CustomerRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerRecordRetrieverServiceImpl implements CustomerRecordRetrieverService {

    @Autowired
    private Gson gson;
    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public List<CustomerRecord> retrieveCustomerRecordsFromFile(String fileName) throws FileRetrievalException {
        List<CustomerRecord> customerRecordList = new ArrayList<>();
        try {
            log.info("Retrieving customer records from file : {}", fileName);
            InputStream in = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                customerRecordList.add(convertLineToCustomerRecord(line));
            }
            reader.close();

        } catch (NullPointerException | IOException ex) {
            log.error("Error Occurred reading customerRecords from file.");
            throw new FileRetrievalException(ex);
        }
        log.debug("Number of Customer records returned : {}", customerRecordList.size());
        return customerRecordList;
    }

    @Override
    public List<CustomerRecord> retrieveCustomerRecordsFromS3Bucket(String bucketName, String fileName) throws FileRetrievalException {
        List<CustomerRecord> customerRecordList = new ArrayList<>();
        try {
            log.info("Retrieving customer records file : {} from s3 bucket : {}", fileName, bucketName);
            S3Object s3object = amazonS3.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                customerRecordList.add(convertLineToCustomerRecord(line));
            }
            bufferedReader.close();
            inputStream.close();
        } catch (NullPointerException | IOException ex) {
            log.error("Error Occurred reading customerRecords from file.");
            throw new FileRetrievalException(ex);
        }
        log.debug("Number of Customer records returned : {}", customerRecordList.size());
        return customerRecordList;
    }

    private CustomerRecord convertLineToCustomerRecord(String line) {
        log.debug("Converting line to customer record : {}  ", line);
        return gson.fromJson(line, CustomerRecord.class);
    }
}


