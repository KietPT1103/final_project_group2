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
import model.Cart;
import model.Product;
import model.Category;
import model.History;
import model.Item;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author LENOVO
 */
public class DAO extends DBContext {

    public void deleteOrderByOrderId(int OrderId) {
        String sqlCart = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE [or_id] = ?";
        String sqlCartDetail = "DELETE FROM [dbo].[cartdetail]\n"
                + "      WHERE [or_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sqlCartDetail);
            st.setInt(1, OrderId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            PreparedStatement st = connection.prepareStatement(sqlCart);
            st.setInt(1, OrderId);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateNodication(int OrderId) {
        String sql = "UPDATE cart\n"
                + "SET nodication = 1\n"
                + "WHERE [or_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderDetail> getListOfOrderDetailByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        String sql = "SELECT [or_id]\n"
                + "      ,[pro_id]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "  FROM [dbo].[cartdetail]\n"
                + "  WHERE [or_id]= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product pro = getProductById(rs.getString("pro_id"));
                OrderDetail ord = new OrderDetail(rs.getInt("or_id"), pro, rs.getInt("quantity"), rs.getLong("price"));
                list.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Order getOrderByOrderId(int OrderId) {
        String sql = "SELECT [or_id]\n"
                + "      ,[or_date]\n"
                + "      ,[username]\n"
                + "      ,[or_totalmoney]\n"
                + "      ,[nodication]\n"
                + "  FROM [dbo].[cart]\n"
                + "  WHERE [or_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, OrderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order order = new Order(rs.getInt("or_id"), rs.getDate("or_date"), rs.getString("username"), rs.getLong("or_totalmoney"), rs.getBoolean("nodication"));
                return order;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Order> getListOfOrder() {
        List<Order> list = new ArrayList<Order>();
        String sql = "SELECT [or_id]\n"
                + "      ,[or_date]\n"
                + "      ,[username]\n"
                + "      ,[or_totalmoney]\n"
                + "      ,[nodication]\n"
                + "  FROM [dbo].[cart]";
//                + "  WHERE [nodication] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
//            st.setBoolean(1, notifition);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("or_id"), rs.getDate("or_date"), rs.getString("username"), rs.getLong("or_totalmoney"), rs.getBoolean("nodication"));
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

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
        String sql = "UPDATE [dbo].[account]\n"
                + "   SET [role] = 3\n"
                + " WHERE [username] = ?";

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
        String sql1 = "DELETE FROM [dbo].[product]\n"
                + "      WHERE [cate_id] = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, id);
            st1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        String sql = "DELETE FROM [dbo].[categories]\n"
                + "      WHERE cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
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
                Account a = new Account(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("picture"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at"),
                        rs.getInt("role"));
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
                p.setPrice(rs.getLong("pro_price"));
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
     * Hàm lấy ra tất cả các product có cate id trùng nhau
     *
     * @param id id do người dùng nhập vào
     * @return list các product có cùng cate_id
     */
    public List<Product> getRandomProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 4 * FROM [product] ORDER BY NEWID()";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("pro_id"));
                p.setName(rs.getString("pro_name"));
                p.setDescription(rs.getString("pro_description"));
                p.setPrice(rs.getLong("pro_price"));
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
                    + "select * from x where r between ? * 8-7 and ? * 8";
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
    public List<Product> searchAsc(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (ORDER BY pro_price ASC) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 8-7 and ? * 8";
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
    public List<Product> searchDesc(String txtSearch, int index, int size) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (ORDER BY pro_price Desc) as r\n"
                    + ",* from product where pro_name like ?)\n"
                    + "select * from x where r between ? * 8-7 and ? * 8";
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

    /**
     * Hàm thêm đơn hàng người dùng đặt vào bảng cart, sau đó lưu lại từng
     * product người dùng dặt vào bảng cartdetail
     *
     * @param acc tài khoảng của người dùng
     * @param cart đơn hàng của người dùng, chứa các item(sản phẩm do người dùng
     * mua)
     */
    public void addOrder(Account acc, Cart cart) {
        try {
            //add order
            String sql = "INSERT INTO [dbo].[cart]\n"
                    + "           ([username]\n"
                    + "           ,[or_totalmoney])\n"
                    + "     VALUES (?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getUserName());
            st.setDouble(2, cart.getTotalMoney());
            st.executeUpdate();

            //lấy order id vừa add
            String sql1 = "select top 1 or_id from [cart] order by or_id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();

            //add dữ liệu vào bảng order detail
            if (rs.next()) {
                int oid = rs.getInt("or_id");
                for (Item i : cart.getItems()) {  //Lấy tất cả các item có trong cart hiện tại add dô database
                    String sql2 = "insert into [cartdetail] values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid); //Lấy id của 1 order
                    st2.setString(2, i.getProduct().getId());  // lấy ra id của sản phẩm trong cart đó
                    st2.setInt(3, i.getQuantity()); //Lấy ra số lượng mua
                    st2.setDouble(4, i.getPrice()); //Lấy ra tổng giá của sản phẩm mua đó
                    st2.executeUpdate();
                }
            }

            //Cập nhật lại số lượng sản phẩm còn lại trong kho
            String sql3 = "update product set pro_quantity = pro_quantity - ? where pro_id = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setString(2, i.getProduct().getId());
                st3.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<History> getHistoryByUsername(String username) throws SQLException {
        List<History> list = new ArrayList<>();
        String sql = "select * from cart c\n"
                + "join cartdetail cd on c.or_id = cd.or_id\n"
                + "where username = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Product p = getProductById(rs.getString("pro_id"));
            History h = new History(rs.getInt("or_id"),
                    rs.getString("or_date"),
                    rs.getString("username"),
                    p,
                    rs.getInt("quantity"),
                    rs.getLong("price"),
                    rs.getBoolean("nodication"));
            list.add(h);
        }
        return list;
    }

//    public static void main(String[] args) throws SQLException {
//        DAO d = new DAO();
//        List<History> li = d.getHistoryByUsername("exampleUser@gmail.com");
//        System.out.println("Li: "+ li);
//        for (History history : li) {
//            System.out.println("H: "+ history.getId());
//        }
//    }
//    /**
//     * Hàm lấy ra các sản phẩm khách hàng đã mua trong cùng 1 giỏ hàng
//     *
//     * @param oid id của giỏ hàng đó
//     * @return list chứa tất cả các mặt hàng khách mua
//     */
//    public List<OrderDetail> getOrderDetailByOrderId(int oid) {
//        List<OrderDetail> list = null;
//        try {
//            String sql = "select * from cartdetail where oid = ?";
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, oid);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                OrderDetail od = new OrderDetail(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        rs.getLong(4));
//                list.add(od);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
}
