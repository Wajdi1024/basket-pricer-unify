package unify.basket.services;

import unify.basket.entities.*;
import unify.basket.repositories.ProductRepository;
import unify.basket.repositories.SpecialOfferRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository = new ProductRepository();
    private SpecialOfferRepository specialOfferRepository = new SpecialOfferRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product add(Product p) {
        return productRepository.insertOne(p);
    }
}
