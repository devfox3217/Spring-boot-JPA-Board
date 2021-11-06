package com.devfox.board.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PageScriptUtil {

    public static void initial(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    public static void alert(HttpServletResponse response, String alertText) throws IOException {
        initial(response);
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('" + alertText + "');</script> ");
        out.flush();
    }

    public static void alertAndMove(HttpServletResponse response, String alertText, String nextPage) throws IOException {
        initial(response);
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
        out.flush();
    }

    public static void alertAndBack(HttpServletResponse response, String alertText) throws IOException {
        initial(response);
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('" + alertText + "'); history.go(-1);</script>");
        out.flush();
    }

}
