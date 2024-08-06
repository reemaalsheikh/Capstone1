package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;

//Create endpoint for getting and adding and deleting updating a Product.

    ArrayList<Product> products = new ArrayList<>();

    // 1. Get products
    public ArrayList<Product> getProducts() {
        return products;
    }

    //2.Add products
    public void AddProducts(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (categoryService.categories.get(i).getCategoryID().equalsIgnoreCase(product.getCategoryID())) {
                products.add(product);
            }
        }

    }

    //3.Update products
    public boolean updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }


    //4.Delete products
    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Product> filter (int minPrice , int maxPrice){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductPrice() >= minPrice && products.get(i).getProductPrice() <= maxPrice){
                filteredProducts.add(products.get(i));
            }
        }
        return filteredProducts;

    }













}




















