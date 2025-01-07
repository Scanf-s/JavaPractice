package com.sullung2yo.servletpractice.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// "/hello" 경로에 대한 요청이 들어오면, 이 클래스가 실행됨
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // servlet 호출 시 service 메서드가 실행됨. 이걸 오버라이드해서 입맛에 맞게 바꿔야함
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.service(req, resp);
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req); // soutv로 자동생성
        System.out.println("req.hostname = " + req.getRemoteHost());
        System.out.println("req.hostname = " + req.getContentType());


        System.out.println("resp = " + resp);
        
        String name = req.getParameter("name"); // 쿼리 파라미터 확인
        System.out.println("name = " + name);

        // 그냥 텍스트만 반환
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello " + name); // http response body에 데이터 추가
    }
}
