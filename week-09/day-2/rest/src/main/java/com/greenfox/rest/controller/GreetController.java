package com.greenfox.rest.controller;

import com.greenfox.rest.model.Greeter;
import com.greenfox.rest.model.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    Greeter greeter;

    @Autowired
    ErrorMessage errorMessage;

    @GetMapping("/greeter")
    public Greeter greeter(@RequestParam(value="name") String name , @RequestParam(value="title") String title ){
        greeter.setName(name);
        greeter.setTitle(title);
        return greeter;
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage errorMessage(MissingServletRequestParameterException e){
        String parameterName=e.getParameterName();
        errorMessage.setError("Please provide a "+ parameterName + "!");
        return errorMessage;
    }

}
