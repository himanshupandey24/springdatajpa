package com.springdatajpa.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_generator"
    )
    @SequenceGenerator(
            name = "order_generator",
            sequenceName = "order_sequence_generator",
            allocationSize = 1
    )
    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    /*
    Unidirectional mapping of Order to Address
    //@JoinColumn for Foreign Key Name and referencedColumnName refer to Primary key of Address table
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;
    */

    //Bidirectional Mapping
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;

    /*One to Many Unidirectional Mapping, Default Fetch Type for OneToMany is Lazy*/
    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();*/

    //One to Many Bi-Directional Mapping
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal("0.0");
        for(OrderItem orderItem : this.orderItems)
            amount = amount.add(orderItem.getPrice());

        return amount;
    }
}
