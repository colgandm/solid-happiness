package com.intercom.takehome.backend.services;

import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.model.Invitation;

import java.util.List;

public interface InvitationGeneratorService {

    /**
     * Returns a List of Invitation, created from a List of Customer Records
     *
     * @param customerRecordList List of Customer Records to use for  InvitationList creation.
     * @return List of Invitations
     */
    List<Invitation> createInvitationListFromCustomerRecords(List<CustomerRecord> customerRecordList);
}
