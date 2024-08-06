package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    //get
    @GetMapping("/get")
    public ResponseEntity getMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

  //add
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.AddMerchant(merchant);
        return ResponseEntity.status(200).body( new ApiResponse("Merchant is added successfully"));

    }
   //3.update
   @PutMapping("/update/{merchantId}")
  public ResponseEntity UpdateMerchant(@PathVariable String merchantId ,@Valid @RequestBody Merchant merchant , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       boolean updated = merchantService.UpdateMerchant(merchantId,merchant);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant is updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
   }

   @DeleteMapping("/delete/{merchantId}")
    public ResponseEntity deleteMerchant(@PathVariable String merchantId) {
        boolean deleted = merchantService.DeleteMerchant(merchantId);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant is deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
   }

}
