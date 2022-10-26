package com.securitytutorial.securitytutorial.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitytutorial.securitytutorial.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
