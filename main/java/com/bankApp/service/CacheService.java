package com.bankApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManagerFactory;

@Service
public class CacheService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void clearCaches() {
        entityManagerFactory.getCache().evictAll();
    }
}
