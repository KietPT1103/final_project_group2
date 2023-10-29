/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/createProduct"})
@MultipartConfig
public class CreateProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<Category> list = dao.getListOfCategory();
        request.setAttribute("data", list);
        request.getRequestDispatcher("createProduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("In do post method of Add Image servlet");

        // xử lý file image
        Part filePart = request.getPart("picture");
        String fileName = filePart.getSubmittedFileName();
        System.out.println(fileName);

        String uploadPath = getServletContext().getRealPath("assets/picture_pro/") + File.separator + fileName;
        System.out.println("Upload path: " + uploadPath);

        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = filePart.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        String categoryItem = request.getParameter("categoryItem");

        DAO dao = new DAO();

        Category cate = dao.getCateById(categoryItem);

        long price;
        int quantity;
        try {
            price = Long.parseLong(price_raw);
            quantity = Integer.parseInt(quantity_raw);
            Product proFind = dao.getProductById(id);
            if (proFind == null) {
                Product pro = new Product(id, name, describe, price, quantity, fileName, cate);
                dao.inserProduct(pro);
                response.sendRedirect("productmangement?id=");
            } else {
                List<Category> list = dao.getListOfCategory();
                request.setAttribute("data", list);
                request.setAttribute("error", "id: " + id + " is exitsed!");
                request.getRequestDispatcher("createProduct.jsp").forward(request, response);
            }
        } catch (Exception e) {
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
