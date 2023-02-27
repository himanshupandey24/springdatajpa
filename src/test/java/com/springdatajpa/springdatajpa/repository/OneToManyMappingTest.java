package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Address;
import com.springdatajpa.springdatajpa.entity.Order;
import com.springdatajpa.springdatajpa.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    //save order along with order items

    @Test
    void saveOrder(){

        Order order = Order.builder()
                .orderTrackingNumber("100ABC")
                .status("In Progress")
                .orderItems(new HashSet<>())
                .build();

        //Create Order Item 1

        OrderItem orderItem1 = OrderItem.builder()
                .product(productRepository.findById(1L).get())
                .quantity(2)
                .imageUrl(productRepository.findById(1L).get().getImageURl())
                .build();

        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(orderItem1.getQuantity())));
        order.getOrderItems().add(orderItem1);

        //Create Order Item 2

        OrderItem orderItem2 = OrderItem.builder()
                .product(productRepository.findById(2L).get())
                .quantity(3)
                .imageUrl(productRepository.findById(2L).get().getImageURl())
                .build();

        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(orderItem2.getQuantity())));
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(order.getOrderItems().size());

        Address address = Address.builder()
                .street("Alambagh")
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
    void fetchOrder(){
        Optional<Order> orderOptional = orderRepository.findById(1L);
        orderOptional.ifPresent(order -> {
            System.out.println(order.getStatus());
            order.getOrderItems().forEach(System.out::println);
        });
    }

    @Test
    void deleteOrder(){
        orderRepository.deleteById(1L);
    }
}
