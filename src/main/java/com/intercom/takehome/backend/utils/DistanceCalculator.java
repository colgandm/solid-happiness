package com.intercom.takehome.backend.utils;

import com.intercom.takehome.backend.model.CustomerRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DistanceCalculator {

    private final double RADIUS = 6371;
    @Value("${dublin.office.location.longitude}")
    private double longitude;
    @Value("${dublin.office.location.latitude}")
    private double latitude;

    public double findDistanceToCustomer(CustomerRecord customerRecord) {

        double customerLat = customerRecord.getLatitude();
        double customerLong = customerRecord.getLongitude();

        double latDistance = Math.toRadians(customerLat - latitude);
        double lonDistance = Math.toRadians(customerLong - longitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(customerLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = RADIUS * c;

        return distance;
    }
}
