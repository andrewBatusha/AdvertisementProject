package servlet.management;

import email.TLSEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/explanatoryServlet")
public class ExplanatoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();
        String reason = request.getParameter("reason");
        String details = request.getParameter("details");
        TLSEmail.sendMessage(email, reason , details);
        session.removeAttribute("email");
        response.sendRedirect(request.getContextPath() +"/manageServlet");
    }
}