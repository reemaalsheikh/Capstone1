package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Cart {

    @NotEmpty(message = "Cart Id should not be Empty!")
    private String cartId;

    @NotEmpty(message = "Product Id should not be Empty!")
    private String productName;

    @NotEmpty(message = "Product Id should not be Empty!")
    private String productId;

    @NotNull(message = "Quantity should not be Null!")
    private int quantity;


    @NotNull(message = "Total price should not be Empty!")
    private double totalPrice;

//    @NotEmpty(message="Payment method should not be Empty!")
//    private String paymentMethod;


}
