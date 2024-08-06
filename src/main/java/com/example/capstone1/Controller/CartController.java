package com.example.capstone1.Controller;


import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Cart;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/get/cart")
    public ResponseEntity getCart(){
        return ResponseEntity.status(200).body(cartService.getCart());
    }

    @PostMapping("/add/ToCart")
    public ResponseEntity addToCart(@Valid @RequestBody Cart cart, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        cartService.addToCart(cart);
        return ResponseEntity.status(200).body(new ApiResponse("Adding to cart Successfully"));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateCart(@PathVariable String productId ,@Valid @RequestBody Cart cart, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = cartService.UpdateCart(productId,cart);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Cart updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Cart Not Found"));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteCart(@PathVariable String productId){
        boolean isDeleted = cartService.DeleteCart(productId);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Cart Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Cart Not Found"));

    }


    @GetMapping("/display/{cartId}")
    public ResponseEntity DisplayCart (@PathVariable String cartId){
         Cart cart = cartService.displayCart(cartId);
       if(cart==null){
           return ResponseEntity.status(400).body(new ApiResponse("Cart is Not Found"));

        }
       return ResponseEntity.status(200).body(cart);
    }

}
