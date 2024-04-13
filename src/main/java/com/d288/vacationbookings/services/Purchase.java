package com.d288.vacationbookings.services;

import com.d288.vacationbookings.entities.Cart;
import com.d288.vacationbookings.entities.CartItem;
import com.d288.vacationbookings.entities.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;

}
