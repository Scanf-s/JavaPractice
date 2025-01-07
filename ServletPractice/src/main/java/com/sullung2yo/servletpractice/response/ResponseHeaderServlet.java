package com.sullung2yo.servletpractice.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    private void setContent(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //생략시 자동으로 생성해줌
    }

    private void setCookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void setRedirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        response.setStatus(HttpServletResponse.SC_FOUND); // 302 Found
        response.setHeader("Location", "/basic/hello-form.html"); // Redirect 위치
        response.sendRedirect("/basic/hello-form.html"); // 이 경로로 리다이렉트
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK); // 200 OK로 설정
        resp.setHeader("Content-Type", "text/plain;charset=utf-8"); // Header 추가

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 관련 헤더 (캐시 무효화)
        resp.setHeader("Pragma", "no-cache"); // 캐시 관련 헤더 (캐시 무효화)

        resp.setHeader("my-header", "hello"); // 커스텀 헤더 추가


        //setContent(resp); // Content-type 설정
        //setCookie(resp); // Cookie 설정
        setRedirect(resp); // Redirect 설정

        //간단한 message body 설정
        PrintWriter writer = resp.getWriter();
        writer.println("ok");
    }
}
