package com.adidas.subscriptionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private boolean consentGranted;
    private String newsletterId;
}
