package servlet;

import dao.PoolConnectionBuilder;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl(new PoolConnectionBuilder());
        userService = new UserService(userDAO);
        log("RegisterServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String adress = req.getParameter("adress");

        User user = new User(name,adress,login,password);
        if (userService.checkUserPresence(user) == false) {
            userService.addNewUser(user);
            resp.sendRedirect("/users");
            return;
        } else {
            out.println("<h3> User already exist! <h3/>");
            req.getRequestDispatcher("register.jsp").include(req, resp);
            out.close();
        }
    }

    @Override
    public void destroy() {
        log("RegisterServlet destroy");
    }
}
