package com.sullung2yo.servletpractice.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="RequestFormDataServlet", urlPatterns = "/request-form-data")
public class RequestFormDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName +
                        "=" + req.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        //http://localhost:8080/request-param?username=hello&age=20
        System.out.println("[단일 파라미터 조회]");
        String username = req.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);
        String age = req.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();

        //http://localhost:8080/request-param?username=hello&username=kim&age=20
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);
        }

        resp.getWriter().write("ok");
    }
}
