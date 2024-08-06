package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;


    //1.get
    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    //2.add
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.AddMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
    }

    //3.update
    @PutMapping("/update/{MerchantStockId}")
   public ResponseEntity updateMerchantStock(@PathVariable String MerchantStockId , @Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean ifUpdated= merchantStockService.UpdateMerchantStock(MerchantStockId,merchantStock);
        if (ifUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Found"));
   }

   //4.delete
    @DeleteMapping("/delete/{MerchantStockId}")
   public ResponseEntity deleteMerchantStock(@PathVariable String MerchantStockId ) {
       boolean ifDeleted = merchantStockService.DeleteMerchantStock(MerchantStockId);
       if (ifDeleted) {
           return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted Successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Found"));
   }


//11.Create endpoint where user can add more stocks of product to a merchant Stock
//• this endpoint should accept a product id and merchant id and the amount of additional stock.

    @PutMapping("/stock/{productId}/{MerchantId}/{MerchantStockId}/{stock}")
    public ResponseEntity addStock(@PathVariable String productId,@PathVariable String MerchantId,@PathVariable String MerchantStockId,@PathVariable int stock ) {
       boolean ifUpdated = merchantStockService.addStock(productId,MerchantId,MerchantStockId,stock);
       if (ifUpdated) {
           return ResponseEntity.status(200).body(new ApiResponse("Stock Added Successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("Stock Not Found"));

    }


    @PutMapping("/discount/{userId}/{productId}")
    public ResponseEntity discount(@PathVariable String userId,@PathVariable String productId) {
        boolean discount = merchantStockService.discount(userId,productId);
        if (discount) {
            return ResponseEntity.status(200).body(new ApiResponse("Discount Added Successfully!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Discount Not Found!"));
    }





}
