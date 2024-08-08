package com.example.capstone1.Service;


import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final UserService userService;
    private final MerchantStockService merchantStockService;

//Create endpoint for getting and adding and deleting updating a Product.

    ArrayList<Product> products = new ArrayList<>();



    // 1. Get products
    public ArrayList<Product> getProducts() {
        return products;
    }

    //2.Add products
    public void AddProducts(Product product) {
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (categoryService.categories.get(i).getCategoryID().equalsIgnoreCase(product.getCategoryID())) {
                products.add(product);
            }
        }


    }


    //3.Update products
    public boolean updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }


    //4.Delete products
    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Product> filter (int minPrice , int maxPrice){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductPrice() >= minPrice && products.get(i).getProductPrice() <= maxPrice){
                filteredProducts.add(products.get(i));
            }
        }
        return filteredProducts;

    }

    public Product getProductbyId(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                return products.get(i);
            }
        }
        return null;
    }



    public String discount(String userId){
        if(userService.getUserById(userId) == null || userService.getUserById(userId).getRole().equals("Customer")){
            return "User not allowed to make discount";
        }
        for (Product product : products) {
            if (product.getProductPrice() >= 900) {
                product.setProductPrice((product.getProductPrice()) - (product.getProductPrice() * 0.15));
                return "Discount added successful";
            }
        }
        return "Discount not added";
    }


        public ArrayList<Product> topSeller (){
        ArrayList<Product> topSeller = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTopSeller() >= 4) {
                topSeller.add(products.get(i));
            }
        }
        return topSeller;
        }




        public String Review (String userId , String productId, String review){
        if(userService.getUserById(userId) == null ){
            return "User not found";
        }
        if(userService.getUserById(userId).getListOfProducts().isEmpty()){
            return "Your list is empty";
        }
        for (Product product : userService.getUserById(userId).getListOfProducts()) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                product.setReview(review);
                return "Review added successfully";
            }
        }
        return "Review not added";

        }



// 12.Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.



    public int buyProduct(String userId, String productId, String merchantId) {

        for (int i = 0; i < userService.getUsers().size(); i++) {
            //• check if all the given ids are valid or not
            if ( userService.getUsers().get(i).getUserId().equalsIgnoreCase(userId)) { //user done
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
                        if ( userService.getUsers().get(i).getBalance() >= getProducts().get(i).getProductPrice()) {
                          userService.getUsers().get(i).setBalance( userService.getUsers().get(i).getBalance() - getProducts().get(i).getProductPrice());
                            Product product = getProductbyId(productId);
                            product.setProductPrice(product.getTopSeller()+1);


                            ArrayList<Product> listbuy =new ArrayList<>();
                            listbuy.add(product);
                            userService.getUsers().get(i).setListOfProducts(listbuy);
                            userService.UpdateUser(userId,userService.getUsers().get(i));

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




















