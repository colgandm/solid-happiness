package com.intercom.takehome.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.takehome.backend.model.Invitation;
import com.intercom.takehome.backend.services.CustomerRecordRetrieverService;
import com.intercom.takehome.backend.services.InvitationsGeneratorServiceImpl;
import com.intercom.takehome.backend.utils.DistanceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.util.List;

import static com.intercom.takehome.backend.helper.TestUtils.ONE_HUNDRED;
import static com.intercom.takehome.backend.helper.TestUtils.getCustomerRecordList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InvitationsGeneratorServiceImplTest {

    protected Gson gson;

    @InjectMocks
    private InvitationsGeneratorServiceImpl unitUnderTest;

    @Mock
    private DistanceCalculator distanceCalculator;

    @Mock
    private CustomerRecordRetrieverService customerRecordRetrieverService;

    @Before
    public void init() {
        gson = new GsonBuilder().create();
        ReflectionTestUtils.setField(unitUnderTest, "invitationRange", ONE_HUNDRED);
    }

    @Test
    public void shouldCreateInvitationList() throws FileNotFoundException {
        List<Invitation> actualInvitationList = unitUnderTest.createInvitationListFromCustomerRecords(getCustomerRecordList());
        assertEquals(1, actualInvitationList.size());
    }

}