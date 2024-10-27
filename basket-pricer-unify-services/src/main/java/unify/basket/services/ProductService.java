package unify.basket.services;

import unify.basket.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product add(Product p);

}
