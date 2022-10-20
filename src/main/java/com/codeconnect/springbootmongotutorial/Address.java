package com.codeconnect.springbootmongotutorial;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String city;
    private String postcode;
    private String country;
}
