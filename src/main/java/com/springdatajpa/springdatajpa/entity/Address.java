package com.springdatajpa.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_generator"
    )

    @SequenceGenerator(
            name = "address_generator",
            sequenceName = "address_sequence_generator",
            allocationSize = 1
    )

    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    //Bidirectional mapping of Address to Order
    //@JoinColumn for Foreign Key Name and referencedColumnName refer to Primary key of Order Table
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
