package com.sullung2yo.servletpractice.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="requestHeaderServlet", urlPatterns = {"/request-header"})
public class RequestHeaderServlet extends HttpServlet {

    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        request.getHeaderNames().asIterator()
                .forEachRemaining(
                        headerName -> System.out.println(headerName + ": " + request.getHeader(headerName))
                );
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    //기타 정보 조회
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); // 호스트 정보
        System.out.println("request.getRemoteAddr() = " +request.getRemoteAddr()); // 호스트 정보
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); // 호스트 포트 정보
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); // localhost
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); // 127.0.0.0.1
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); // 8080
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--- REQUEST-LINE - start ---");
        printHeaders(req); // 헤더 기본 정보 출력
        printHeaderUtils(req); // 헤더 추가 정보 조회
        printEtc(req); // 기타 정보 조회
        System.out.println("request.getMethod() = " + req.getMethod()); // GET
        System.out.println("request.getProtocol() = " + req.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + req.getScheme()); // http 프로토콜
        System.out.println("request.getRequestURL() = " + req.getRequestURL()); // http://127.0.0.1:8080/request-header
        System.out.println("request.getRequestURI() = " + req.getRequestURI()); // /request-header
        System.out.println("request.getQueryString() = " + req.getQueryString()); // username=hi 없다면 null
        System.out.println("request.isSecure() = " + req.isSecure()); // https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }
}
