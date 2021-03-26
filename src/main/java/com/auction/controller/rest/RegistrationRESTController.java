package com.auction.controller.rest;

import com.auction.entity.User;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationRESTController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public Response addUser(@Valid @RequestBody User user) throws ExceptionRest{
        Response response;
        boolean userCheck = userService.checkUserExist(user);
        if (userCheck) throw new ExceptionRest("User Already Exist");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean success = userService.addNewUser(user);
        if (!success) throw new ExceptionRest("Can't add user");
        response = new Response(HttpStatus.OK,"User register success",user);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("message","BadFormData");
        });
        return errors;
    }
}
