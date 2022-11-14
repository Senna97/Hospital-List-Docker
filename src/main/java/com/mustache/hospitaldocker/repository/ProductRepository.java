package com.mustache.hospitaldocker.repository;

import com.mustache.hospitaldocker.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
