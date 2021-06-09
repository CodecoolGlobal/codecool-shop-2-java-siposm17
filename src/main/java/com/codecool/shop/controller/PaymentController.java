package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.User;
import com.codecool.shop.model.order.BillingAddress;
import com.codecool.shop.model.order.Order;
import com.codecool.shop.model.order.ShippingAddress;
import com.codecool.shop.serialization.FileWriterLocal;
import com.codecool.shop.util.EmailSender;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("userName", User.getInstance().getName());
        context.setVariable("cartSum", User.getInstance().cartSum());
        context.setVariable("totalPrice", User.getInstance().cartSumPrice());
        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String paymentType = req.getParameter("payment-type");
        String isError = req.getParameter("error-checkbox") != null ? "true" : "false";
        context.setVariable("isError", isError);
        context.setVariable("orderDetails", User.getInstance().getNewOrder());
        context.setVariable("total", User.getInstance().cartSumPrice());

        EmailSender.sendEmail(User.getInstance().getNewOrder());
        FileWriterLocal.serializeObj(User.getInstance().getNewOrder());

        engine.process("product/confirmation-page.html", context, resp.getWriter());
    }
}
