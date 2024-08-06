package com.example.capstone1.Service;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

   // private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;


    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        users.add(user);
    }

    public boolean UpdateUser(String userId, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean DeleteUser(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }


// 12.Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.


    public int buyProduct(String userId, String productId, String merchantId) {

        for (int i = 0; i < users.size(); i++) {
            //• check if all the given ids are valid or not
            if (users.get(i).getUserId().equalsIgnoreCase(userId)) { //user done
                for (MerchantStock m : merchantStockService.getMerchantStock()) {

                 //• check if the merchant has the product in stock or return bad request.
                    if (m.getProductId().equals(productId) && m.getMerchantId().equals(merchantId)) { //product+merchant
                        if (m.getStock() > 0) {
                        //• reduce the stock from the MerchantStock.
                            m.setStock(m.getStock() - 1);
                        } else {
                            return 1;//The product is not in stock
                        }

                        //• deducted the price of the product from the user balance.
                        if (users.get(i).getBalance() >= productService.getProducts().get(i).getProductPrice()) {
                            users.get(i).setBalance(users.get(i).getBalance() - productService.getProducts().get(i).getProductPrice());
                        } else {
                            return 2; //Error! balance is less than the product price
                        }


                    }

                }

                return 3; //success
            }


        }
        return 0; //not found
    }




}









