package com.BookManager.demo.service;


import com.BookManager.demo.Repository.OrderRepository;
import com.BookManager.demo.model.Orderr;
import com.BookManager.demo.service.client.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private BookRepository bookRepository;
//
    @Autowired
    private BookClient bookClient;


////
//    public Orderr placeOrder(Orderr order){
//        Book book = bookClient.getBookById(order.getBookId());
//
//        if(book != null && book.getStock() > order.getQuantity()){
//            book.setStock(book.getStock() - order.getQuantity());
//            return orderRepository.save(order);
//        }
//        throw new RuntimeException("Can't place the order. Try choosing a minimum quantity.");
//    }

    public List<Orderr> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Orderr> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Orderr saveOrder(Orderr order) {
        return orderRepository.save(order);
    }

    public Orderr updateOrder(Long id, Orderr order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
