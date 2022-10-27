package com.cstftutotial.csrfexample.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping(value = "/add")
    public String add(@RequestBody String name) {

        logger.info("Adding product " + name);
        return "main.html";
    }

}
