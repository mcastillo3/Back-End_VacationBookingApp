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
        cart.setStatus(StatusType.ordered);
        Customer customer = purchase.getCustomer();

        // populate and set cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            item.setCart(cart);

            Vacation vacation = item.getVacation();
            Set<Excursion> excursions = item.getExcursions();
            for (Excursion excursion : excursions) {
                excursion.setVacation(vacation);
            }
/*            item.getExcursions().forEach(excursion -> {
                excursion.setVacation(item.getVacation());
                excursion.getCartItems().add(item);
            });*/
        });
        //customer.add(cart);

        // check if cart is empty to either get a tracking number or 'empty' message
        String orderTrackingNumber = "";
        if (purchase.getCart() == null || purchase.getCartItems().isEmpty()) {
            orderTrackingNumber = "Cart is empty";
        } else {
            orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);
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