package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Address;
import com.springdatajpa.springdatajpa.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;


@SpringBootTest
public class OneToOneBiDirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void savedAddress(){

        Order order = Order.builder()
                .orderTrackingNumber("1001ABC")
                .totalQuantity(10)
                .status("IN PROGRESS")
                .totalPrice(new BigDecimal(1542.00))
                .build();

        Address address = Address.builder()
                .street("Hazratganj")
                .city("Lucknow")
                .state("Uttar Pradesh")
                .country("India")
                .zipcode("226005")
                .build();

        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address);
    }

    @Test
    void savedOrder(){

        Order order = Order.builder()
                .orderTrackingNumber("1002ABC")
                .totalQuantity(12)
                .status("IN PROGRESS")
                .totalPrice(new BigDecimal(1942.00))
                .build();

        Address address = Address.builder()
                .street("Hazratganj")
                .city("Lucknow")
                .state("Uttar Pradesh")
                .country("India")
                .zipcode("226005")
                .build();

        order.setBillingAddress(address);
        address.setOrder(order);
        orderRepository.save(order);
    }

    @Test
    void updateAddress(){
        Optional<Address> addressOptional = addressRepository.findById(1L);
        addressOptional.ifPresent(address -> {
            address.setZipcode("226002");
            address.getOrder().setStatus("DELIVERED");
            addressRepository.save(address);
        });

    }

    @Test
    void fetchAddress(){
      Optional<Address> addressOptional = addressRepository.findById(2L);
      //addressOptional.ifPresent(System.out::println);
    }

    @Test
    void fetchOrder(){
        Optional<Order> orderOptional = orderRepository.findById(2L);
    }

    @Test
    void deleteAddress(){
        addressRepository.deleteById(3L);
    }
}
