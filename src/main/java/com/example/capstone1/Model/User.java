package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

//Create User Class:
public class User {

//• id (must not be empty).
@NotEmpty(message = " user Id should not be Empty!")
    private String userId;

//• username (must not be empty, have to be more than 5 length long).
@NotEmpty(message = "User name should not be Empty!")
@Size(min=6, message = "User name have to be more than 5 length long!")
    private String userName;

//• password (must not be empty, have to be more than 6 length long, must have characters and digits).
  @NotEmpty(message = "Password should not be Empty!")
//@Size(min=6)
@Pattern(regexp = "^((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,})$" , message = "Password must have to be more than 6 length long,characters and digits")
    private String Password;

//• email (must not be empty, must be valid email).
@NotEmpty(message = "Email should not be Empty!")
@Email
    private String email;

//• role (must not be empty, have to be in ( “Admin”,”Customer”)).
@NotEmpty(message = "Role should not be Empty!")
@Pattern(regexp = "^(Admin|Customer)$", message = "Role inputs has to be: Admin or Customer ONLY!")
    private String role;

//• balance (must not be empty, have to be positive).

@NotNull(message = "Balance should not be Null!")
@Positive()
    private double balance;


//Extra..
    @NotEmpty(message="Payment method should not be Empty!")
    @Pattern(regexp = "^(Cash|CreditCard)$" , message = "Two valid inputs only Cash, and CreditCard!")
    private String paymentMethod;

    @NotNull(message = "Total price should not be Empty!")
    private double totalPrice;


}
