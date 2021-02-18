package servlet;

import dao.PoolConnectionBuilder;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAOImpl(new PoolConnectionBuilder());
        productService = new ProductService(productDAO);
        System.out.println("productServlet is Created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("We are in productServlet");
        List<Product> productList = productService.getAllProductList();
        req.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product.jsp");
        requestDispatcher.forward(req,resp);
    }
}
