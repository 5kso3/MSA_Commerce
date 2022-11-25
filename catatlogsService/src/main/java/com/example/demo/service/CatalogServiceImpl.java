package com.example.demo.service;

import com.example.demo.jpa.CatalogEntity;
import com.example.demo.jpa.CatalogRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;


@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{

    CatalogRepository repository;

    Environment env;

    @Autowired
    public CatalogServiceImpl(CatalogRepository repository, Environment env){
        this.repository = repository;
        this.env = env;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return repository.findAll();
    }
}
