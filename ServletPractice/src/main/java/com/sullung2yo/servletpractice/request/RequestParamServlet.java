package com.sullung2yo.servletpractice.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="RequestBodyServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET 쿼리 파라미터 정보 가져오기
        // String username = req.getParameter("username"); // 단일 쿼리 파라미터 값 조회
        // Enumeration<String> parameterNames = req.getParameterNames(); // 여러가지 쿼리 파라미터의 key 조회
        // Map<String, String[]> parameterMap = req.getParameterMap(); // Map 형태로 쿼리 파라미터 정보 가져오기
        // String[] usernames = req.getParameterValues("username"); // 여러개 username 쿼리 파라미터 값 조회

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
