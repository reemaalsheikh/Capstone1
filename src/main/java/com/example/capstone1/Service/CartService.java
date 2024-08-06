package com.example.capstone1.Service;

import com.example.capstone1.Model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    ArrayList <Cart> carts = new ArrayList <>();

    public ArrayList <Cart> getCart() {
        return carts;
    }

    //Adding to cart
    public void addToCart(Cart cart) {
                carts.add(cart);
    }

    public boolean UpdateCart(String productId,Cart cart) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProductId().equals(productId)) {
                carts.set(i, cart);
                return true;
            }
        }
        return false;

    }

    public boolean DeleteCart(String productId) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProductId().equals(productId)) {
                carts.remove(i);
                return true;
            }
        }
        return false;
    }


 //Display the cart has been added
    public Cart displayCart (String cartId){

        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCartId().equals(cartId)) {
                return carts.get(i);
            }
        }
        return null;

    }




}
