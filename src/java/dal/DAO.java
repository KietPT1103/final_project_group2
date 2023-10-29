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

    public Account getAccountByUsername(String userName) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[fullname]\n"
                + "      ,[phone]\n"
                + "      ,[picture]\n"
                + "      ,[created_at]\n"
                + "      ,[updated_at]\n"
                + "      ,[role]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[account]\n"
                + "  WHERE [username] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account acc = new Account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("phone"), rs.getString("Address"), rs.getString("picture"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("role"));
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertCustomer(Account cus) {
        String sql = "INSERT INTO account (username, password, fullname, phone, Address)\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cus.getUserName());
            st.setString(2, cus.getPassword());
            st.setString(3, cus.getFullName());
            st.setString(4, cus.getPhone());
            st.setString(5, cus.getAddress());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertAdmin(Account acc) {
        String sql = "INSERT INTO account (username, password, fullname, phone, Address, role)\n"
                + "VALUES (?, ?, ?, ?, ?, 1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getUserName());
            st.setString(2, acc.getPassword());
            st.setString(3, acc.getFullName());
            st.setString(4, acc.getPhone());
            st.setString(5, acc.getAddress());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteAccount(String username) {
        String sql = "DELETE FROM [dbo].[account]\n"
                + "      WHERE [username] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Account> getListOfAccount(int role) {
        List<Account> list = new ArrayList<Account>();
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
                + " WHERE [role] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("phone"), rs.getString("Address"), rs.getString("picture"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("role"));
                list.add(acc);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void inserProduct(Product pro) {
        String sql = "INSERT INTO [dbo].[product]\n"
                + "           ([pro_id]\n"
                + "           ,[pro_name]\n"
                + "           ,[pro_description]\n"
                + "           ,[pro_price]\n"
                + "           ,[pro_quantity]\n"
                + "           ,[pro_picture]\n"
                + "           ,[cate_id])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro.getId());
            st.setString(2, pro.getName());
            st.setString(3, pro.getDescription());
            st.setLong(4, pro.getPrice());
            st.setInt(5, pro.getQuantity());
            st.setString(6, pro.getPicture());
            st.setString(7, pro.getCatergory().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProduct(Product pro) {
        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [pro_name] = ?\n"
                + "      ,[pro_description] = ?\n"
                + "      ,[pro_price] = ?\n"
                + "      ,[pro_quantity] = ?\n"
                + "      ,[pro_picture] = ?\n"
                + "      ,[cate_id] = ?\n"
                + " WHERE [pro_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro.getName());
            st.setString(2, pro.getDescription());
            st.setLong(3, pro.getPrice());
            st.setInt(4, pro.getQuantity());
            st.setString(5, pro.getPicture());
            st.setString(6, pro.getCatergory().getId());
            st.setString(7, pro.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteProduct(String id) {
        String sql = "DELETE FROM [dbo].[product]\n"
                + "      WHERE [pro_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertCategory(Category cat) {
        String sql = "INSERT INTO [dbo].[categories]\n"
                + "           ([cate_id]\n"
                + "           ,[cate_name]\n"
                + "           ,[cate_describe])\n"
                + "     VALUES(?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cat.getId());
            st.setString(2, cat.getName());
            st.setString(3, cat.getDescribe());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteCategory(String id) {
        String sql = "DELETE FROM [dbo].[categories]\n"
                + "      WHERE cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCategory(Category cate) {
        String sql = "UPDATE [dbo].[categories]\n"
                + "   SET [cate_name] = ?\n"
                + "      ,[cate_describe] = ?\n"
                + " WHERE cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cate.getName());
            st.setString(2, cate.getDescribe());
            st.setString(3, cate.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Category> getListOfCategory() {
        List<Category> list = new ArrayList<Category>();
        String sql = "SELECT [cate_id]\n"
                + "      ,[cate_name]\n"
                + "      ,[cate_describe]\n"
                + "  FROM [dbo].[categories]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category cate = new Category(rs.getString("cate_id"), rs.getString("cate_name"), rs.getString("cate_describe"));
                list.add(cate);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

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
        String sql = "select * from categories where cate_id = ?";
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
                + "  FROM [dbo].[product]\n";

        try {
            if ((!id.isEmpty() && !id.equals("") && !id.equals("all"))) {
                sql += "WHERE cate_id = " + id;
//                st.setString(1, id);
            }
            PreparedStatement st = connection.prepareStatement(sql);
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
//        Product pro = d.getProductById("ip001");
//        System.out.println(pro.getName());
//    }
}
