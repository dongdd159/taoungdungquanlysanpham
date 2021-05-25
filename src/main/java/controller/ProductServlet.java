package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "create":
                displayNewProductForm(request,response);
                break;
            case "edit":
                displayEditForm(request,response);
                break;
            case "delete":
                displayDeleteForm(request,response);
                break;
            case "view":
                viewProduct(request,response);
                break;

            default:
                displayProductList(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
            default:
                break;
        }
    }
    private void displayProductList(HttpServletRequest request, HttpServletResponse response){
        List<Product> productList = productService.findAll();
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            requestDispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void displayNewProductForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createNewProduct(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int id = productService.getSize();
        Product product = new Product(id,name);
        productService.save(product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message","New product was created");
        try {
            requestDispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void displayEditForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findbyId(id);
        RequestDispatcher requestDispatcher;
        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        }
        try {
            requestDispatcher.forward(request,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Product product = productService.findbyId(id);
        RequestDispatcher requestDispatcher;
        if(product==null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            product.setId(id);
            product.setName(name);
            productService.update(id,product);
            request.setAttribute("message","Product information was updated");
            requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        }
        try {
            requestDispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void displayDeleteForm(HttpServletRequest request, HttpServletResponse response){
        int id =Integer.parseInt(request.getParameter("id"));
        Product product = productService.findbyId(id);
        RequestDispatcher requestDispatcher;
        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("product/delete.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findbyId(id);
        RequestDispatcher requestDispatcher;
        if(product==null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            productService.remove(id);
            try {
                response.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findbyId(id);
        RequestDispatcher requestDispatcher;
        if (product == null) {
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Product product = productService.findbyName(name);
        RequestDispatcher requestDispatcher;
        if (product==null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            requestDispatcher = request.getRequestDispatcher("product/search.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
