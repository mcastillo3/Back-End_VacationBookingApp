package com.d288.vacationbookings.services;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {

    @NonNull
    private String cartTrackingNumber;
}
