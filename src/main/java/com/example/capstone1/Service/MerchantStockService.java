package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class MerchantStockService {


    ArrayList <MerchantStock> merchantStocks = new ArrayList<>();

    // 1. Get
    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }

    //2.Add
    public void AddMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    //3.Update
    public boolean UpdateMerchantStock(String MerchantStockId,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantStockId().equals(MerchantStockId)) {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    //4.Delete
    public boolean DeleteMerchantStock(String MerchantStockId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantStockId().equals(MerchantStockId)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;

    }

//11.Create endpoint where user can add more stocks of product to a merchant Stock
//• this endpoint should accept a product id and merchant id and the amount of additional stock.

    public boolean addStock (String productId, String MerchantId, String MerchantStockId, int stock ) {
       for (int i = 0; i < merchantStocks.size(); i++) {
           if (merchantStocks.get(i).getProductId().equals(productId)) {
               for (int j = 0; j < merchantStocks.size(); j++) {
                   if (merchantStocks.get(j).getMerchantId().equals(MerchantId)) {

                       for (int ms = 0; ms < merchantStocks.size(); ms++) {
                           if (merchantStocks.get(ms).getMerchantStockId().equals(MerchantStockId)) {
                               if(merchantStocks.get(ms).getStock() > 0) {
                                   merchantStocks.get(ms).setStock(merchantStocks.get(ms).getStock() + stock);
                                   return true;
                               }
                           }
                       }
                   }
               }
           }
       }

       return false;
    }





}
