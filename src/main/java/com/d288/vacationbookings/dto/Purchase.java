package com.d288.vacationbookings.dto;

import com.d288.vacationbookings.entities.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;
}
