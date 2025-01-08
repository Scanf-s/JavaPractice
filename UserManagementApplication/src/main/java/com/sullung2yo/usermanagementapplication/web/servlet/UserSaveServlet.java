package com.sullung2yo.usermanagementapplication.web.servlet;

import com.sullung2yo.usermanagementapplication.domain.User;
import com.sullung2yo.usermanagementapplication.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="userSaveServlet", urlPatterns = "/servlet/users/save")
public class UserSaveServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Form data parse
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        User newUser = new User(username, age);
        userRepository.save(newUser);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "   <li>id="+newUser.getId()+"</li>\n" +
                "   <li>username="+newUser.getName()+"</li>\n" +
                "   <li>age="+newUser.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
