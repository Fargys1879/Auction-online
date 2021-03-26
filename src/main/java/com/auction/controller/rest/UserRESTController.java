package com.auction.controller.rest;

import com.auction.entity.User;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserRESTController {
    @Autowired
    UserService userService;

    @PutMapping("/{id}/save")
    public Response updateUser(@PathVariable(value = "id") Long id,
                                   @Valid @RequestBody User user) throws ExceptionRest, MethodArgumentNotValidException {
        Response response;
        User userFromDb;
        userFromDb = userService.getUserById(id);
        if (userFromDb == null) { throw new ExceptionRest("Don't exist User with same id");}
        userFromDb.setName(user.getName());
        userFromDb.setAdress(user.getAdress());
        userFromDb.setLogin(user.getLogin());
        boolean update = userService.updateUser(userFromDb);
        if (!update) { throw new ExceptionRest("Exception in userService.updateUser()");}
        response = new Response(HttpStatus.OK,"User Updated success",userFromDb);
        return response;
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteUser(@PathVariable(value = "id") Long id) throws ExceptionRest {
        Response response;
        User userToRemove = userService.removeUserById(id);
        if (userToRemove == null) { throw new ExceptionRest("User already Deleted");}
        response = new Response(HttpStatus.OK,"User Deleted success",id);
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
