package com.d288.vacationbookings.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cart_items")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = false, insertable = false, updatable = false)
    private Vacation vacations;

//    @ManyToMany()
//    @JoinTable(name = "excursion_cartitem",
//            joinColumns = @JoinColumn(name = "excursion_id"),
//            inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
//    @ManyToMany(mappedBy = "cartItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "cartItem")
    private Set<Excursion> excursions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false, insertable = false, updatable = false)
    private Cart cart;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @Column(name = "cart_id")
    private Long cart_id;

    public void setCart(Cart cart) {
        setCart_id(cart.getId());
        this.cart = cart;
    }

    @Column(name = "vacation_id")
    private Long vacation_id;

    public void setVacation(Vacation vacation) {
        setVacation_id(vacation.getId());
        this.vacations = vacation;
    }
}
