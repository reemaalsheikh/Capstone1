package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Payment;
import com.example.capstone1.Service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/get")
    public ResponseEntity getpayment(){
        return ResponseEntity.status(200).body(paymentService.getPayments());
    }

    @PostMapping("/add")
    public ResponseEntity addpayment(@Valid @RequestBody Payment payment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        paymentService.addPayment(payment);
        return ResponseEntity.status(201).body(new ApiResponse("Payment added successfully"));
    }

    @PutMapping("/update/{paymentId}")
    public ResponseEntity updatepayment(@PathVariable String paymentId,@Valid @RequestBody Payment payment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = paymentService.updatePayment(payment,paymentId);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Payment updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Payment not found"));
    }

    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity deletepayment(@PathVariable String paymentId){
        boolean isDeleted = paymentService.deletePayment(paymentId);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Payment deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Payment not found"));
    }


    @PutMapping("method/{paymentId}")
    public ResponseEntity ChangeAmount (@PathVariable String paymentId){
        Payment Amount = paymentService.ChangeAmount(paymentId);

        if(Amount == null){
            return ResponseEntity.status(400).body(new ApiResponse("Payment not found"));
        }
        return ResponseEntity.status(201).body(Amount);


    }








}
