package com.efuture.demo.repository;

import com.efuture.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("FROM Product p " +
            "WHERE p.price >= 500 ")
    List<Product> findPremiumProducts();


    @Query("FROM Product p " +
            "WHERE p.productCategory.name = :name ")
    List<Product> findByCategoryName(String name);
}
