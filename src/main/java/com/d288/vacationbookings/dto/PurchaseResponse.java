package com.d288.vacationbookings.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {

    @NonNull
    private String orderTrackingNumber;
}
