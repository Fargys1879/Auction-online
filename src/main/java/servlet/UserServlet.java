package servlet;

import dao.PoolConnectionBuilder;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl(new PoolConnectionBuilder());
        userService = new UserService(userDAO);
        log("UserServlet init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        res.getWriter().println("User Service\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("We are doGet in UserServlet");
        List<User> allUsersList = userService.getAllUserList();
        req.setAttribute("allUserList",allUsersList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
        log("UserServlet destroy");
    }
}
