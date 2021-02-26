package servlet;

import dao.BidDAO;
import dao.PoolConnectionBuilder;
import dao.ProductDAO;
import dao.UserDAO;
import dao.impl.BidDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Product;
import entity.User;
import service.BidService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private BidDAO bidDAO;

    private ProductService productService;
    private BidService bidService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        PoolConnectionBuilder poolConnectionBuilder = new PoolConnectionBuilder();
        productDAO = new ProductDAOImpl(poolConnectionBuilder);
        userDAO = new UserDAOImpl(poolConnectionBuilder);
        bidDAO = new BidDAOImpl(poolConnectionBuilder);

        productService = new ProductService(productDAO);
        bidService = new BidService(productDAO,userDAO,bidDAO);
        log("ProductServlet init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        res.getWriter().println("Product Service\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("We are doGet in productServlet");
        List<Product> productList = productService.getAllProductList();
        req.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        String bidderLogin = (String) session.getAttribute("login");
        Long uid = Long.parseLong(req.getParameter("uid"));
        if (bidService.bidProduct(uid, bidderLogin)) {
            resp.sendRedirect("/products");
        }
        out.println("Bid is not success");
        out.close();
    }

    @Override
    public void destroy() {
        log("ProductServer destroy");
    }
}
