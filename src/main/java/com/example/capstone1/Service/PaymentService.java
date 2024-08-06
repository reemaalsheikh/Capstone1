package com.example.capstone1.Service;

import com.example.capstone1.Model.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PaymentService {

    ArrayList<Payment> payments = new ArrayList<>();

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public boolean updatePayment(Payment payment ,String paymentId) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equals(paymentId)) {
                payments.set(i, payment);
                return true;
            }
        }
        return false;

    }

    public boolean deletePayment(String paymentId) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equals(paymentId)) {
                payments.remove(i);
                return true;
            }
        }
        return false;
    }


    public Payment ChangeAmount (String paymentId) {
        for (int i = 0; i < payments.size(); i++) {
         if (payments.get(i).getPaymentId().equals(paymentId)) {
          if(payments.get(i).getPaymentMethod().equalsIgnoreCase("Cash")){
            payments.get(i).setPaymentAmount(payments.get(i).getPaymentAmount()+(payments.get(i).getPaymentAmount()*0.25));
                }
                if(payments.get(i).getPaymentMethod().equalsIgnoreCase("CreditCard")){
                    payments.get(i).setPaymentAmount((payments.get(i).getPaymentAmount())-(payments.get(i).getPaymentAmount()*0.15));
                }

            }
            return payments.get(i);
        }


        return null;
    }








}
