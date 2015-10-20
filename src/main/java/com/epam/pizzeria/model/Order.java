package com.epam.pizzeria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapKeyColumn(name = "UserID")
    User user;

//    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
////    @JoinTable(name="OrderDeatils", joinColumns = @JoinColumn(name = "order_id"),
////            inverseJoinColumns = @JoinColumn(name = "good_id"))
//    Set<Good> goods = new HashSet<Good>();

}
