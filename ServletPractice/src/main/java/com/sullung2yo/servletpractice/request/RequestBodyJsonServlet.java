package com.sullung2yo.servletpractice.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sullung2yo.servletpractice.domain.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

import java.io.IOException;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); // JSON -> Object, Object -> JSON
    // 즉, 직렬화, 역직렬화 수행하는 객체라고 생각하면 된다. Django의 Serializer 역할

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // Deserialize
        System.out.println("helloData.username = " + helloData.getUsername());System.out.println("helloData.age = " + helloData.getAge());

        resp.getWriter().write("ok");
    }
}
