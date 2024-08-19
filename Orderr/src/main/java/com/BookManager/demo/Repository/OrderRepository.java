package com.BookManager.demo.Repository;


import com.BookManager.demo.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderr, Long> {
}
