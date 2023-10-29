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
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/updateProduct"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        DAO dao = new DAO();
        Product pro = dao.getProductById(id);
        System.out.println(pro.getName());
        request.setAttribute("pro", pro);
        List<Category> list = dao.getListOfCategory();
        request.setAttribute("data", list);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
        DAO dao = new DAO();
        System.out.println("In do post method of Add Image servlet");

        Part filePart = request.getPart("picture");
        String id = request.getParameter("id");

        Product productOld = dao.getProductById(id);

        String fileName;

        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName();
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

            String fileNameOld = productOld.getPicture();
            String deletePath = getServletContext().getRealPath("assets/picture_pro/") + File.separator + fileNameOld;
            File fileToDelete = new File(deletePath);
            try {
                if (fileToDelete.exists()) {
                    if (fileToDelete.delete()) {
                        System.out.println("File deleted successfully.");
                    } else {
                        System.out.println("Failed to delete file.");
                        request.setAttribute("error", "Failed to delete file.");
                        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
                    }
                } else {
                    System.err.println("File does not exist.");
                    request.setAttribute("error", "File does not exist.");
                    request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            fileName = productOld.getPicture();
        }

        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        String categoryItem = request.getParameter("categoryItem");

        Category cate = dao.getCateById(categoryItem);

        long price;
        int quantity;
        try {
            price = Long.parseLong(price_raw);
            quantity = Integer.parseInt(quantity_raw);
            Product pro = new Product(id, name, describe, price, quantity, fileName, cate);
            dao.updateProduct(pro);
            response.sendRedirect("productmangement?id=");
        } catch (NumberFormatException e) {
            System.out.println(e);
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
