package servlet;

import dao.PoolConnectionBuilder;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl(new PoolConnectionBuilder());
        userService = new UserService(userDAO);
        System.out.println("UserServlet is Created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("We are in userServlet");
        List<User> allUsersList = userService.getAllUserList();
        req.setAttribute("allUserList",allUsersList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user.jsp");
        requestDispatcher.forward(req,resp);
    }
}
