package com.example.capstone1.Service;
import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {



    ArrayList<Merchant> merchants = new ArrayList<>();

// 1. Get
public ArrayList<Merchant> getMerchants() {
    return merchants;
}

//2.Add
    public void AddMerchant(Merchant merchant) {
    merchants.add(merchant);
    }

//3.Update
    public boolean UpdateMerchant(String MerchantId,Merchant merchant) {
    for (int i = 0; i < merchants.size(); i++) {
        if (merchants.get(i).getMerchantId().equals(MerchantId)) {
            merchants.set(i,merchant);
            return true;
        }
    }
    return false;
    }

//4.Delete
    public boolean DeleteMerchant(String MerchantId) {
    for (int i = 0; i < merchants.size(); i++) {
        if (merchants.get(i).getMerchantId().equals(MerchantId)) {
            merchants.remove(i);
            return true;
        }
    }
    return false;
    }





}
