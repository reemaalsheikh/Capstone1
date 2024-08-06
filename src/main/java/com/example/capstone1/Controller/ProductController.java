package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

// 1. Get products
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

//2.Add products
    @PostMapping("/add")
public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors){
        if(errors.hasErrors()){
       String message = errors.getFieldError().getDefaultMessage();
       return ResponseEntity.status(400).body(message);
        }
        productService.AddProducts(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
}
//3.Update products
@PutMapping("update/{id}")
public ResponseEntity updateProduct(@PathVariable String id ,@Valid @RequestBody Product product , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = productService.updateProduct(id,product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Product is updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
}

//4.Delete products
 @DeleteMapping("/delete/{id}")
public ResponseEntity deleteProduct(@PathVariable String id){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product is deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
}

@GetMapping("/filter/{minPrice}/{maxPrice}")
public ResponseEntity filterProduct(@PathVariable int minPrice,@PathVariable int maxPrice){
        ArrayList<Product> filter = productService.filter(minPrice,maxPrice);
        if(filter.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Products not found!"));
        }
        return ResponseEntity.status(200).body(filter);
}






}
