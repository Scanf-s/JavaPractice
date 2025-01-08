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
import java.util.List;

@WebServlet(name="userListServlet", urlPatterns = "/servlet/users")
public class UserListServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userRepository.findAll();

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<html>");
        printWriter.write("<head>");
        printWriter.write("<meta charset=\"UTF-8\">");
        printWriter.write("<title>Title</title>");
        printWriter.write("</head>");
        printWriter.write("<body>");
        printWriter.write("<a href=\"/index.html\">Main page</a>");
        printWriter.write("<table>");
        printWriter.write("<thead>");
        printWriter.write("<th>id</th>");
        printWriter.write("<th>username</th>");
        printWriter.write("<th>age</th>");
        printWriter.write("</thead>");
        printWriter.write("<tbody>");
        printWriter.write("<tr>");
/*
printWriter.write("<td>1</td>");
printWriter.write("<td>userA</td>");printWriter.write("
printWriter.write("
<td>10</td>");
</tr>");
*/
        for (User user : users) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + user.getId() + "</td>");
            printWriter.write("<td>" + user.getName() + "</td>");
            printWriter.write("<td>" + user.getAge() + "</td>");
            printWriter.write(" </tr>");
        }
        printWriter.write(" </tbody>");
        printWriter.write("</table>");
        printWriter.write("</body>");
        printWriter.write("</html>");
    }
}
