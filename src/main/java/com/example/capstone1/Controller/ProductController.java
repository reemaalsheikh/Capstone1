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

    @PutMapping("/discount/{userId}")
    public ResponseEntity discount(@PathVariable String userId) {
       if (productService.discount(userId).equalsIgnoreCase("Discount addedd successful")){
           return ResponseEntity.status(200).body(new ApiResponse("Discount added successfully"));
        }
       return ResponseEntity.status(400).body(productService.discount(userId));
    }


    @GetMapping("/topseller")
    public ResponseEntity DisplayTopSeller(){
        if(productService.topSeller().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Top Sellers products not found!"));
        }
        return ResponseEntity.status(200).body(productService.topSeller());
    }

    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity AddReview (@PathVariable String userId ,@PathVariable String productId,@RequestBody String review){
        if (productService.Review(userId ,productId, review).equalsIgnoreCase("Review added successfully")){
            return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
        }
        return ResponseEntity.status(400).body(productService.Review(userId ,productId, review));
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
        int r1 = productService.buyProduct(userId,productId,merchantId);
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





}
