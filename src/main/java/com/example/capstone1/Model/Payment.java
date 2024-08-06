package com.example.capstone1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {

    @NotEmpty(message="Payment Id should not be Empty!")
    private String paymentId;

    @NotEmpty(message="User Id method should not be Empty!")
    private String userId;

    @NotEmpty(message="Order Id method should not be Empty!")
    private String orderId;

    @NotNull(message="Payment date should not be Empty!")
    @JsonFormat(pattern= "yyyy-MM-dd")
    private String paymentDate;


    @NotNull(message="Payment Amount should not be Empty!")
    @Positive
    private double paymentAmount;


    @NotEmpty(message="Payment method should not be Empty!")
    @Pattern(regexp = "^(Cash|CreditCard)$" , message = "Two valid inputs only Cash, and CreditCard!")
    private String paymentMethod;
}
