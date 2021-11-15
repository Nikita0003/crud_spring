package com.testtask.crud.repo;

import com.testtask.crud.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
