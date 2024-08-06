package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/get")
    public ResponseEntity getUser (){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse( "User added successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId ,@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean ifUpdated = userService.UpdateUser(userId,user);
        if(ifUpdated){
            return ResponseEntity.status(200).body(new ApiResponse( "User is updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "User not found"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        boolean ifDeleted = userService.DeleteUser(userId);
        if(ifDeleted){
            return ResponseEntity.status(200).body(new ApiResponse( "User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "User not found"));
    }


//12. Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.

    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProducts (@PathVariable String userId , @PathVariable String productId , @PathVariable String merchantId){
        int r1 = userService.buyProduct(userId,productId,merchantId);
     if(r1 == 0){
         return ResponseEntity.status(400).body(new ApiResponse( "Product not found"));
     }
     if (r1 == 1){
         return ResponseEntity.status(400).body(new ApiResponse( "The product is not in stock"));
     }
     if (r1 == 2) {
         return ResponseEntity.status(400).body(new ApiResponse("Error! balance is less than the product price."));
     }

         return ResponseEntity.status(200).body(new ApiResponse("Successfully bought the product"));


    }

    @PutMapping("checkout/{userId}")
    public ResponseEntity Checkout (String userId){
        ArrayList<User> checkOut = userService.checkout(userId);
        if(checkOut.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Checkout failed"));
        }
        return ResponseEntity.status(200).body(Checkout(userId));
    }






}
