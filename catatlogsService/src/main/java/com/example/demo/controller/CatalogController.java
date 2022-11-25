package com.example.demo.controller;

import com.example.demo.jpa.CatalogEntity;
import com.example.demo.service.CatalogService;
import com.example.demo.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("Is Working");
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(v ->{result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
