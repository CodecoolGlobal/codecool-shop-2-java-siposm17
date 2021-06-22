package com.codecool.shop.service;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    public ProductDaoMem productDao;
    public ProductCategoryDaoMem productCategoryDao;
    public SupplierDao productSupplierDao;
    public ProductService ps;

    @Before
    public void setUp() {
        productDao = Mockito.mock(ProductDaoMem.class);
        productCategoryDao = Mockito.mock(ProductCategoryDaoMem.class);
        productSupplierDao = Mockito.mock(SupplierDaoMem.class);
        ps = new ProductService(productDao, productCategoryDao, productSupplierDao);
    }

    @Test
    public void getProductCategory() {
        ProductCategory expected = new ProductCategory("tablets", "tech", "tablets");
        when(productCategoryDao.find(5)).thenReturn(expected);
        assertEquals(expected, ps.getProductCategory(5));
    }

//    @Test
//    void getProductsForCategory() {
//        ProductCategory pc = new ProductCategory("tablets", "tech", "tablets");
//        when(productCategoryDao.find(5)).thenReturn(pc);
//    }
//
//    @Test
//    void getAllCategories() {
//    }
//
//    @Test
//    void getSupplierName() {
//    }
//
//    @Test
//    void getProductsForSupplier() {
//    }
//
//    @Test
//    void getAllSuppliers() {
//    }
//
//    @Test
//    void getProduct() {
//    }
}