package com.d288.vacationbookings.services;

import com.d288.vacationbookings.dao.CartRepository;
import com.d288.vacationbookings.dao.CustomerRepository;
import com.d288.vacationbookings.dto.Purchase;
import com.d288.vacationbookings.dto.PurchaseResponse;
import com.d288.vacationbookings.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl (CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Cart cart = purchase.getCart();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            item.setCart(cart);
            cart.setCartItem(cartItems);
            cart.add(item);
        });

        // populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // save to the database
        // customerRepository.save(customer);
        //cartRepository.save(cart);
        //cart.setStatus(StatusType.ordered);

        // return a response
        if (purchase.getCart() == null || purchase.getCartItems().isEmpty()) {
            return new PurchaseResponse("Cart is Empty");
        } else {
            return new PurchaseResponse(orderTrackingNumber);
        }
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}