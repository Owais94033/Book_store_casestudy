package com.BookManager.demo.Controller;

import com.BookManager.demo.model.Orderr;
import com.BookManager.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping //
    public List<Orderr> getAllOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orderr> getOrderrById(@PathVariable Long id) {
        Optional<Orderr> orderOptional = orderService.getOrderById(id);
        return orderOptional
                .map(x -> new ResponseEntity<>(x , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Orderr createCustomer(@RequestBody Orderr order) {
        return orderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orderr> updateOrder(@PathVariable Long id,
                                                    @RequestBody Orderr order) {
        Optional<Orderr> orderOptional = orderService.getOrderById(id);
        return orderOptional
                .map(a -> new ResponseEntity<>(a , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Orderr> orderOptional = orderService.getOrderById(id);
        if(orderOptional.isPresent()){
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
