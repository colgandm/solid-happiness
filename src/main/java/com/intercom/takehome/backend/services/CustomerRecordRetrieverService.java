package com.intercom.takehome.backend.services;

import com.intercom.takehome.backend.exception.FileRetrievalException;
import com.intercom.takehome.backend.model.CustomerRecord;

import java.util.List;

public interface CustomerRecordRetrieverService {

    /**
     * Returns a List of CustomerRecords, retrieved from a file.
     *
     * @param fileName Name of Customer Records File
     * @return List of CustomerRecords
     * @throws FileRetrievalException Exception throw if error Retrieving File
     */
    List<CustomerRecord> retrieveCustomerRecordsFromFile(String fileName) throws FileRetrievalException;

    /**
     * Returns a List of CustomerRecords, retrieved from a given S3Bucket and file.
     *
     * @param bucketName Name of S3Bucket
     * @param fileName   Name of Customer Records File
     * @return List of CustomerRecords
     * @throws FileRetrievalException Exception throw if error Retrieving File
     */
    List<CustomerRecord> retrieveCustomerRecordsFromS3Bucket(String bucketName, String fileName) throws FileRetrievalException;

}
