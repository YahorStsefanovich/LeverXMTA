package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.model.Product;
import com.leverx.leverxspringproj.service.ODataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ODataController {

    @Autowired
    private ODataService oDataService;

    @GetMapping(value="/odata/{destinationName}")
    public List<Product> getAllProducts(@PathVariable String destinationName) {
        return 	oDataService.getAllProducts(destinationName);
    }
}
