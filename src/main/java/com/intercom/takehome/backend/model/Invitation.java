package com.intercom.takehome.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Invitation {
    private int userId;
    private String name;

    @Override
    public String toString() {
        return "userId=" + userId + ", name='" + name + '\'';
    }
}
