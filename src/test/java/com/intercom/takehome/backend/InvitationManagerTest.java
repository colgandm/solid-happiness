package com.intercom.takehome.backend;

import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.model.Invitation;
import com.intercom.takehome.backend.services.CustomerRecordRetrieverService;
import com.intercom.takehome.backend.services.InvitationGeneratorService;
import com.intercom.takehome.backend.utils.FileWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.util.List;

import static com.intercom.takehome.backend.helper.TestUtils.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvitationManagerTest {

    @InjectMocks
    private InvitationManager unitUnderTest;

    @Mock
    private CustomerRecordRetrieverService customerRecordRetrieverService;

    @Mock
    private InvitationGeneratorService invitationGeneratorService;

    @Mock
    private FileWriter fileWriter;

    @Before
    public void init() {
        ReflectionTestUtils.setField(unitUnderTest, "bucketName", S_3_BUCKET);
        ReflectionTestUtils.setField(unitUnderTest, "fileName", FILE_NAME);
    }

    @Test
    public void shouldCreateInvitationListFromFile() throws FileNotFoundException {
        List<CustomerRecord> customerRecords = getCustomerRecordList();
        List<Invitation> invitations = getInvitationList();
        when(customerRecordRetrieverService.retrieveCustomerRecordsFromFile(anyString())).thenReturn(customerRecords);
        when(invitationGeneratorService.createInvitationListFromCustomerRecords(customerRecords)).thenReturn(invitations);
        unitUnderTest.createInvitationListFromFile();
        verify(customerRecordRetrieverService, times(1)).retrieveCustomerRecordsFromFile(anyString());
        verify(invitationGeneratorService, times(1)).createInvitationListFromCustomerRecords(anyList());
        verify(fileWriter, times(1)).writeInvitationsToFile(anyList());
    }

    @Test
    public void shouldCreateInvitationListFromS3() throws FileNotFoundException {
        List<CustomerRecord> customerRecords = getCustomerRecordList();
        List<Invitation> invitations = getInvitationList();
        when(customerRecordRetrieverService.retrieveCustomerRecordsFromS3Bucket(anyString(), anyString())).thenReturn(customerRecords);
        when(invitationGeneratorService.createInvitationListFromCustomerRecords(customerRecords)).thenReturn(invitations);
        unitUnderTest.createInvitationListFromS3();
        verify(customerRecordRetrieverService, times(1)).retrieveCustomerRecordsFromS3Bucket(anyString(), anyString());
        verify(invitationGeneratorService, times(1)).createInvitationListFromCustomerRecords(anyList());
        verify(fileWriter, times(1)).writeInvitationsToFile(anyList());
    }

}