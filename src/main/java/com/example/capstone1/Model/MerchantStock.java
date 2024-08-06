package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

//Create MerchantStock Class:
public class MerchantStock {

//• id (must not be empty).
@NotEmpty(message = "Merchant Stock Id should not be Empty!")
private String  MerchantStockId;


//• productid (must not be empty).
@NotEmpty(message = "Product Id should not be Empty!")
private String productId;

//• merchantid (must not be empty).
@NotEmpty(message = "Merchant Id should not be Empty!")
private String  MerchantId;

//• stock (must not be empty, have to be more than 10 at start).
@NotNull(message = "Stock should not be Null!")
@Min(value=10)
private int stock;
}
