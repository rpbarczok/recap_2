package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepo {
    private Map<Integer, Product> products = new HashMap<Integer, Product>();
    private int productCount = 0;

    public ProductRepo() {
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Product addProduct(String name, BigDecimal price) {
        productCount += 1;
        Product product = new Product(productCount, name, price);
        products.put(productCount, product);
        return product;
    }

    public Optional<Product> getProductById(int productId) {
        return Optional.ofNullable(products.get(productId));
    }


}

