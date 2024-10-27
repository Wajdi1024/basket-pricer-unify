package unify.basket.repositories;

import org.apache.commons.lang3.StringUtils;
import unify.basket.entities.Product;
import unify.basket.exceptions.DuplicateKeyException;
import unify.basket.exceptions.EntityNotFound;
import unify.basket.exceptions.InvalidKeyException;

import java.util.*;

public class ProductRepository implements  AbstractRepository<Product, String> {

    private static final  Set<Product> products = new HashSet<>();
    @Override
    public Optional<Product> findById(String s) {
        return products.stream().filter(p -> s.equals(p.getName())).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Product insertOne(Product product) {
        if (StringUtils.isEmpty(product.getName())) {
            throw new InvalidKeyException("Invalid product name");
        }
        Optional<Product> optionalProduct = findById(product.getName());
        if(optionalProduct.isPresent()) {
            throw new DuplicateKeyException("product with the same name exists");
        }
        products.add(product);
        return product;
    }

    @Override
    public Product updateOne(Product product) {
        Optional<Product> optionalProduct = findById(product.getName());
        if(optionalProduct.isEmpty()) {
            throw new EntityNotFound("Product not found");
        }
        Product newProduct = optionalProduct.get();
        newProduct.setPrices(product.getPrices());
        newProduct.setSpecialOffers(product.getSpecialOffers());
        return newProduct;
    }

    @Override
    public boolean deleteById(String s) {
        Optional<Product> optionalProduct = products.stream().filter(p -> p.getName().equals(s)).findFirst();
        return optionalProduct.filter(this::delete).isPresent();
    }

    @Override
    public boolean delete(Product product) {
        return products.remove(product);
    }


}
