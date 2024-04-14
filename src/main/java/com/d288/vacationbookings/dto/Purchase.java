package com.d288.vacationbookings.dto;

import com.d288.vacationbookings.entities.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Cart cart;
    private CartItem cartItem;
    private Excursion excursion;
    private Purchase purchase;
    private Country country;
    private Set<CartItem> cartItems;

}
