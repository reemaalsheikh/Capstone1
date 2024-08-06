package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

// Create Product Class:
public class Product {

//• id (must not be empty).
    @NotEmpty(message = "Product Id should not be Empty!")
    private String productId;

//• name (must not be empty, have to be more than 3 length long).
    @NotEmpty(message = "Product Name should not be Empty!")
    @Size(min= 4 , message = "Product name have to be more than 3 length long.")
    private String productName;

//• price (must not be empty, must be positive number).
    @NotNull(message = "Product Price should not be Null!")
    @Positive
    private double productPrice;

//• categoryID (must not be empty).
@NotEmpty(message = "Category Id should not be Empty!")
private String categoryID;



}
