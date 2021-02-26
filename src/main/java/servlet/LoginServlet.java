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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl(new PoolConnectionBuilder());
        userService = new UserService(userDAO);
        log("LoginServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String login=req.getParameter("login");
        String password=req.getParameter("password");

        HttpSession session=req.getSession();
        session.setAttribute("login",login);

        List<User> allUserList = userService.getAllUserList();
        for (User user : allUserList) {
            if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
                FilterServlet.authorizationUser = true;
                resp.sendRedirect("/");
                return;
            }
        }
        out.print("<h3> Sorry, Username or Password error! <h3/>");
        req.getRequestDispatcher("login.jsp").include(req, resp);
        out.close();
    }

    @Override
    public void destroy() {
        log("LoginServlet destroy");
    }
}
