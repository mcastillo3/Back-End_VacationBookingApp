package com.d288.vacationbookings.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="vacations")
@Getter
@Setter
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long id;

    @Column(name = "vacation_title")
    private String vacation_title;

    @Column(name = "description")
    private String description;

    @Column(name = "travel_fare_price")
    private BigDecimal travel_price;

    @Column(name = "image_url")
    private String image_URL;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "last_update")
    private Date last_update;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacation")
    private Set<Excursion> excursions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacation")
    private Set<CartItem> cartItems = new HashSet<>();

    public void add(Excursion excursion) {
        if (excursion != null) {
            if (excursions == null) {
                excursions = new HashSet<>();
            }
            excursions.add(excursion);
            excursion.setVacation(this);
        }
    }

    public void add(CartItem cartItem) {
        if (cartItem != null) {
            if (cartItems == null) {
                cartItems = new HashSet<>();
            }
            cartItems.add(cartItem);
            cartItem.setVacation(this);
        }
    }
}
