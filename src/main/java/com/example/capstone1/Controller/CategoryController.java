package com.example.capstone1.Controller;


import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

// 1. Get
    @GetMapping("/get")
    public ResponseEntity getCategories() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

//2.Add
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.AddCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
    }
//3.Update
    @PutMapping("/update/{CategoryId}")
    public ResponseEntity updateCategory(@PathVariable String CategoryId,@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
   boolean isUpdated = categoryService.UpdateCategory(CategoryId,category);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Category is updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found!"));

    }

//4.Delete
    @DeleteMapping("/delete/{CategoryId}")
    public ResponseEntity deleteCategory(@PathVariable String CategoryId) {
        boolean isDeleted = categoryService.DeleteCategory(CategoryId);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Category is deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found!"));
    }

}
