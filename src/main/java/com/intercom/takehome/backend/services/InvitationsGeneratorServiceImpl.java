package com.intercom.takehome.backend.services;

import com.intercom.takehome.backend.model.CustomerRecord;
import com.intercom.takehome.backend.model.Invitation;
import com.intercom.takehome.backend.utils.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class InvitationsGeneratorServiceImpl implements InvitationGeneratorService {

    @Value("${dublin.office.invitation.range}")
    private double invitationRange;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private CustomerRecordRetrieverService customerRecordRetrieverService;

    public List<Invitation> createInvitationListFromCustomerRecords(List<CustomerRecord> customerRecordList) {
        List<Invitation> invitationList = new ArrayList<>();
        for (CustomerRecord customerRecord : customerRecordList) {
            if (isCustomerWithinInvitationRange(customerRecord)) {
                invitationList.add(new Invitation(customerRecord.getUserId(), customerRecord.getName()));
            }
        }
        invitationList.sort(Comparator.comparingInt(Invitation::getUserId));
        log.debug("Number of Customer on Invitation List : {}", invitationList.size());
        return invitationList;
    }

    private boolean isCustomerWithinInvitationRange(CustomerRecord customerRecord) {
        log.debug("Checking if customer: {} with Id :{} , is with invitation range : {} .", customerRecord.getName(), customerRecord.getUserId(), invitationRange);
        return distanceCalculator.findDistanceToCustomer(customerRecord) <= invitationRange;
    }

}
