package com.jsu.chat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * com.jsu.chat.servlet
 *
 * @desc
 * @author:EumJi
 * @year: 2016
 * @month: 09
 * @day: 02
 * @time: 2016/9/2
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        req.getSession().setAttribute("username",username);
        //重定向
        resp.sendRedirect("chat.jsp");
    }
}
