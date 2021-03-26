package com.intercom.takehome.backend.utils;

import com.intercom.takehome.backend.model.Invitation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.List;

import static com.intercom.takehome.backend.helper.TestUtils.OUTPUT_FILE_NAME;
import static com.intercom.takehome.backend.helper.TestUtils.getInvitationList;

@RunWith(MockitoJUnitRunner.class)
public class FileWriterTest {

    @InjectMocks
    private FileWriter unitUnderTest;

    @Before
    public void init() {
        ReflectionTestUtils.setField(unitUnderTest, "outputFileName", OUTPUT_FILE_NAME);
    }

    @Test
    public void shouldWriteInvitationsToFile() {
        List<Invitation> invitations = getInvitationList();
        unitUnderTest.writeInvitationsToFile(invitations);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPoinrterException() {
        List<Invitation> invitations = null;
        unitUnderTest.writeInvitationsToFile(invitations);
    }

}