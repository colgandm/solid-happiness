package com.intercom.takehome.backend;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.takehome.backend.exception.FileRetrievalException;
import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.services.CustomerRecordRetrieverServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.intercom.takehome.backend.helper.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRecordRetrieverServiceTest {

    protected Gson gson;
    @InjectMocks
    private CustomerRecordRetrieverServiceImpl unitUnderTest;
    @Mock
    private AmazonS3 amazonS3;

    @Before
    public void init() {
        gson = new GsonBuilder().create();
        ReflectionTestUtils.setField(unitUnderTest, "gson", gson);
    }

    @Test
    public void shouldRetrieveCustomerRecordsFromFile() throws FileRetrievalException {
        List<CustomerRecord> actualCustomerRecordList = unitUnderTest.retrieveCustomerRecordsFromFile(FILE_NAME);
        assertEquals(EXPECTED_NUMBER_OF_USERS, actualCustomerRecordList.size());
    }

    @Test(expected = FileRetrievalException.class)
    public void shouldThrowExceptionRetrievingCustomerRecordsFromFile() throws FileRetrievalException {
        List<CustomerRecord> actualCustomerRecordList = unitUnderTest.retrieveCustomerRecordsFromFile(null);
        assertEquals(EXPECTED_NUMBER_OF_USERS, actualCustomerRecordList.size());
    }

    @Test
    public void shouldRetrieveCustomerRecordsFromS3() throws FileRetrievalException, IOException {
        when(amazonS3.getObject(S_3_BUCKET, FILE_NAME)).thenReturn(getS3Object());
        List<CustomerRecord> actualCustomerRecordList = unitUnderTest.retrieveCustomerRecordsFromS3Bucket(S_3_BUCKET, FILE_NAME);
        assertEquals(1, actualCustomerRecordList.size());
    }

    private S3Object getS3Object() throws IOException {
        InputStream objectContent = IOUtils.toInputStream("{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}", "UTF-8");
        S3Object object = new S3Object();
        object.setBucketName(S_3_BUCKET);
        object.setKey(FILE_NAME);
        object.setObjectContent(objectContent);

        return object;
    }
}