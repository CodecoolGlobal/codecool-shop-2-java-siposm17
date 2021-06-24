package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart/add"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("name") == null) {
            User.getInstance().setCartError(true);
            resp.sendRedirect(req.getHeader("referer"));
        }

        ProductService productService = new ProductService();
        User newUser = User.getInstance();
        String productIdParam = req.getParameter("productid");
        int productId = (productIdParam != null) ? Integer.parseInt(productIdParam) : 0;
        newUser.add(productService.getProduct(productId));

        resp.sendRedirect(req.getHeader("referer"));
    }
}
