package com.sullung2yo.usermanagementapplication.web.servlet;

import com.sullung2yo.usermanagementapplication.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="userFormServlet", urlPatterns = "/servlet/users/new-form")
public class UserFormServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        //Servlet이 불편한 점 -> HTML 직접 자바에다가 다 쳐야함
        writer.write("""
                <!DOCTYPE html>
                <html>
                <head>
                <meta charset="UTF-8">
                <title>Title</title>
                </head>
                <body>
                <form action="/servlet/users/save" method="post">
                   username: <input type="text" name="username" />
                   age: <input type="text" name="age" />
                   <button type="submit">전송</button>
                </form>
                </body>
                </html>
                """);
    }
}
