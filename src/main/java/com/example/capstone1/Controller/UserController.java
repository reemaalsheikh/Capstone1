package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/get")
    public ResponseEntity getUser (){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse( "User added successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId ,@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean ifUpdated = userService.UpdateUser(userId,user);
        if(ifUpdated){
            return ResponseEntity.status(200).body(new ApiResponse( "User is updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "User not found"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        boolean ifDeleted = userService.DeleteUser(userId);
        if(ifDeleted){
            return ResponseEntity.status(200).body(new ApiResponse( "User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse( "User not found"));
    }









}
