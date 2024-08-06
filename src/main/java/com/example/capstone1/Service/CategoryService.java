package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    //Create endpoint for getting and adding and deleting updating a Product.

    ArrayList<Category> categories = new ArrayList<>();


    // 1. Get
    public ArrayList<Category> getCategories() {
        return categories;
    }

    //2.Add
    public void AddCategory(Category category) {
        categories.add(category);
    }
    //3.Update
  public boolean UpdateCategory(String id,Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getCategoryID().equalsIgnoreCase(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
  }

    //4.Delete
public boolean DeleteCategory(String id) {
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getCategoryID().equalsIgnoreCase(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;
}
}
