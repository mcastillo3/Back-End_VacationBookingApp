package com.d288.vacationbookings.services;

import com.d288.vacationbookings.dao.CartItemRepository;
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

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl (CartRepository cartRepository,
                                CustomerRepository customerRepository,
                                CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order information info from dto
        Cart cart = purchase.getCart();
        Set<CartItem> cartItems = purchase.getCartItems();

        String orderTrackingNumber = "";

        // check for cart items and add to cart if any
        if (cartItems.isEmpty()) {
            orderTrackingNumber = "Cart is empty";
        } else {
            orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);
            cartItems.forEach(item -> {
                cart.add(item);

                Vacation vacation = item.getVacation();
                Set<Excursion> excursions = item.getExcursions();
                for (Excursion excursion : excursions) {
                    excursion.setVacation(vacation);
                }

            });
            cart.setStatus(StatusType.ordered);
        }

        // save cart to database
        cartRepository.save(cart);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }


    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}