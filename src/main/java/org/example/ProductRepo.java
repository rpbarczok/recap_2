package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductRepo {
    Map<Integer, Product> products = new HashMap<Integer, Product>();
    int productCount = 0;
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

    public Product getProductById(int productId) {
        return products.get(productId);
    }


}

