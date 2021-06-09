package com.codecool.shop.serialization;

import com.codecool.shop.model.order.Order;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriterLocal {

    public static void serializeObj(Order order) {
        String serializedMovie = new Gson().toJson(order);
        FileWriter file = null;
        try {
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            String fileName = dateFormat.format(date);
            file = new FileWriter("src/main/webapp/static/orders/" + fileName + ".json");
            file.write(serializedMovie);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
