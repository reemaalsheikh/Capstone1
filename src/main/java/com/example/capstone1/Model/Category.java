package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Create Category Class:
public class Category {

//• id (must not be empty).
@NotEmpty(message = "Category Id should not be Empty!")
private String categoryID;

//• name (must not be empty, have to be more than 3 length long).
@NotEmpty(message = "Category Name should not be Empty!")
@Size(min = 4 , message = "Category name have to be more than 3 length long!")
private String categoryName;

}
