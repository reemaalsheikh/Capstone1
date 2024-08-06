package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Create Merchant Class:
public class Merchant {

//• id (must not be empty).
@NotEmpty(message = "Merchant Id should not be Empty!")
private String  MerchantId;

//• name (must not be empty, have to be more than 3 length long).
@NotEmpty(message = "Merchant Name should not be Empty!")
@Size(min = 4, message = "Merchant Name have to be more than 3 length long ")
 private String  MerchantName;

}
