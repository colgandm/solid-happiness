package com.intercom.takehome.backend.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRecord {
    @SerializedName("user_id")
    private int userId;
    private String name;
    private double latitude;
    private double longitude;
}
