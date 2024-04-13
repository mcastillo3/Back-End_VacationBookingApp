package com.d288.vacationbookings.services;

import com.d288.vacationbookings.dao.CustomerRepository;
import com.d288.vacationbookings.entities.Cart;
import com.d288.vacationbookings.entities.CartItem;
import com.d288.vacationbookings.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from Purchase
        Cart cart = purchase.getCart();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}
