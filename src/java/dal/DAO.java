/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Product;
import model.Category;

/**
 *
 * @author LENOVO
 */
public class DAO extends DBContext {

    public Account checkAccount(String userName, String password) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[fullname]\n"
                + "      ,[phone]\n"
                + "      ,[picture]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "      ,[role]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[account]"
                + "  WHERE username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("phone"), rs.getString("address"), rs.getString("picture"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("role"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm lấy ra tất cả product trong database
     *
     * @return all product
     */
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Category cate = getCateById(rs.getString(7));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getString(6),
                        cate);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Hàm lấy ra Category theo id
     *
     * @param cate_id id truyền vào để tìm cate
     * @return Category theo id
     */
    public Category getCateById(String cate_id) {
        String sql = "select * from category where cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, cate_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Category c = new Category(rs.getString(1), rs.getString(2), rs.getString(3));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm trả về một product dựa vào id
     *
     * @param id id do người dùng truyền vào
     * @return trả về một product có id trùng với id truyền vào
     */
    public Product getProductById(String id) {
        String sql = "select * from product where pro_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category cate = getCateById(rs.getString(7));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getString(6),
                        cate);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Hàm lấy ra tất cả các product có cate id trùng nhau
     *
     * @param id id do người dùng nhập vào
     * @return list các product có cùng cate_id
     */
    public List<Product> getProductByCategoryId(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [pro_id]\n"
                + "      ,[pro_name]\n"
                + "      ,[pro_description]\n"
                + "      ,[pro_price]\n"
                + "      ,[pro_quantity]\n"
                + "      ,[pro_picture]\n"
                + "      ,[cate_id]\n"
                + "  FROM [dbo].[product]";
        if (!id.isEmpty() && !id.equals("")) {
            sql += "WHERE cate_id = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("pro_id"));
                p.setName(rs.getString("pro_name"));
                p.setDescription(rs.getString("pro_description"));
                p.setPrice(rs.getInt("pro_price"));
                p.setQuantity(rs.getInt("pro_quantity"));
                p.setPicture(rs.getString("pro_picture"));
                Category cate = getCateById(rs.getString("cate_id"));
                p.setCatergory(cate);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return list;
    }

    /**
     * Hàm đếm tất cả các sản phẩm có tên trùng với từ khóa người dùng nhập vào
     *
     * Hàm dùng để đếm xem ta cần chia các sản phẩm kia ra bao nhiêu trang
     *
     * @param txtSearch Từ khóa do người dung nhập vào
     * @return trả về số lượng các sản phẩm được tìm thấy
     */
    public int countProduct(String txtSearch) {
        try {
            String sql = "select count(*) from product where pro_name like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * Select ra một bảng ảo chứa số lượng sản phẩm muốn lấy ra, chứa một cột ảo
     * x chứa số thứ tự ảo của các prouct vừa select ra. Sau đó chọn ra các sản
     * phẩm trong khoảng mong muốn rồi luu nó vào list. trả về list đó
     *
     * Hàm dùng để lấy ra các sản phẩm có thứ tự theo khoảng nhất định để in ra
     * màn hình product
     *
     * @param txtSearch Từ khóa do người dùng nhập vào
     * @param index 
     * @param size
     * @return list chứa số lượng sản phẩm ta muốn
     */
    public List<Product> search(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by pro_id) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 3-2 and ? * 3";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2, index);
            st.setInt(3, index);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Các tham số phải trùng tên với các cột trong bảng trong SQL, nếu không không tạo được
                Category cate = getCateById(rs.getString(8));
                Product p = new Product(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5),
                        rs.getInt(6),
                        rs.getString(7),
                        cate);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        DAO d = new DAO();
//        int count = d.countProduct("n");
//        System.out.println("Check: "+ count);
//    }
}
