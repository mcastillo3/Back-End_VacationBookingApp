package com.d288.vacationbookings.services;

import com.d288.vacationbookings.dto.Purchase;
import com.d288.vacationbookings.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
