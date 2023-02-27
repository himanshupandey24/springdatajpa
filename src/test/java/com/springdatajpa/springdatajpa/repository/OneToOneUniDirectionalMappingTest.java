package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Address;
import com.springdatajpa.springdatajpa.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class OneToOneUniDirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderMethod(){
        Order order = Order.builder()
                .orderTrackingNumber("100ABC")
                .totalQuantity(5)
                .status("IN PROGRESS")
                .totalPrice(new BigDecimal(10000))
                .build();

        Address address = Address.builder()
                .street("Singar Nagar")
                .city("Lucknow")
                .state("Uttar Pradesh")
                .country("India")
                .zipcode("226005")
                .build();

        order.setBillingAddress(address);
        orderRepository.save(order);
    }

    @Test
    void getOrder(){
        Optional<Order> order = orderRepository.findById(2L);
        order.ifPresent(System.out::println);
    }

    @Test
    void updateOrder(){
        Optional<Order> orderOptional = orderRepository.findById(2L);
        orderOptional.ifPresent(order -> {
            order.setStatus("Delivered");
            //order.getBillingAddress().setZipcode("226001");
            orderRepository.save(order);
        });
    }

    @Test
    void deleteOrder(){
        orderRepository.deleteById(1L);
    }
}
